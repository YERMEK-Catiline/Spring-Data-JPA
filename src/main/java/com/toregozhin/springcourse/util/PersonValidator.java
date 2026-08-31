package com.toregozhin.springcourse.util;

import com.toregozhin.springcourse.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;


@Component
public class PersonValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (person.getDateOfBirth() == null) {
            errors.rejectValue("dateOfBirth", "person.dateOfBirth.null", "Пустая дата рождения");
        } else if (person.getDateOfBirth().after(new Date())) {
            errors.rejectValue("dateOfBirth", "person.dateOfBirth.future",
                    "Дата рождения не может быть в будущем");
        }


    }
}
