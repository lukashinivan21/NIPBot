package tgbots.nipbot.service.handlers;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.PhotoSize;
import org.springframework.stereotype.Service;
import tgbots.nipbot.constants.Shelter;
import tgbots.nipbot.service.cache.CacheShelter;

import static tgbots.nipbot.constants.Shelter.*;
import static tgbots.nipbot.constants.TextForButtons.*;


@Service
public class ShelterDeterminant {

    private final CacheShelter cacheShelter;

    public ShelterDeterminant(CacheShelter cacheShelter) {
        this.cacheShelter = cacheShelter;
    }

    public Shelter determinate(Message message){
        String text = message.text();
        Long id = message.chat().id();
        PhotoSize[] photo = message.photo();

        Shelter shelter = Shelter.DEFAULT;

        if (photo != null) {
            if (cacheShelter.getShelter(id) != null) {
                shelter = cacheShelter.getShelter(id);
            }
        }

        if (text != null) {
            if (cacheShelter.getShelter(id) != null) {
                shelter = cacheShelter.getShelter(id);
            }
            if(text.equals(DOG_SHELTER.getTextButton())){
                cacheShelter.addUpdateShelter(id, DOG);
                shelter = DOG;
            } else if(text.equals(CAT_SHELTER.getTextButton())){
                cacheShelter.addUpdateShelter(id, CAT);
                shelter = CAT;
            }
        }
        return shelter;
    }

    public Shelter determinate(CallbackQuery callbackQuery){
        String data = callbackQuery.data();
        Long id = callbackQuery.message().chat().id();
        if (cacheShelter.getShelter(id) != null) {
            return cacheShelter.getShelter(id);
        }
        if(data.startsWith(DOG.name())){
            cacheShelter.addUpdateShelter(id, DOG);
            return DOG;
        } else if(data.startsWith(CAT.name())){
            cacheShelter.addUpdateShelter(id, CAT);
            return CAT;
        }
        return Shelter.DEFAULT;
    }


}
