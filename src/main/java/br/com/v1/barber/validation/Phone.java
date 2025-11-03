package br.com.v1.barber.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Document
@Constraint(validatedBy = PhoneValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface  Phone {

    String message() default "Invalid phone number";
    Class<?>[] groups() default {};

    Class<?extends Payload>[] payload()default {};
}
