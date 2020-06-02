package model.exption;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @classname UnauthorizedException
 * @description 未授权异常401
 * @date 2020/2/11 14:45
 * @author xhe
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }
}
