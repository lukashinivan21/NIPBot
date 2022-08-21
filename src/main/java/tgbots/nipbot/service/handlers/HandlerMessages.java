package tgbots.nipbot.service.handlers;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import tgbots.nipbot.constants.Shelter;
import tgbots.nipbot.constants.TextForButtons;
import tgbots.nipbot.models.CatCandidate;
import tgbots.nipbot.models.DogCandidate;
import tgbots.nipbot.service.ReplyKeyboard;
import tgbots.nipbot.service.Validation;
import tgbots.nipbot.service.by_models.CandidateServiceImpl;

import static tgbots.nipbot.constants.Shelter.CAT;
import static tgbots.nipbot.constants.Shelter.DOG;
import static tgbots.nipbot.constants.TextForButtons.*;

/**
 * Класс, служащий для обработки обновления {@link Message}.
 */
@Service
public class HandlerMessages implements Handler{

    private final ReplyKeyboard replyKeyboard;
    private final CandidateServiceImpl candidateService;

    public HandlerMessages(ReplyKeyboard replyKeyboard, CandidateServiceImpl candidateService) {
        this.replyKeyboard = replyKeyboard;
        this.candidateService = candidateService;
    }

    /**
     * Метод обрабатывает обновление с сообщением.
     * В зависимости от содержания добавляет в ответное сообщение определенную клавиатуру и текст
     * @param update, отфильраванный, содержащий сообщение
     * @return {@link BaseRequest} с необходимыми добавлениями или null
     */
    @Override
    public BaseRequest handle(Update update, Shelter shelter){
        Message msg = update.message();
        User from = msg.from();
        String firstName = from.firstName();
        String secondName = from.lastName();
        String username = from.username();
        String text = msg.text();
        Long chatId = msg.chat().id();
        if(shelter.equals(Shelter.DEFAULT)){
            return replyKeyboard.addStartMenu(new SendMessage(chatId, START.getResponse()));
        }
        if (shelter.equals(DOG)) {
            if (text.equals(DOG_SHELTER.getTextButton())) {
                if (isFirstTimeUser(chatId, shelter)) {
                    candidateService.saveCandidate(DogCandidate.create(chatId, firstName, secondName, username), false, shelter);
                    return replyKeyboard.addMainMenuDog(new SendMessage(chatId, START_NEW.getResponse().replace("@NAME", firstName)));
                } else {
                    return replyKeyboard.addMainMenuDog(new SendMessage(chatId, START_OLD.getResponse().replace("@NAME", firstName)));
                }
            } else if(text.equals(DOG_INFO_ABOUT_SHELTER.getTextButton())){
                return replyKeyboard.addInfoMenuDog(new SendMessage(chatId, DOG_INFO_ABOUT_SHELTER.getResponse()));
            } else if(text.equals(DOG_HOW_TAKE_FROM_SHELTER.getTextButton())){
                return replyKeyboard.addTakeMenuDog(new SendMessage(chatId, DOG_HOW_TAKE_FROM_SHELTER.getResponse()));
            } else if(text.equals(DOG_SEND_PET_REPORT.getTextButton())){
                return replyKeyboard.addReportMenuDog(new SendMessage(chatId, DOG_SEND_PET_REPORT.getResponse()));
            } else if(text.equals(DOG_CALL_VOLUNTEER.getTextButton())){
                return new SendMessage(chatId, DOG_CALL_VOLUNTEER.getResponse());
            } else if(Validation.isValidPhoneNumberAndFullName(text)){
                candidateService.updateCandidate(msg, text, shelter);
                return new SendMessage(chatId, CONTACT_SAVE.getResponse());
            } else if(text.equals(BACK_MAIN_MENU.getTextButton())){
                return replyKeyboard.addStartMenu(new SendMessage(chatId, BACK_MAIN_MENU.getResponse()));
            }
        }
        if(shelter.equals(CAT)){
            if (text.equals(CAT_SHELTER.getTextButton())) {
                if (isFirstTimeUser(chatId, shelter)) {
                    candidateService.saveCandidate(CatCandidate.create(chatId, firstName, secondName, username), false, shelter);
                    return replyKeyboard.addMainMenuCat(new SendMessage(chatId, START_NEW.getResponse().replace("@NAME", firstName)));
                } else {
                    return replyKeyboard.addMainMenuCat(new SendMessage(chatId, START_OLD.getResponse().replace("@NAME", firstName)));
                }
            } else if(text.equals(CAT_INFO_ABOUT_SHELTER.getTextButton())){
                return replyKeyboard.addInfoMenuCat(new SendMessage(chatId, CAT_INFO_ABOUT_SHELTER.getResponse()));
            } else if(text.equals(CAT_HOW_TAKE_FROM_SHELTER.getTextButton())){
                return replyKeyboard.addTakeMenuCat(new SendMessage(chatId, CAT_HOW_TAKE_FROM_SHELTER.getResponse()));
            } else if(text.equals(CAT_SEND_PET_REPORT.getTextButton())){
                return replyKeyboard.addReportMenuCat(new SendMessage(chatId, CAT_SEND_PET_REPORT.getResponse()));
            } else if(text.equals(CAT_CALL_VOLUNTEER.getTextButton())){
                return new SendMessage(chatId, CAT_CALL_VOLUNTEER.getResponse());
            } else if(Validation.isValidPhoneNumberAndFullName(text)){
                candidateService.updateCandidate(msg, text, shelter);
                return new SendMessage(chatId, CONTACT_SAVE.getResponse());
            } else if(text.equals(BACK_MAIN_MENU.getTextButton())){
                return replyKeyboard.addStartMenu(new SendMessage(chatId, BACK_MAIN_MENU.getResponse()));
            }
        }
        return new SendMessage(chatId, TextForButtons.DEFAULT.getTextButton());
    }

    /**
     * Метод проверяет первый раз пользователь запускает бота или нет
     * @return true, если пользователь первый раз запускает бота, иначе false
     */
    private boolean isFirstTimeUser(Long id, Shelter shelter){
        try {
            candidateService.findCandidateById(id, shelter);
        } catch (NotFoundException e) {
            return true;
        }
        return false;
    }
}
