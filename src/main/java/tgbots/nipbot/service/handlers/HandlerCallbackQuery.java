package tgbots.nipbot.service.handlers;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import tgbots.nipbot.constants.Shelter;
import tgbots.nipbot.service.ReplyKeyboard;

import static tgbots.nipbot.constants.TextForButtons.*;
import static tgbots.nipbot.constants.Shelter.*;

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
    public BaseRequest handle(Update update, Shelter shelter){
        CallbackQuery clb = update.callbackQuery();
        Integer messageId = clb.message().messageId();
        String data = clb.data();
        Long chatId = clb.message().chat().id();

        if (shelter.equals(DOG)) {
            //CallbackQuery info меню
            if(data.equals(DOG_ABOUT_SHELTER.name())){
                return replyKeyboard.addInfoMenuDog(new EditMessageText(chatId, messageId, DOG_ABOUT_SHELTER.getResponse()));
            } else if(data.equals(DOG_WORK_SCHEDULE.name())){
                return replyKeyboard.addInfoMenuDog(new EditMessageText(chatId, messageId, DOG_WORK_SCHEDULE.getResponse()));
            } else if(data.equals(DOG_GENERAL_SAFETY_RECOMMENDATIONS.name())){
                return replyKeyboard.addInfoMenuDog(new EditMessageText(chatId, messageId, DOG_GENERAL_SAFETY_RECOMMENDATIONS.getResponse()));
            }

            //CallbackQuery take_dog меню и recommendation меню
            if(data.equals(DOG_DATING_RULES.name())){
                return replyKeyboard.addTakeMenuDog(new EditMessageText(chatId, messageId, DOG_DATING_RULES.getResponse()));
            } else if(data.equals(DOG_LIST_OF_DOCUMENTS.name())){
                return replyKeyboard.addTakeMenuDog(new EditMessageText(chatId, messageId, DOG_LIST_OF_DOCUMENTS.getResponse()));
            } else if(data.equals(DOG_LIST_RECOMMENDATIONS.name())){
                return replyKeyboard.addRecommendationMenuDog(new SendMessage(chatId, DOG_LIST_RECOMMENDATIONS.getResponse()));
            }
            else if(data.equals(DOG_LIST_RECOMMENDATIONS_TRANSPORTATION.name())){
                return replyKeyboard.addRecommendationMenuDog(new EditMessageText(chatId, messageId, DOG_LIST_RECOMMENDATIONS_TRANSPORTATION.getResponse()));
            } else if(data.equals(DOG_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_PUPPY.name())){
                return replyKeyboard.addRecommendationMenuDog(new EditMessageText(chatId, messageId, DOG_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_PUPPY.getResponse()));
            } else if(data.equals(DOG_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_DOG.name())){
                return replyKeyboard.addRecommendationMenuDog(new EditMessageText(chatId, messageId, DOG_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_DOG.getResponse()));
            } else if(data.equals(DOG_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_DOG_WITH_DISABILITIES.name())){
                return replyKeyboard.addRecommendationMenuDog(new EditMessageText(chatId, messageId, DOG_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_DOG_WITH_DISABILITIES.getResponse()));
            } else if(data.equals(DOG_BACK_TO_DOG_HOW_TAKE_FROM_SHELTER.name())){
                return replyKeyboard.addTakeMenuDog(new SendMessage(chatId, DOG_BACK_TO_DOG_HOW_TAKE_FROM_SHELTER.getResponse()));
            }
            else if(data.equals(DOG_TIPS_FROM_DOG_HANDLER.name())){
                return replyKeyboard.addTakeMenuDog(new EditMessageText(chatId, messageId, DOG_TIPS_FROM_DOG_HANDLER.getResponse()));
            } else if(data.equals(DOG_PROVEN_DOG_HANDLERS.name())){
                return replyKeyboard.addTakeMenuDog(new EditMessageText(chatId, messageId, DOG_PROVEN_DOG_HANDLERS.getResponse()));
            } else if(data.equals(DOG_LIST_REASONS_FOR_REFUSAL.name())){
                return replyKeyboard.addTakeMenuDog(new EditMessageText(chatId, messageId, DOG_LIST_REASONS_FOR_REFUSAL.getResponse()));
            }
            //CallbackQuery reports меню
            if(data.equals(DOG_DAILY_REPORT_FORM.name())){
                return new SendMessage(chatId, DOG_DAILY_REPORT_FORM.getResponse());
            } else if(data.equals(DOG_PROBATION_PERIOD.name())){
                return new SendMessage(chatId, DOG_PROBATION_PERIOD.getResponse());
            }

            if(data.equals(DOG_CONTACT_DETAILS_FOR_COMMUNICATION.name())){
                return new SendMessage(chatId, DOG_CONTACT_DETAILS_FOR_COMMUNICATION.getResponse());
            } else if(data.equals(DOG_CALL_VOLUNTEER.name())){
                return new SendMessage(chatId, DOG_CALL_VOLUNTEER.getResponse());
            }
        }
        if (shelter.equals(CAT)) {
            //CallbackQuery info меню
            if(data.equals(CAT_ABOUT_SHELTER.name())){
                return replyKeyboard.addInfoMenuCat(new EditMessageText(chatId, messageId, CAT_ABOUT_SHELTER.getResponse()));
            } else if(data.equals(CAT_WORK_SCHEDULE.name())){
                return replyKeyboard.addInfoMenuCat(new EditMessageText(chatId, messageId, CAT_WORK_SCHEDULE.getResponse()));
            } else if(data.equals(CAT_GENERAL_SAFETY_RECOMMENDATIONS.name())){
                return replyKeyboard.addInfoMenuCat(new EditMessageText(chatId, messageId, CAT_GENERAL_SAFETY_RECOMMENDATIONS.getResponse()));
            }

            //CallbackQuery take меню и recommendation меню
            if(data.equals(CAT_DATING_RULES.name())){
                return replyKeyboard.addTakeMenuCat(new EditMessageText(chatId, messageId, CAT_DATING_RULES.getResponse()));
            } else if(data.equals(CAT_LIST_OF_DOCUMENTS.name())){
                return replyKeyboard.addTakeMenuCat(new EditMessageText(chatId, messageId, CAT_LIST_OF_DOCUMENTS.getResponse()));
            } else if(data.equals(CAT_LIST_RECOMMENDATIONS.name())){
                return replyKeyboard.addRecommendationMenuCat(new SendMessage(chatId, CAT_LIST_RECOMMENDATIONS.getResponse()));
            }
            else if(data.equals(CAT_LIST_RECOMMENDATIONS_TRANSPORTATION.name())){
                return replyKeyboard.addRecommendationMenuCat(new EditMessageText(chatId, messageId, CAT_LIST_RECOMMENDATIONS_TRANSPORTATION.getResponse()));
            } else if(data.equals(CAT_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_PUPPY.name())){
                return replyKeyboard.addRecommendationMenuCat(new EditMessageText(chatId, messageId, CAT_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_PUPPY.getResponse()));
            } else if(data.equals(CAT_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_CAT.name())){
                return replyKeyboard.addRecommendationMenuCat(new EditMessageText(chatId, messageId, CAT_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_CAT.getResponse()));
            } else if(data.equals(CAT_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_CAT_WITH_DISABILITIES.name())){
                return replyKeyboard.addRecommendationMenuCat(new EditMessageText(chatId, messageId, CAT_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_CAT_WITH_DISABILITIES.getResponse()));
            } else if(data.equals(CAT_BACK_TO_CAT_HOW_TAKE_FROM_SHELTER.name())){
                return replyKeyboard.addTakeMenuCat(new SendMessage(chatId, CAT_BACK_TO_CAT_HOW_TAKE_FROM_SHELTER.getResponse()));
            } else if(data.equals(CAT_LIST_REASONS_FOR_REFUSAL.name())){
                return replyKeyboard.addTakeMenuCat(new EditMessageText(chatId, messageId, CAT_LIST_REASONS_FOR_REFUSAL.getResponse()));
            }
            //CallbackQuery reports меню
            if(data.equals(CAT_DAILY_REPORT_FORM.name())){
                return new SendMessage(chatId, CAT_DAILY_REPORT_FORM.getResponse());
            } else if(data.equals(CAT_PROBATION_PERIOD.name())){
                return new SendMessage(chatId, CAT_PROBATION_PERIOD.getResponse());
            }

            if(data.equals(CAT_CONTACT_DETAILS_FOR_COMMUNICATION.name())){
                return new SendMessage(chatId, CAT_CONTACT_DETAILS_FOR_COMMUNICATION.getResponse());
            } else if(data.equals(CAT_CALL_VOLUNTEER.name())){
                return new SendMessage(chatId, CAT_CALL_VOLUNTEER.getResponse());
            }
        }
        return null;
    }
}
