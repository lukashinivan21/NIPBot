package tgbots.nipbot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import tgbots.nipbot.constants.Keyboards;

import static tgbots.nipbot.constants.StringConstants.*;

/**
 * Класс, содержащий логику обработки текста входящих сообщений и обратных сигналов от кнопок
 * Используются методы класса {@link Keyboards} - клавиатуры; строковые константы класса {@link tgbots.nipbot.constants.StringConstants}
 */
@Service
public class HandlerInputUpdateImpl implements HandlerInputUpdate {

    /**
     * Метод для обработки текста входящих сообщений
     * @return {@link SendMessage}
     */
    @Override
    public SendMessage handleMessage(Message message) {

        String inputMessage = message.getText();
        String chatId = message.getChatId().toString();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        switch (inputMessage) {
            case START -> {
                sendMessage.setText(MAIN_GREETING);
                sendMessage.enableMarkdown(true);
                sendMessage.setReplyMarkup(Keyboards.keyboard1());
            }
            case TEXT_BUTTON1 -> {
                sendMessage.setText(GREETING_STEP1);
                sendMessage.setReplyMarkup(Keyboards.keyboard2());
            }
            case TEXT_BUTTON2 -> {
                sendMessage.setText(GREETING_STEP2);
                sendMessage.setReplyMarkup(Keyboards.keyboard3());
            }
            case TEXT_BUTTON3 -> {
                sendMessage.setText(MESS_FOR_BUTTON3);
                sendMessage.setReplyMarkup(Keyboards.keyboard4());
            }
            case TEXT_BUTTON4 -> sendMessage.setText(MESS_FOR_BUTTON4);
            default -> sendMessage.setText(MESS_DEFAULT);
        }
        return sendMessage;
    }

    /**
     * Метод для обработки обратных сигналов от кнопок клавиатур
     * @return {@link SendMessage}
     */
    @Override
    public SendMessage handleCallback(CallbackQuery callbackQuery) {

        String chatId = callbackQuery.getMessage().getChatId().toString();
        String data = callbackQuery.getData();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        switch (data) {
            case CALLBACK_BUTTON5 -> sendMessage.setText(MESS_FOR_BUTTON5);
            case CALLBACK_BUTTON6 -> sendMessage.setText(MESS_FOR_BUTTON6);
            case CALLBACK_BUTTON7 -> sendMessage.setText(MESS_FOR_BUTTON7);
            case CALLBACK_BUTTON8 -> sendMessage.setText(MESS_FOR_BUTTON8);
            case CALLBACK_BUTTON9 -> sendMessage.setText(MESS_FOR_BUTTON9);
            case CALLBACK_BUTTON10 -> sendMessage.setText(MESS_FOR_BUTTON10);
            case CALLBACK_BUTTON11 -> sendMessage.setText(MESS_FOR_BUTTON11);
            case CALLBACK_BUTTON12 -> sendMessage.setText(MESS_FOR_BUTTON12);
            case CALLBACK_BUTTON13 -> sendMessage.setText(MESS_FOR_BUTTON13);
            case CALLBACK_BUTTON14 -> sendMessage.setText(MESS_FOR_BUTTON14);
            case CALLBACK_BUTTON15 -> sendMessage.setText(MESS_FOR_BUTTON15);
            case CALLBACK_BUTTON16 -> sendMessage.setText(MESS_FOR_BUTTON16);
            case CALLBACK_BUTTON17 -> sendMessage.setText(MESS_FOR_BUTTON17);
            case CALLBACK_BUTTON18 -> sendMessage.setText(MESS_FOR_BUTTON18);
            case CALLBACK_BUTTON19 -> sendMessage.setText(MESS_FOR_BUTTON19);
            case CALLBACK_BUTTON4 -> sendMessage.setText(MESS_FOR_BUTTON4);
            default -> sendMessage.setText(MESS_DEFAULT);
        }
        return sendMessage;
    }
}
