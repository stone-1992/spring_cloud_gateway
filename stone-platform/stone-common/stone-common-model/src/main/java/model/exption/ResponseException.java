package model.exption;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @classname ExportException
 * @description 输出流导出异常
 * @date 2020/2/10 16:07
 * @author xhe
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseException extends RuntimeException {

    public ResponseException(String msg){
        super(msg);
    }
}
