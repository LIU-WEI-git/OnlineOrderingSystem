package ordering.utils;

/**
 * 异常信息类
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/30 9:19
 */
public class ErrorInfo {

    public int code;
    public String message;
    public String url;

    public ErrorInfo() {
    }

    public ErrorInfo(int code, String message, String url) {
        this.code = code;
        this.message = message;
        this.url = url;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
