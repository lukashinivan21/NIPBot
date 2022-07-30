package tgbots.nipbot.service.handlers;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;

public interface Handler {

    public BaseRequest handle(Update update);

}
