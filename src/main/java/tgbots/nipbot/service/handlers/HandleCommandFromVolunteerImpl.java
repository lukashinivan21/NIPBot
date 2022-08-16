package tgbots.nipbot.service.handlers;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import tgbots.nipbot.constants.StringConstants;
import tgbots.nipbot.service.cache.CacheIdsUsersOnTestPeriod;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static tgbots.nipbot.constants.StringConstants.*;

@Service
public class HandleCommandFromVolunteerImpl implements HandleCommandFromVolunteer{

    private final Pattern msgFromVolNotice = Pattern.compile("([0-9]{6,})(\\s*)(NOTICE)");
    private final Pattern msgFromVolTestPeriodSuccess = Pattern.compile("([0-9]{6,})(\\s*)(SUCCESS)");
    private final Pattern msgFromVolTestPeriodLose = Pattern.compile("([0-9]{6,})(\\s*)(LOSE)");
    private final Pattern msgFromVolTestPeriodLong = Pattern.compile("([0-9]{6,})(\\s*)(LONG)(\\s*)(14|30)");

    private final CacheIdsUsersOnTestPeriod idsTestPeriod;

    public HandleCommandFromVolunteerImpl(CacheIdsUsersOnTestPeriod idsTestPeriod) {
        this.idsTestPeriod = idsTestPeriod;
    }

    @Override
    public BaseRequest message(Update update) {

        Long chatId = update.message().chat().id();
        String text = update.message().text();

        SendMessage sendMessage = null;

        if (text != null) {

            Matcher matcherNotice = msgFromVolNotice.matcher(text);
            Matcher matcherSuccess = msgFromVolTestPeriodSuccess.matcher(text);
            Matcher matcherLose = msgFromVolTestPeriodLose.matcher(text);
            Matcher matcherLong = msgFromVolTestPeriodLong.matcher(text);

            if (matcherNotice.matches()) {
                String id = matcherSuccess.group(1);
                Long idL = Long.parseLong(id);
                if (idsTestPeriod.containsId(idL)) {
                    sendMessage = collectSendMessage(idL, NOTICE);
                } else if (!idsTestPeriod.containsId(idL)) {
                    sendMessage = collectSendMessage(chatId, "Проверьте введенный id чата");
                }
            }

            if (matcherSuccess.matches()) {
                String id = matcherSuccess.group(1);
                Long idL = Long.parseLong(id);
                if (idsTestPeriod.containsId(idL)) {
                    sendMessage = collectSendMessage(idL, CON_USER);
                    idsTestPeriod.removeId(idL);
                } else if (!idsTestPeriod.containsId(idL)) {
                    sendMessage = collectSendMessage(chatId, "Проверьте введенный id чата");
                }
            }

            if (matcherLose.matches()) {
                String id = matcherLose.group(1);
                Long idL = Long.parseLong(id);
                if (idsTestPeriod.containsId(idL)) {
                    sendMessage = collectSendMessage(idL, LOSE);
                    idsTestPeriod.removeId(idL);
                } else if (!idsTestPeriod.containsId(idL)) {
                    sendMessage = collectSendMessage(chatId, "Проверьте введенный id чата");
                }
            }

            if (matcherLong.matches()) {
                String id = matcherLong.group(1);
                String days = matcherLong.group(5);
                Long idL = Long.parseLong(id);
                if (idsTestPeriod.containsId(idL)) {
                    sendMessage = collectSendMessage(idL, StringConstants.periodProLong(days));
                } else if (!idsTestPeriod.containsId(idL)) {
                    sendMessage = collectSendMessage(chatId, "Проверьте введенный id чата");
                }
            }

            if (!matcherNotice.matches() && !matcherSuccess.matches() && !matcherLose.matches() && !matcherLong.matches()) {
                sendMessage = collectSendMessage(chatId, "Проверьте правильность введенных данных. Будьте внимательнее при вводе команд.");
            }
        }

        return sendMessage;
    }

    private SendMessage collectSendMessage(Long chatId, String textAnswer) {
        return new SendMessage(chatId, textAnswer);
    }
}
