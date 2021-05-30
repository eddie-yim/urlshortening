package com.example.presentation.url;

import org.apache.logging.log4j.util.Strings;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UrlRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UrlRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UrlRequest request = (UrlRequest) target;
        String originalUrl = request.getOriginal().trim();
        if (Strings.isBlank(originalUrl)) {
            errors.rejectValue("original", "url.original.required");
        }
    }
}
