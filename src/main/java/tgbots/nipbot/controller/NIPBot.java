/*
package tgbots.nipbot.controller;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import tgbots.nipbot.service.Handler;

import static tgbots.nipbot.constants.StringConstants.LIST_CALLBACKS;

*/
/**
 * Класс, содержащий главные поля и методы для работы бота
 *//*

public class NIPBot extends TelegramWebhookBot {

    private final Handler handler;

    */
/**
     * Поле для адреса, по которому происходит обращение к боту
     *//*

    private String webHookPath;
    */
/**
     * Поле для именя бота
     *//*

    private String botUsername;
    */
/**
     * Поле для уникального ключа бота
     *//*

    private String token;

    public NIPBot(DefaultBotOptions botOptions, Handler handler) {
        super(botOptions);
        this.handler = handler;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }

    public void setWebHookPath(String webHookPath) {
        this.webHookPath = webHookPath;
    }

    public void setBotUsername(String botUsername) {
        this.botUsername = botUsername;
    }

    public void setToken(String token) {
        this.token = token;
    }

    */
/**
     * Основной метод, отвечающий за отправку ответов на сообщения пользователя
     * Исрользует методы интерфейса {@link Handler}
     * @return {@link BotApiMethod}
     *//*

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        SendDocument sendDocument = null;
        if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            if (LIST_CALLBACKS.contains(data)) {
                sendDocument = handler.sendingDocument(update);
            }
        }
        if (sendDocument != null) {
            try {
                execute(sendDocument);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        return handler.sendingMessage(update);
    }
}
*/
