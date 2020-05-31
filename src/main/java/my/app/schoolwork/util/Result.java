package my.app.schoolwork.util;

/**
 * 封装返回消息的类
 */
public class Result {
    private int status;//状态码
    private Object data;//消息体
    private String message;//提示

    public Result(int status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static Result ok() {
        return new Result(200, null, "成功");
    }

    public static Result ok(Object data) {
        return new Result(200, data, "成功");
    }

    public static Result ok(Object data, String message) {
        return new Result(200, data, message);
    }

    public static Result error(int status, Object data, String message) {
        return new Result(status, data, message);
    }

    public static Result error(int status, String message) {
        return error(status, null, message);
    }

    public static Result error() {
        return error(511, null, "失败");
    }

    public static Result error(String message) {
        return error(511,null,message);
    }

    //自定义状态码
    //511 失败
    //420 token验证失败
    public static Result error512() {
        return error(512, "token失效，请重新登录");
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", data='" + data.toString() + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
