package tgbots.nipbot.botapi.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tgbots.nipbot.service.handlers.HandlerUpdates;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Класс, содержащий логику получения обновлений с TelegramAPI,
 * выполняет request с прикрепленной логикой
 */
@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final HandlerUpdates handlerUpdates;

    public TelegramBotUpdatesListener(HandlerUpdates handlerUpdates) {
        this.handlerUpdates = handlerUpdates;
    }

    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    /**
     * Метод, содержащий логику получения обновлений с TelegramAPI
     * Также метод выполняет request с прикрепленной логикой
     * @param updates, которые бот получил в чате
     * @return CONFIRMED_UPDATES_ALL
     */
    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            BaseRequest request = handlerUpdates.choiceHandler(update);
            if(request != null){
                telegramBot.execute(request);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
