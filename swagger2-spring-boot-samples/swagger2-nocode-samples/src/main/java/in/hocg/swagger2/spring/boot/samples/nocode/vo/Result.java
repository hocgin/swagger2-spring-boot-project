package in.hocg.swagger2.spring.boot.samples.nocode.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

/**
 * @author hocgin
 * @date 2019/6/13
 */
@Data
@Accessors(chain = true)
@ApiModel("响应")
public class Result<T> implements Serializable {
    @ApiModelProperty(value = "状态码", required = true, example = "200")
    private int code;
    @ApiModelProperty(value = "消息", required = true, example = "ok")
    private String message;
    @ApiModelProperty(value = "响应", required = true)
    private T data;
    
    private Result() {
    }
    
    public static Result newInstance() {
        return new Result();
    }
    
    public static Result result(boolean result) {
        return result ? success() : error();
    }
    
    
    public static <T> Result<T> success(T data) {
        return Result.result(200, "ok", data);
    }
    
    public static <T> Result<T> success() {
        return Result.success(null);
    }
    
    public static <T> Result<T> result(Integer code, String message) {
        return Result.result(code, message, null);
    }
    
    public static <T> Result<T> error(String message) {
        return Result.result(500, message, null);
    }
    
    public static <T> Result<T> error() {
        return Result.result(500, "error", null);
    }
    
    public static <T> Result<T> result(Integer code, String message, T data) {
        Result<T> result = new Result<>();
        return result.setCode(code)
                .setMessage(message)
                .setData(data);
    }
    
    public ResponseEntity<Result<T>> asResponseEntity() {
        return ResponseEntity.ok(this);
    }
    
}
