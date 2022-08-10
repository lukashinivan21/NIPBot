package tgbots.nipbot.service.handlers;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import org.springframework.stereotype.Service;

/**
 * Класс, служащий для фильрации обновлений.
 * В зависимости от содержания обновления направляет его в определенный обработчик
 */
@Service
public class HandlerUpdates {

    private final HandlerMessages handlerMessages;
    private final HandlerCallbackQuery handlerCallbackQuery;
    private final HandlerMessagesWithPhoto handlerMessagesWithPhoto;

    public HandlerUpdates(HandlerMessages handlerMessages, HandlerCallbackQuery handlerCallbackQuery, HandlerMessagesWithPhoto handlerMessagesWithPhoto) {
        this.handlerMessages = handlerMessages;
        this.handlerCallbackQuery = handlerCallbackQuery;
        this.handlerMessagesWithPhoto = handlerMessagesWithPhoto;
    }

    /**
     * Метод, служащий для фильрации обновлений.
     * В зависимости от содержания обновления направляет его в определенный обработчик
     *
     * @param update, полученный с {@link tgbots.nipbot.botapi.listener.TelegramBotUpdatesListener}
     * @return {@link BaseRequest} или null
     */
    public BaseRequest choiceHandler(Update update) {
        if (update != null) {
            if (update.message() != null) {
                return handlerMessages.handle(update);
            }
            if (update.callbackQuery() != null) {
                return handlerCallbackQuery.handle(update);
            }
            if (update.message() != null && update.message().photo() != null) {
                handlerMessagesWithPhoto.handle(update);
            }
        }
        return null;
    }
}
