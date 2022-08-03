package tgbots.nipbot.service;

import org.springframework.stereotype.Service;
import tgbots.nipbot.models.Candidate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class Validation {
    //
    private final static Pattern patternPhoneNumberAndFullName = Pattern.compile("^(([+]7|8)[0-9]{10})\\s+(([а-яёА-яЁ]+|[a-zA-Z]+\\s*)|(([а-яёА-яЁ]+|[a-zA-Z]+)\\s+([а-яёА-яЁ]+|[a-zA-Z]+)))\\s*$");

    public Validation() {
    }

    public static boolean isValidPhoneNumberAndFullName(String string){
        Matcher matcher = patternPhoneNumberAndFullName.matcher(string);
        return matcher.matches();
    }

    public static Candidate createCandidateFromRegex(String string){
        Matcher matcher = patternPhoneNumberAndFullName.matcher(string);
        if(matcher.matches()){
            Candidate candidate = new Candidate();
            String[] strings = matcher.group(0).split("\\s+");
            if(strings[0].startsWith("+7")){
                strings[0] = strings[0].replace("+7", "8");
            }
            candidate.setPhoneNumber(strings[0]);
            candidate.setFirstName(strings[1]);
            if(strings.length >= 3){
                candidate.setSecondName(strings[2]);
            }
            return candidate;
        }
        return null;
    }
}
