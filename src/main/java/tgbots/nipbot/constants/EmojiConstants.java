package tgbots.nipbot.constants;

import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Repository;

/**
 * Класс, содержащий эмодзи и стикеры, используемые для общения бота и пользователя
 */
@Repository
public class EmojiConstants {

    /**
     * Эмодзи "Белая галочка в зеленом квадратике"
     */
    public static final String CHECK = EmojiParser.parseToUnicode(":white_check_mark:");

}
