/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic.formValidation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Aks
 */
public class FormValidator {

    public boolean verifyStringInput(String input) {
        if (null != input && !(input.equals(""))) {
            return true;
        }
        return false;
    }

    public boolean verifyZipCodeFormat(String input) {
        if (isNumeric(input)) {
            if (input.length() == 5) {
                return true;
            }
        }
        return false;
    }

    public boolean isNumeric(String input) {
        Pattern pattern = Pattern.compile("-?\\d+(.\\d+)?"); // use \\p{L} for Unicode support
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public boolean isAlphabetical(String input) {
        Pattern pattern = Pattern.compile("^\\p{Alpha}+$"); // use \\p{L} for Unicode support
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public boolean verifyEmailAddress(String input) {
        if(input.contains("@") && input.contains(".")) {

            return true;

        }
        return false;
    }

    public boolean verifyAreaCode(String input) {
        if (isNumeric(input)) {
            if (input.length() == 3) {
                return true;
            }
        }
        return false;
    }

    public boolean verifyPhoneNumber(String input) {
        if (isNumeric(input)) {
            if (input.length() == 7) {
                return true;
            }
        }
        return false;
    }

    public boolean verifyDateFormat(String inputDate, String dateFormat) {
        Date date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        simpleDateFormat.setLenient(false);

        try {
            //throw parse exception if invalid
            date = simpleDateFormat.parse(inputDate);

        } catch (ParseException e) {
            return false;

        }
        Date currentDate = new Date();
        if(date.after(currentDate)) {
            return false;
        }
        return true;
    }
}
