package tgbots.nipbot.service.handlers;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import org.springframework.stereotype.Service;

@Service
public class HandlerUpdates {

    private final HandlerMessages handlerMessages;
    private final HandlerCallbackQuery handlerCallbackQuery;


    public HandlerUpdates(HandlerMessages handlerMessages, HandlerCallbackQuery handlerCallbackQuery) {
        this.handlerMessages = handlerMessages;
        this.handlerCallbackQuery = handlerCallbackQuery;
    }

    public BaseRequest choiceHandler(Update update){
        if(update != null){
            if(update.message() != null){
                return handlerMessages.handle(update);
            }
            if(update.callbackQuery() != null){
                return handlerCallbackQuery.handle(update);
            }
        }
        return null;
    }
}
