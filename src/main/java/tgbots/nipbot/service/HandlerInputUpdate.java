package tgbots.nipbot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Интерфейс, отвечающий за обработку текста сообщений и обратных сигналов от кнопок клавиатур
 */
public interface HandlerInputUpdate {

    /**
     * Метод для обработки текста входящих сообщений
     * @return {@link SendMessage}
     */
    SendMessage handleMessage(Message message);

    /**
     * Метод для обработки сигналов от кнопок клавиатур
     * @return {@link SendMessage}
     */
    SendMessage handleCallback(CallbackQuery callbackQuery);
}
