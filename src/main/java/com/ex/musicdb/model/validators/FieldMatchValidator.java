package com.ex.musicdb.model.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstField;
    private String secondField;
    private String massage;


    @Override
    public void initialize(FieldMatch constraintAnnotation) {
       this.firstField = constraintAnnotation.first();
       this.secondField = constraintAnnotation.second();
       this.massage= constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);

        Object firstFieldValue =  beanWrapper.getPropertyValue(firstField);
        Object secondFieldValue =  beanWrapper.getPropertyValue(secondField);


        boolean valid ;

        if (firstFieldValue == null){
            valid = secondFieldValue == null;
        }else {
            valid = firstFieldValue.equals(secondFieldValue);
        }

        if (!valid){
            context.buildConstraintViolationWithTemplate(massage)
                    .addPropertyNode(firstField).addConstraintViolation()
                    .buildConstraintViolationWithTemplate(massage)
                    .addPropertyNode(secondField)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }
}
