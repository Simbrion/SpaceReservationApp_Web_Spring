package com.sra.util.userinputvalidators;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Component
public class DateInputValidator implements ValidationOperation {

    public boolean validate(String userInput) {
        try {
            LocalDate.parse(userInput);
            return true;
        }
        catch (DateTimeParseException e) {
            return false;
        }
    }

}
