package com.booking.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
/*
test:
{
"account":"12345@qq.com",
"password":12345,
"email":"2196411859@qq.com"
}
* */
public class User {
    private String id;
    private String account;
    private String password;
    private String email;
}
