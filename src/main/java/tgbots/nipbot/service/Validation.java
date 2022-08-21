package tgbots.nipbot.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class Validation {

    public final static Pattern PATTERN_PHONE_NUMBER_AND_FULL_NAME = Pattern.compile("^(([+]7|8)[0-9]{10})\\s+(([а-яёА-яЁ]+|[a-zA-Z]+\\s*)|(([а-яёА-яЁ]+|[a-zA-Z]+)\\s+([а-яёА-яЁ]+|[a-zA-Z]+)))\\s*$");
    public final static Pattern PATTERN_PHONE_NUMBER = Pattern.compile("^(([+]7|8)[0-9]{10})$");

    public Validation() {
    }

    public static boolean isValidPhoneNumberAndFullName(String string){
        Matcher matcher = PATTERN_PHONE_NUMBER_AND_FULL_NAME.matcher(string);
        return matcher.matches();
    }
}
