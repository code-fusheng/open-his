package xyz.fusheng.config.execption; /**
 * @author: code-fusheng
 * @Date: 2020/9/2 11:21
 */

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.fusheng.vo.AjaxResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @FileName: GlobalExceptionHandler
 * @Author: code-fusheng
 * @Date: 2020/9/2 11:21
 * @version: 1.0
 * Description: 全局异常处理器
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 当系统出现MethodArgumentNotValidException这个异常时，会调用下面的方法
     * MethodArgumentNotValidException如果页面传参是json对象如果为空能触发
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public AjaxResult jsonErrorHandler(MethodArgumentNotValidException e){
        // 返回事件对象e,的绑定结果
        return getAjaxResult(e.getBindingResult());
    }


    /**
     * 当系统出现MethodArgumentNotValidException这个异常时，会调用下面的方法
     * MethodArgumentNotValidException如果页面传参是json对象如果为空能触发
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public AjaxResult jsonErrorHandler(BindException e){
        return getAjaxResult(e.getBindingResult());
    }

    /**
     * 抽像出来的方法
     * @param bindingResult
     * @return
     */
    private AjaxResult getAjaxResult(BindingResult bindingResult) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        for (ObjectError allError : allErrors) {
            Map<String, Object> map = new HashMap<>();
            // 校验消息
            map.put("defaultMessage", allError.getDefaultMessage());
            // 校验对象名
            map.put("objectName", allError.getObjectName());
            // 注意，这里面拿到具体的某一个属性
            FieldError fieldError = (FieldError) allError;
            map.put("field", fieldError.getField());
            list.add(map);
        }
        return AjaxResult.fail("后端数据校验异常", list);
    }

}
