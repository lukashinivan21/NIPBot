package tgbots.nipbot.botapi.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tgbots.nipbot.service.handlers.HandlerUpdates;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

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

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            //logger.info("Processing update: {}", update.);
            BaseRequest request = handlerUpdates.choiceHandler(update);
            if(request != null){
                telegramBot.execute(request);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
