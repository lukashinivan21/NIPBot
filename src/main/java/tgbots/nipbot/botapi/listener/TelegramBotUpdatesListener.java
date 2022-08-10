package tgbots.nipbot.botapi.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tgbots.nipbot.service.handlers.HandlerUpdates;
import tgbots.nipbot.service.mentions.MentionSendReport;

import javax.annotation.PostConstruct;
import java.util.List;

import static tgbots.nipbot.constants.StringConstants.MENTION_TO_SEND_REPORT;

/**
 * Класс, содержащий логику получения обновлений с TelegramAPI,
 * выполняет request с прикрепленной логикой
 */
@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final HandlerUpdates handlerUpdates;
    private final MentionSendReport mentionSendReport;

    public TelegramBotUpdatesListener(HandlerUpdates handlerUpdates, MentionSendReport mentionSendReport) {
        this.handlerUpdates = handlerUpdates;
        this.mentionSendReport = mentionSendReport;
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



    @Scheduled(cron = "0 0 12 * * *")
    public void mentionForUserToSendReport() {
        List<Long> ids = mentionSendReport.idsForMentionToSendReport();
        if (!ids.isEmpty()) {
            ids.forEach(id -> telegramBot.execute(new SendMessage(id, MENTION_TO_SEND_REPORT)));
        }
    }

}
