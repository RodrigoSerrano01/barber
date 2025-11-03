package br.com.v1.barber.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator <Phone,String>{

    // Regex para telefones brasileiros: (XX) XXXX-XXXX ou (XX) XXXXX-XXXX ou apenas números
    private static final String PHONE_PATTERN = "^\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) return true;
        return value.matches(PHONE_PATTERN);
    }
}
