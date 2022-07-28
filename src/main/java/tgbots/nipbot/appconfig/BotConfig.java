package tgbots.nipbot.appconfig;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import tgbots.nipbot.controller.NIPBot;
import tgbots.nipbot.service.Handler;

/**
 * Класс, предназначенный для инициализации объекта класса {@link NIPBot}
 */
@Setter
@Getter
@Configuration
public class BotConfig {

    /**
     * Значение переменной взято из файла application.properties
     */
    @Value("${telegram.bot.webHookPath}")
    private String webHookPath;

    /**
     * Значение переменной взято из файла application.properties
     */
    @Value("${telegram.bot.botUsername}")
    private String botUserName;

    /**
     * Значение переменной взято из файла application.properties
     */
    @Value("${telegram.bot.token}")
    private String token;


    /**
     * Метод, в котором происходит инициализация объекта
     * @return {@link NIPBot}
     */
    @Bean
    public NIPBot nipBot(Handler handler) {

        DefaultBotOptions options = new DefaultBotOptions();

        NIPBot bot = new NIPBot(options, handler);

        bot.setBotUsername(botUserName);
        bot.setWebHookPath(webHookPath);
        bot.setToken(token);

        return bot;
    }


}
