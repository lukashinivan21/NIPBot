package tgbots.nipbot.service.handlers;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import tgbots.nipbot.constants.Shelter;

public interface Handler {

    public BaseRequest handle(Update update, Shelter shelter);

}
