package com.learn.email.vos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailRequestVo {

    private String to;

    private String subject;

    private String message;

}
