package tgbots.nipbot.service.handlers;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import tgbots.nipbot.models.Candidate;
import tgbots.nipbot.service.ReplyKeyboard;
import tgbots.nipbot.service.Validation;
import tgbots.nipbot.service.by_models.CandidateServiceImpl;

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
    public BaseRequest handle(Update update){
        Message msg = update.message();
        User from = msg.from();
        String firstName = from.firstName();
        String secondName = from.lastName();
        String username = from.username();
        String text = msg.text();
        Long chatId = msg.chat().id();
        if (text.equals(START_NEW.getTextButton())) {
            if (isFirstTimeUser(chatId)) {
                candidateService.saveCandidate(Candidate.create(chatId, firstName, secondName, username));
                return replyKeyboard.addMainMenu(new SendMessage(chatId, START_NEW.getResponse().replace("@NAME", firstName)));
            } else {
                return replyKeyboard.addMainMenu(new SendMessage(chatId, START_OLD.getResponse().replace("@NAME", firstName)));
            }
        } else if(text.equals(INFO_ABOUT_DOG_SHELTER.getTextButton())){
            return replyKeyboard.addInfoMenu(new SendMessage(chatId, INFO_ABOUT_DOG_SHELTER.getResponse()));
        } else if(text.equals(HOW_TAKE_DOG_FROM_SHELTER.getTextButton())){
            return replyKeyboard.addTakeDogMenu(new SendMessage(chatId, HOW_TAKE_DOG_FROM_SHELTER.getResponse()));
        } else if(text.equals(SEND_PET_REPORT.getTextButton())){
            return replyKeyboard.addReportMenu(new SendMessage(chatId, SEND_PET_REPORT.getResponse()));
        } else if(text.equals(CALL_VOLUNTEER.getTextButton())){
            return new SendMessage(chatId, CALL_VOLUNTEER.getResponse());
        } else if(Validation.isValidPhoneNumberAndFullName(text)){
            candidateService.updateCandidate(msg, text);
            return new SendMessage(chatId, CONTACT_SAVE.getResponse());
        }
        return new SendMessage(chatId, DEFAULT.getTextButton());
    }

    /**
     * Метод проверяет первый раз пользователь запускает бота или нет
     * @return true, если пользователь первый раз запускает бота, иначе false
     */
    private boolean isFirstTimeUser(Long id){
        Candidate candidate = candidateService.findCandidateById(id);
        if(candidate != null){
            return false;
        }
        return true;
    }
}
