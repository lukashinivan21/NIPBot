package tgbots.nipbot.service;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Интерфейс, отвечающий за обработку обновлений, приходящих от пользователя в чат
 */
public interface Handler {

    /**
     * Метод для генерации ответного сообщения пользователю
     * @return {@link BotApiMethod}
     */
    SendMessage sendingMessage(Update update);

    /**
     * Метод для генерации файлов с инструкциями и справочной информацией
     * @return {@link SendDocument}
     */
    SendDocument sendingDocument(Update update);
}
