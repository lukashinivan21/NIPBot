package tgbots.nipbot.service.handlers;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
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

        //CallbackQuery info меню
        if(data.equals(ABOUT_SHELTER.name())){
            return replyKeyboard.addInfoMenu(new EditMessageText(chatId, messageId, ABOUT_SHELTER.getResponse()));
        } else if(data.equals(WORK_SCHEDULE.name())){
            return replyKeyboard.addInfoMenu(new EditMessageText(chatId, messageId, WORK_SCHEDULE.getResponse()));
        } else if(data.equals(GENERAL_SAFETY_RECOMMENDATIONS.name())){
            return replyKeyboard.addInfoMenu(new EditMessageText(chatId, messageId, GENERAL_SAFETY_RECOMMENDATIONS.getResponse()));
        }

        //CallbackQuery take_dog меню и recommendation меню
        if(data.equals(DATING_RULES.name())){
            return replyKeyboard.addTakeDogMenu(new EditMessageText(chatId, messageId, DATING_RULES.getResponse()));
        } else if(data.equals(LIST_OF_DOCUMENTS.name())){
            return replyKeyboard.addTakeDogMenu(new EditMessageText(chatId, messageId, LIST_OF_DOCUMENTS.getResponse()));
        } else if(data.equals(LIST_RECOMMENDATIONS.name())){
            return replyKeyboard.addRecommendationMenu(new SendMessage(chatId, LIST_RECOMMENDATIONS.getResponse()));
        }
        else if(data.equals(LIST_RECOMMENDATIONS_TRANSPORTATION.name())){
            return replyKeyboard.addRecommendationMenu(new EditMessageText(chatId, messageId, LIST_RECOMMENDATIONS_TRANSPORTATION.getResponse()));
        } else if(data.equals(LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_PUPPY.name())){
            return replyKeyboard.addRecommendationMenu(new EditMessageText(chatId, messageId, LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_PUPPY.getResponse()));
        } else if(data.equals(LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_DOG.name())){
            return replyKeyboard.addRecommendationMenu(new EditMessageText(chatId, messageId, LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_DOG.getResponse()));
        } else if(data.equals(LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_DOG_WITH_DISABILITIES.name())){
            return replyKeyboard.addRecommendationMenu(new EditMessageText(chatId, messageId, LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_DOG_WITH_DISABILITIES.getResponse()));
        } else if(data.equals(BACK_TO_HOW_TAKE_DOG_FROM_SHELTER.name())){
            return replyKeyboard.addTakeDogMenu(new SendMessage(chatId, BACK_TO_HOW_TAKE_DOG_FROM_SHELTER.getResponse()));
        }
        else if(data.equals(TIPS_FROM_DOG_HANDLER.name())){
            return replyKeyboard.addTakeDogMenu(new EditMessageText(chatId, messageId, TIPS_FROM_DOG_HANDLER.getResponse()));
        } else if(data.equals(PROVEN_DOG_HANDLERS.name())){
            return replyKeyboard.addTakeDogMenu(new EditMessageText(chatId, messageId, PROVEN_DOG_HANDLERS.getResponse()));
        } else if(data.equals(LIST_REASONS_FOR_REFUSAL.name())){
            return replyKeyboard.addTakeDogMenu(new EditMessageText(chatId, messageId, LIST_REASONS_FOR_REFUSAL.getResponse()));
        }
        //CallbackQuery reports меню
        if(data.equals(DAILY_REPORT_FORM.name())){
            return new SendMessage(chatId, DAILY_REPORT_FORM.getResponse());
        } else if(data.equals(PROBATION_PERIOD.name())){
            return new SendMessage(chatId, PROBATION_PERIOD.getResponse());
        }

        if(data.equals(CONTACT_DETAILS_FOR_COMMUNICATION.name())){
            return new SendMessage(chatId, CONTACT_DETAILS_FOR_COMMUNICATION.getResponse());
        } else if(data.equals(CALL_VOLUNTEER.name())){
            return new SendMessage(chatId, CALL_VOLUNTEER.getResponse());
        }
        return null;
    }
}
