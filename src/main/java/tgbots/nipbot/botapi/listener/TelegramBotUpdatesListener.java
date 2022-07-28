package tgbots.nipbot.botapi.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            //logger.info("Processing update: {}", update);
            Message msg = update.message();
            if (msg != null) {
                Long chatId = msg.chat().id();

                if(msg.text().equals("1")){
                    SendMessage message = new SendMessage(chatId,"Связь налажена!");
                    telegramBot.execute(message);
                } else if(msg.text().equals("2")){
                    //при создании клавиатуры настраивается колбэк дата
                    InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
                    keyboardMarkup.addRow(
                            new InlineKeyboardButton("Нажми1").callbackData("1"),
                            new InlineKeyboardButton("Нажми2").callbackData("2"));
                    keyboardMarkup.addRow(
                            new InlineKeyboardButton("Клавиша на 2 ряду").callbackData("3"));

                    SendMessage message = new SendMessage(chatId,"Клавиатура")
                            .replyMarkup(keyboardMarkup);
                    telegramBot.execute(message);
                }
            }
            CallbackQuery callbackQuery = update.callbackQuery();
            if(callbackQuery != null){
                System.out.println(callbackQuery.data());
                if (callbackQuery.data().equals("1")) {
                    SendMessage message = new SendMessage(callbackQuery.message().chat().id(),"Ответ на клавиатуру");
                    telegramBot.execute(message);
                } else if(callbackQuery.data().equals("2")) {
                    SendMessage message = new SendMessage(callbackQuery.message().chat().id(), "Ответ на клавиатуру #2");
                    telegramBot.execute(message);
                } else if(callbackQuery.data().equals("3")) {
                    SendMessage message = new SendMessage(callbackQuery.message().chat().id(), "Ответ на клавиатуру #3");
                    telegramBot.execute(message);
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
