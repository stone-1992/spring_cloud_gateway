package com.stone.auth.component;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.ClientAuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import java.util.Locale;

/**
 * @classname WebResponseExceptionTranslator
 * @description OAuth Server 异常处理,重写oauth 默认实现
 * @author stone
 */
@Slf4j
public class WebAppResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {

    private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) {
        // Try to extract a SpringSecurityException from the stacktrace
        Throwable[] causeChain = throwableAnalyzer.determineCauseChain(e);

        Exception ase = (AuthenticationException) throwableAnalyzer
                .getFirstThrowableOfType(AuthenticationException.class, causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new UnauthorizedException(e.getMessage(), e));
        }

        ase = (AccessDeniedException) throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class,
                causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new ForbiddenException(ase.getMessage(), ase));
        }

        ase = (InvalidGrantException) throwableAnalyzer.getFirstThrowableOfType(InvalidGrantException.class,
                causeChain);
        if (ase != null) {
            String msg = SpringSecurityMessageSource.getAccessor().getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials", ase.getMessage(), Locale.CHINA);
            return handleOAuth2Exception(new InvalidException(msg, ase));
        }

        ase = (HttpRequestMethodNotSupportedException) throwableAnalyzer
                .getFirstThrowableOfType(HttpRequestMethodNotSupportedException.class, causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new MethodNotAllowedException(ase.getMessage(), ase));
        }

        ase = (OAuth2Exception) throwableAnalyzer.getFirstThrowableOfType(OAuth2Exception.class, causeChain);

        if (ase != null) {
            return handleOAuth2Exception((OAuth2Exception) ase);
        }

        return handleOAuth2Exception(new ServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e));

    }

    private ResponseEntity<OAuth2Exception> handleOAuth2Exception(OAuth2Exception e) {

        int status = e.getHttpErrorCode();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CACHE_CONTROL, "no-store");
        headers.set(HttpHeaders.PRAGMA, "no-cache");
        if (status == HttpStatus.UNAUTHORIZED.value() || (e instanceof InsufficientScopeException)) {
            headers.set(HttpHeaders.WWW_AUTHENTICATE,
                    String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, e.getSummary()));
        }

        // 客户端异常直接返回客户端,不然无法解析
        if (e instanceof ClientAuthenticationException) {
            return new ResponseEntity<>(e, headers, HttpStatus.valueOf(status));
        }
        return new ResponseEntity<>(new Auth2Exception(e.getMessage(), e.getOAuth2ErrorCode()), headers,
                HttpStatus.valueOf(status));
    }

    @JsonSerialize(using = Auth2ExceptionSerializer.class)
    public static class Auth2Exception extends OAuth2Exception {

        @Getter
        private String errorCode;

        public Auth2Exception(String msg) {
            super(msg);
        }

        public Auth2Exception(String msg, Throwable t) {
            super(msg, t);
        }

        public Auth2Exception(String msg, String errorCode) {
            super(msg);
            this.errorCode = errorCode;
        }

    }

    @JsonSerialize(using = Auth2ExceptionSerializer.class)
    public class ForbiddenException extends Auth2Exception {

        public ForbiddenException(String msg) {
            super(msg);
        }

        public ForbiddenException(String msg, Throwable t) {
            super(msg, t);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return "access_denied";
        }

        @Override
        public int getHttpErrorCode() {
            return HttpStatus.FORBIDDEN.value();
        }
    }

    @JsonSerialize(using = Auth2ExceptionSerializer.class)
    public class InvalidException extends Auth2Exception {

        public InvalidException(String msg, Throwable t) {
            super(msg);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return "invalid_exception";
        }

        @Override
        public int getHttpErrorCode() {
            return 426;
        }
    }

    @JsonSerialize(using = Auth2ExceptionSerializer.class)
    public class MethodNotAllowedException extends Auth2Exception {

        public MethodNotAllowedException(String msg, Throwable t) {
            super(msg);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return "method_not_allowed";
        }

        @Override
        public int getHttpErrorCode() {
            return HttpStatus.METHOD_NOT_ALLOWED.value();
        }
    }

    @JsonSerialize(using = Auth2ExceptionSerializer.class)
    public class ServerErrorException extends Auth2Exception {

        public ServerErrorException(String msg, Throwable t) {
            super(msg);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return "server_error";
        }

        @Override
        public int getHttpErrorCode() {
            return HttpStatus.INTERNAL_SERVER_ERROR.value();
        }

    }

    @JsonSerialize(using = Auth2ExceptionSerializer.class)
    public class UnauthorizedException extends Auth2Exception {

        public UnauthorizedException(String msg, Throwable t) {
            super(msg);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return "unauthorized";
        }

        @Override
        public int getHttpErrorCode() {
            return HttpStatus.UNAUTHORIZED.value();
        }

    }
}
