package tgbots.nipbot.constants;

import org.springframework.stereotype.Repository;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static tgbots.nipbot.constants.EmojiConstants.CHECK;
import static tgbots.nipbot.constants.StringConstants.*;

/**
 * Класс, содержащий клавиатуры, используемые для общения бота с пользователем
 * Используются строковые константы из класса {@link StringConstants} и эмодзи из класса {@link EmojiConstants}
 */
@Repository
public class Keyboards {

    /**
     * Клавиатура главного меню при первом обращении пользователя к боту
     */
    public static ReplyKeyboardMarkup keyboard1() {

        ReplyKeyboardMarkup first = new ReplyKeyboardMarkup();

        first.setSelective(true);
        first.setResizeKeyboard(true);
        first.setOneTimeKeyboard(false);

        InlineKeyboardButton button1 = new InlineKeyboardButton(TEXT_BUTTON1);
        InlineKeyboardButton button2 = new InlineKeyboardButton(TEXT_BUTTON2);
        InlineKeyboardButton button3 = new InlineKeyboardButton(TEXT_BUTTON3);
        InlineKeyboardButton button4 = new InlineKeyboardButton(TEXT_BUTTON4);

        KeyboardRow row1 = new KeyboardRow();
        row1.add(button1.getText());

        KeyboardRow row2 = new KeyboardRow();
        row2.add(button2.getText());

        KeyboardRow row3 = new KeyboardRow();
        row3.add(button3.getText());

        KeyboardRow row4 = new KeyboardRow();
        row4.add(button4.getText());

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(row1);
        keyboardRows.add(row2);
        keyboardRows.add(row3);
        keyboardRows.add(row4);

        first.setKeyboard(keyboardRows);

        return first;
    }

    /**
     * Клавиатура для проведения первой консультации с новым пользователем
     */
    public static InlineKeyboardMarkup keyboard2() {

        InlineKeyboardMarkup second = new InlineKeyboardMarkup();

        InlineKeyboardButton button5 = new InlineKeyboardButton(CHECK + " " + TEXT_BUTTON5);
        InlineKeyboardButton button6 = new InlineKeyboardButton(CHECK + " " + TEXT_BUTTON6);
        InlineKeyboardButton button7 = new InlineKeyboardButton(CHECK + " " + TEXT_BUTTON7);
        InlineKeyboardButton button8 = new InlineKeyboardButton(CHECK + " " + TEXT_BUTTON8);
        InlineKeyboardButton button9 = new InlineKeyboardButton(CHECK + " " + TEXT_BUTTON4);

        button5.setCallbackData(CALLBACK_BUTTON5);
        button6.setCallbackData(CALLBACK_BUTTON6);
        button7.setCallbackData(CALLBACK_BUTTON7);
        button8.setCallbackData(CALLBACK_BUTTON8);
        button9.setCallbackData(CALLBACK_BUTTON4);

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(button5);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(button6);

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(button7);

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(button8);

        List<InlineKeyboardButton> row5 = new ArrayList<>();
        row5.add(button9);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);
        rowList.add(row4);
        rowList.add(row5);

        second.setKeyboard(rowList);

        return second;
    }

    /**
     * Клавиатура для проведения подробной консультации с потенциальным новым владельцем собаки
     */
    public static InlineKeyboardMarkup keyboard3() {

        InlineKeyboardMarkup third = new InlineKeyboardMarkup();

        InlineKeyboardButton button9 = new InlineKeyboardButton(CHECK + " " + TEXT_BUTTON9);
        InlineKeyboardButton button10 = new InlineKeyboardButton(CHECK + " " + TEXT_BUTTON10);
        InlineKeyboardButton button11 = new InlineKeyboardButton(CHECK + " " + TEXT_BUTTON11);
        InlineKeyboardButton button12 = new InlineKeyboardButton(CHECK + " " + TEXT_BUTTON12);
        InlineKeyboardButton button13 = new InlineKeyboardButton(CHECK + " " + TEXT_BUTTON13);
        InlineKeyboardButton button14 = new InlineKeyboardButton(CHECK + " " + TEXT_BUTTON14);
        InlineKeyboardButton button15 = new InlineKeyboardButton(CHECK + " " + TEXT_BUTTON15);
        InlineKeyboardButton button16 = new InlineKeyboardButton(CHECK + " " + TEXT_BUTTON16);
        InlineKeyboardButton button17 = new InlineKeyboardButton(CHECK + " " + TEXT_BUTTON17);
        InlineKeyboardButton button18 = new InlineKeyboardButton(CHECK + " " + TEXT_BUTTON8);
        InlineKeyboardButton button19 = new InlineKeyboardButton(CHECK + " " + TEXT_BUTTON4);

        button9.setCallbackData(CALLBACK_BUTTON9);
        button10.setCallbackData(CALLBACK_BUTTON10);
        button11.setCallbackData(CALLBACK_BUTTON11);
        button12.setCallbackData(CALLBACK_BUTTON12);
        button13.setCallbackData(CALLBACK_BUTTON13);
        button14.setCallbackData(CALLBACK_BUTTON14);
        button15.setCallbackData(CALLBACK_BUTTON15);
        button16.setCallbackData(CALLBACK_BUTTON16);
        button17.setCallbackData(CALLBACK_BUTTON17);
        button18.setCallbackData(CALLBACK_BUTTON8);
        button19.setCallbackData(CALLBACK_BUTTON4);

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(button9);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(button10);

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(button11);

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(button12);

        List<InlineKeyboardButton> row5 = new ArrayList<>();
        row5.add(button13);

        List<InlineKeyboardButton> row6 = new ArrayList<>();
        row6.add(button14);

        List<InlineKeyboardButton> row7 = new ArrayList<>();
        row7.add(button15);

        List<InlineKeyboardButton> row8 = new ArrayList<>();
        row8.add(button16);

        List<InlineKeyboardButton> row9 = new ArrayList<>();
        row9.add(button17);

        List<InlineKeyboardButton> row10 = new ArrayList<>();
        row10.add(button18);

        List<InlineKeyboardButton> row11 = new ArrayList<>();
        row11.add(button19);

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);
        rows.add(row5);
        rows.add(row6);
        rows.add(row7);
        rows.add(row8);
        rows.add(row9);
        rows.add(row10);
        rows.add(row11);

        third.setKeyboard(rows);

        return third;
    }

    /**
     * Клавиатура, содержащая подсказки для правильного заполнения и отправки пользователем ежедневного отчета
     */
    public static InlineKeyboardMarkup keyboard4() {

        InlineKeyboardMarkup forth = new InlineKeyboardMarkup();

        InlineKeyboardButton button20 = new InlineKeyboardButton(CHECK + " " + TEXT_BUTTON18);
        InlineKeyboardButton button21 = new InlineKeyboardButton(CHECK + " " + TEXT_BUTTON19);

        button20.setCallbackData(CALLBACK_BUTTON18);
        button21.setCallbackData(CALLBACK_BUTTON19);

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(button20);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(button21);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(row1);
        rowList.add(row2);

        forth.setKeyboard(rowList);

        return forth;
    }

}
