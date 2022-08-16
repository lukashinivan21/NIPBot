package tgbots.nipbot.service.handlers;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;

public interface HandleCommandFromVolunteer {

    /**
     * Метод для обработки сообщений-команд от волонтеров и
     * рассылки соотвествующих сообщений пользователям.
     * @return BaseRequest
     */
    BaseRequest message(Update update);
}
