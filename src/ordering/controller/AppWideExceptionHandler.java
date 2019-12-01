package ordering.controller;

import ordering.utils.ErrorInfo;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import javax.servlet.http.HttpServletRequest;

/**
 * 定义应用全局异常处理
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/30 8:28
 */
//@ControllerAdvice
public class AppWideExceptionHandler {

    private static final String DEFAULT_ERROR_VIEW = "error"; // 定义错误显示页，error.html
    private static final Integer INIT_ERROR_CODE = 500; // 定义初始错误码

    @ExceptionHandler(value = Exception.class) // 所有的异常都是Exception子类
    public String defaultErrorHandler(HttpServletRequest request, Exception e, Model model) {
        ErrorInfo errorInfo = new ErrorInfo();
        //默认异常状态码为999，其他异常
        int errorCode = 999;
        // 对错误类型进行分类
        String header = request.getHeader("content-type");
        if (header != null && header.contains("json")) {
            errorCode = 300; // json异常
        } else if (e instanceof ArithmeticException) {
            errorCode = 100; // 算术异常
        } else if (e instanceof NullPointerException) {
            errorCode = 200; // 空指针异常
        } else if (e instanceof BindException || e instanceof HttpMessageNotReadableException ||
                e instanceof MethodArgumentNotValidException || e instanceof MissingServletRequestParameterException ||
                e instanceof MissingServletRequestPartException || e instanceof TypeMismatchException) {
            errorCode = 400;
        } else if (e instanceof NoSuchRequestHandlingMethodException) {
            errorCode = 404;
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            errorCode = 405;
        } else if (e instanceof HttpMediaTypeNotAcceptableException) {
            errorCode = 406;
        } else if (e instanceof HttpMediaTypeNotSupportedException) {
            errorCode = 415;
        } else if (e instanceof HttpMessageNotWritableException) {
            errorCode = 500;
        }

        errorInfo.setCode(errorCode);
        errorInfo.setMessage(e.getMessage());// 将异常对象传递过去
        errorInfo.setUrl(request.getRequestURL().toString());// 获得请求的路径
        model.addAttribute(errorInfo);
        return "error";
    }
}
