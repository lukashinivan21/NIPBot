package tgbots.nipbot.service.handlers;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import tgbots.nipbot.service.ReplyKeyboard;

import static tgbots.nipbot.constants.TextForButtons.*;

/**
 * Класс, служащий для обработки обновления с {@link CallbackQuery}.
 */
@Service
public class HandlerCallbackQuery implements Handler{

    private final ReplyKeyboard replyKeyboard;

    public HandlerCallbackQuery(ReplyKeyboard replyKeyboard) {
        this.replyKeyboard = replyKeyboard;
    }

    /**
     * Метод обрабатывает обновление с CallbackQuery.
     * В зависимости от содержания добавляет в ответное сообщение определенную клавиатуру и текст
     * @param update, отфильраванный, содержащий CallbackQuery
     * @return {@link BaseRequest} с необходимыми добавлениями или null
     */
    @Override
    public BaseRequest handle(Update update){
        CallbackQuery clb = update.callbackQuery();
        Integer messageId = clb.message().messageId();
        String data = clb.data();
        Long chatId = clb.message().chat().id();
        if(data.equals(ABOUT_SHELTER.name())){
            return replyKeyboard.addInfoMenu(new EditMessageText(chatId.toString(), messageId, ABOUT_SHELTER.getResponse()));
        } else if(data.equals(WORK_SCHEDULE.name())){
            return replyKeyboard.addInfoMenu(new EditMessageText(chatId.toString(), messageId, WORK_SCHEDULE.getResponse()));
        } else if(data.equals(GENERAL_SAFETY_RECOMMENDATIONS.name())){
            return replyKeyboard.addInfoMenu(new EditMessageText(chatId.toString(), messageId, GENERAL_SAFETY_RECOMMENDATIONS.getResponse()));
        } else if(data.equals(CONTACT_DETAILS_FOR_COMMUNICATION.name())){
            return replyKeyboard.addInfoMenu(new EditMessageText(chatId.toString(), messageId, CONTACT_DETAILS_FOR_COMMUNICATION.getResponse()));
        } else if(data.equals(CALL_VOLUNTEER.name())){
            return new SendMessage(chatId, CALL_VOLUNTEER.getResponse());
        }
        return null;
    }
}
