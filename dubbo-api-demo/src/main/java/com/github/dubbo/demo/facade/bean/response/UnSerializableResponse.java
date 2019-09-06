package com.github.dubbo.demo.facade.bean.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnSerializableResponse {

    private Long id;

    private String name;

    private String desc;

}
