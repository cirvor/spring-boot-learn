package com.cirvor.learn.controller;

import com.cirvor.learn.pojo.User;
import com.cirvor.learn.utils.HttpEnum;
import com.cirvor.learn.utils.ResultUtils;
import com.cirvor.learn.vo.ResultData;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@Validated
@RequestMapping("validate")
public class ValidatedController {

    @GetMapping
    public ResultData index(
            @NotBlank(message = "name 不能为空")
            @Length(min = 2, max = 10, message = "name 长度必须在 {min} - {max} 之间") String name) throws ConstraintViolationException {
        return ResultUtils.success(name);
    }

    @GetMapping("user")
    public ResultData user(@Validated User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return ResultUtils.error(HttpEnum.BAD_REQUEST, "参数校验失败", fieldErrors);
        }

        return ResultUtils.success(user);
    }
}
