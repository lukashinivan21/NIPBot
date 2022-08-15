package tgbots.nipbot.constants;

import tgbots.nipbot.exception.ShelterNotFoundException;
import tgbots.nipbot.models.*;

public enum Shelter {
    DEFAULT, DOG, CAT;

    public static Shelter fromString(String text){
        if(text == null){
            return null;
        }
        text = text.toUpperCase();
        if(text.equals(DOG.name())){
            return DOG;
        }
        if(text.equals(CAT.name())){
            return CAT;
        }
        throw new ShelterNotFoundException();
    }

    public static Report choiceReport(Shelter shelter){
        if(shelter.equals(Shelter.DOG)){
            return new DogReport();
        } else if(shelter.equals(CAT)){
            return new CatReport();
        }
        throw new ShelterNotFoundException();
    }

    public static Candidate choiceCandidate(Shelter shelter){
        if(shelter.equals(Shelter.DOG)){
            return new DogCandidate();
        } else if(shelter.equals(CAT)){
            return new CatCandidate();
        }
        throw new ShelterNotFoundException();
    }
}
