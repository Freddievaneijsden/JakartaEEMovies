package com.example.rules;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Egen definition på annotation och egen validator som då bekräftar objekt som bär annotationen
@Target({ElementType.TYPE}) //PÅ vad kan annotations användas
@Retention(RetentionPolicy.RUNTIME) //Hur länge ska den finnas kvar
@Constraint(validatedBy = ValidMovieValidator.class) //Vad heter klassen som validerar denna annotation
public @interface ValidMovie {
    String message() default "Not a valid movie";
    Class <?>[] groups () default {};
    Class<? extends Payload>[] payload() default {}; //Skicka in extra information att validera mot
}
