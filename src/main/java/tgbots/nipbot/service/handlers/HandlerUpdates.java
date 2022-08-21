package tgbots.nipbot.service.handlers;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import org.springframework.stereotype.Service;
import tgbots.nipbot.constants.Shelter;
import tgbots.nipbot.models.Volunteer;
import tgbots.nipbot.repositories.VolunteerRepository;

import java.util.List;

/**
 * Класс, служащий для фильрации обновлений.
 * В зависимости от содержания обновления направляет его в определенный обработчик
 */
@Service
public class HandlerUpdates {

    private final ShelterDeterminant shelterDeterminant;
    private final HandlerMessages handlerMessages;
    private final HandlerCallbackQuery handlerCallbackQuery;
    private final HandlerMessagesWithPhoto handlerMessagesWithPhoto;
    private final VolunteerRepository volunteerRepository;
    private final HandleCommandFromVolunteer commandFromVolunteer;

    public HandlerUpdates(ShelterDeterminant shelterDeterminant,
                          HandlerMessages handlerMessages,
                          HandlerCallbackQuery handlerCallbackQuery,
                          HandlerMessagesWithPhoto handlerMessagesWithPhoto, VolunteerRepository volunteerRepository,
                          HandleCommandFromVolunteer commandFromVolunteer) {
        this.handlerMessages = handlerMessages;
        this.handlerCallbackQuery = handlerCallbackQuery;
        this.handlerMessagesWithPhoto = handlerMessagesWithPhoto;
        this.shelterDeterminant = shelterDeterminant;
        this.volunteerRepository = volunteerRepository;
        this.commandFromVolunteer = commandFromVolunteer;
    }

    /**
     * Метод, служащий для фильрации обновлений.
     * В зависимости от содержания обновления направляет его в определенный обработчик
     *
     * @param update, полученный с {@link tgbots.nipbot.botapi.listener.TelegramBotUpdatesListener}
     * @return {@link BaseRequest} или null
     */
    public BaseRequest choiceHandler(Update update){
        if(update != null){

            if(update.message() != null){
                Long chatId = update.message().chat().id();
                List<Long> volIds = volunteerRepository.findAll().stream().map(Volunteer::getId).toList();
                if (volIds.contains(chatId)) {
                   return commandFromVolunteer.message(update);
                }

                Shelter shelter = shelterDeterminant.determinate(update.message());
                if (update.message().photo() == null) {
                    return handlerMessages.handle(update, shelter);
                } else {
                    return handlerMessagesWithPhoto.handle(update, shelter);
                }
            }

            if(update.callbackQuery() != null){
                Shelter shelter = shelterDeterminant.determinate(update.callbackQuery());
                return handlerCallbackQuery.handle(update, shelter);
            }
        }
        return null;
    }
}
