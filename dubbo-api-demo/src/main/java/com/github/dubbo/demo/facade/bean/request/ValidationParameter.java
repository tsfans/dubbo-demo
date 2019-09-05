package com.github.dubbo.demo.facade.bean.request;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.github.dubbo.demo.facade.api.ValidationService;

import lombok.Data;

@Data
public class ValidationParameter implements Serializable{

    private static final long serialVersionUID = -6979295239053256495L;

    @NotNull(message = "id can not be null!", groups = ValidationService.Update.class)
    private Long id;

    @NotBlank(message = "name can not be blank!", groups = ValidationService.Add.class) // 不允许为空
    @Size(min = 2, max = 4, message = "name length must between 2 and 4!") // 长度或大小范围
    private String name;

    @NotBlank(message = "email can not be blank!", groups = ValidationService.Add.class) // 新增时不允许为空，更新时允许为空 ，表示不更新该字段
    @Pattern(regexp = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$",
        message = "email must have correct pattern!")
    private String email;

    @Min(value = 1, message = "age must grater than 0!") // 最小值
    @Max(value = 100, message = "age must little than 100!") // 最大值
    private int age;

    @Past(message = "loginDate must be a past date!") // 必须为一个过去的时间
    private Date loginDate;

    @Future(message = "expiryDate must be a future date!") // 必须为一个未来的时间
    private Date expiryDate;
    
}
