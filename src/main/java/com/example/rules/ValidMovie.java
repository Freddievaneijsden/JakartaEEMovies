package com.example.rules;

public @interface ValidMovie {
    String message() default "Not a valid movie";
}
