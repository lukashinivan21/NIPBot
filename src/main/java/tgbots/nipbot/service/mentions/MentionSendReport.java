package tgbots.nipbot.service.mentions;

import java.util.List;

public interface MentionSendReport {

    /**
     * Метод возвращает список id пользователей, которые не присылают
     * отчеты более одного дня, находясь на испытательном сроке
     * @return {@link List<Long>}
     */
    List<Long> idsForMentionToSendReport();
}
