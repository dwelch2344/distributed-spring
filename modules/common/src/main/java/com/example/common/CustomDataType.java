package com.example.common;

import lombok.Getter;

/**
 * Created by dave on 4/27/15.
 */
@Getter
public class CustomDataType {

    private String firstName, lastName;

    public CustomDataType() {}

    public CustomDataType(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
