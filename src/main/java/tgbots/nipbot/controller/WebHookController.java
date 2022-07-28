package tgbots.nipbot.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Контроллер, принимающий входящие запросы от пользователя
 */
@RestController
public class WebHookController {

    private final NIPBot nipBot;

    public WebHookController(NIPBot nipBot) {
        this.nipBot = nipBot;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BotApiMethod<?> apiMethod(@RequestBody Update update) {
        return nipBot.onWebhookUpdateReceived(update);
    }
}
