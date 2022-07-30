package tgbots.nipbot.service.handlers;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import tgbots.nipbot.service.ReplyKeyboard;

import static tgbots.nipbot.constants.TextForButtons.*;

/**
 * Класс, служащий для обработки обновления {@link Message}.
 */
@Service
public class HandlerMessages implements Handler{

    private final ReplyKeyboard replyKeyboard;

    public HandlerMessages(ReplyKeyboard replyKeyboard) {
        this.replyKeyboard = replyKeyboard;
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
        String name = msg.from().firstName();
        String text = msg.text();
        Long chatId = msg.chat().id();
        if (text.equals(START_NEW.getTextButton())) {
            if (isFirstTimeUser()) {
                return replyKeyboard.addMainMenu(new SendMessage(chatId, START_NEW.getResponse().replace("@NAME", name)));
            } else {
                return replyKeyboard.addMainMenu(new SendMessage(chatId, START_OLD.getResponse().replace("@NAME", name)));
            }
        } else if(text.equals(INFO_ABOUT_DOG_SHELTER.getTextButton())){
            return replyKeyboard.addInfoMenu(new SendMessage(chatId, INFO_ABOUT_DOG_SHELTER.getResponse()));
        } else if(text.equals(HOW_TAKE_DOG_FROM_SHELTER.getTextButton())){
            return null;
        } else if(text.equals(SEND_PET_REPORT.getTextButton())){
            return null;
        } else if(text.equals(CALL_VOLUNTEER.getTextButton())){
            return new SendMessage(chatId, CALL_VOLUNTEER.getResponse());
        }
        return new SendMessage(chatId, DEFAULT.getTextButton());
    }

    /**
     * Метод проверяет первый раз пользователь запускает бота или нет
     * @return true, если пользователь первый раз запускает бота, иначе false
     */
    private boolean isFirstTimeUser(){
        //НЕ ЗАБЫТЬ!!!
        return true;
    }
}
