package tgbots.nipbot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static tgbots.nipbot.constants.Files.*;
import static tgbots.nipbot.constants.StringConstants.*;

/**
 * Класс, содержащий логику обработки обновлений, приходящих от пользователя в чат
 */
@Service
public class MainHandlerImpl implements Handler{

    private final HandlerInputUpdate handlerInputUpdate;

    private final Logger logger = LoggerFactory.getLogger(MainHandlerImpl.class);

    public MainHandlerImpl(HandlerInputUpdate handlerInputUpdate) {
        this.handlerInputUpdate = handlerInputUpdate;
    }

    /**
     * Метод для обработки обновления и возвращения текстового сообщения (при необходимости с клавиатурой)
     * @return {@link SendMessage}
     */
    @Override
    public SendMessage sendingMessage(Update update) {
        SendMessage result = null;

        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            logger.info("New callbackQuery from User: {}, userId: {}, withData: {}", callbackQuery.getFrom().getUserName(),
                    callbackQuery.getFrom().getId(), callbackQuery.getData());
            result = handlerInputUpdate.handleCallback(callbackQuery);
        }

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            logger.info("New message from User: {}, userId: {}, chatId: {}, with text: {}", message.getFrom().getUserName(), message.getFrom().getId(),
                    message.getChatId(), message.getText());
            result = handlerInputUpdate.handleMessage(message);
        }
        return result;
    }

    /**
     * Метод для обработки обновлений и возвращения документа с инструкцией или справочной информацией
     * @return {@link SendMessage}
     */
    @Override
    public SendDocument sendingDocument(Update update) {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        String data = callbackQuery.getData();
        String chatId = callbackQuery.getMessage().getChatId().toString();
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        switch (data) {
            case CALLBACK_BUTTON5 -> sendDocument.setDocument(FULL_INFO);
            case CALLBACK_BUTTON6 -> sendDocument.setDocument(FULL_ADDRESS);
            case CALLBACK_BUTTON7 -> sendDocument.setDocument(FULL_REC);
            case CALLBACK_BUTTON9 -> sendDocument.setDocument(FULL_CONTACT);
            case CALLBACK_BUTTON10 -> sendDocument.setDocument(LIST_DOC);
            case CALLBACK_BUTTON11 -> sendDocument.setDocument(LIST_REC);
            case CALLBACK_BUTTON12 -> sendDocument.setDocument(PUPPY);
            case CALLBACK_BUTTON13 -> sendDocument.setDocument(DOG);
            case CALLBACK_BUTTON14 -> sendDocument.setDocument(LIMIT);
            case CALLBACK_BUTTON15 -> sendDocument.setDocument(ADVICES);
            case CALLBACK_BUTTON16 -> sendDocument.setDocument(EXPERTS);
            case CALLBACK_BUTTON17 -> sendDocument.setDocument(REASONS);
            case CALLBACK_BUTTON18 -> sendDocument.setDocument(FORM);
            case CALLBACK_BUTTON19 -> sendDocument.setDocument(INST);
        }
        return sendDocument;
    }

}
