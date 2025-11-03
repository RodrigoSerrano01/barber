package br.com.v1.barber.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = DateValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface DateDDMMYYYY {
    String message() default "Invalid date, must be in dd/MM/yyyy format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

