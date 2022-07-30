package tgbots.nipbot.service;

import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

import static tgbots.nipbot.constants.TextForButtons.*;

/**
 * Класс, содержащий логику добавления определенной клавиатуры к сообщению
 */
@Service
public class ReplyKeyboard {

    /**
     * Метод добавляет главную клавиатуру
     * @param message {@link SendMessage}
     * @return сообщение с добавленной клавиатурой
     */
    public SendMessage addMainMenu(SendMessage message){
        Keyboard keyboard = new ReplyKeyboardMarkup(
                        new KeyboardButton(INFO_ABOUT_DOG_SHELTER.getTextButton()))
                .addRow(new KeyboardButton(HOW_TAKE_DOG_FROM_SHELTER.getTextButton()))
                .addRow(new KeyboardButton(SEND_PET_REPORT.getTextButton()))
                .addRow(new KeyboardButton(CALL_VOLUNTEER.getTextButton()))
                .oneTimeKeyboard(true);

        return message.replyMarkup(keyboard);
    }

    /**
     * Метод добавляет info меню к сообщению
     * @param message {@link SendMessage}
     * @return сообщение с добавленной клавиатурой
     */
    public BaseRequest addInfoMenu(SendMessage message){
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(
                        new InlineKeyboardButton(ABOUT_SHELTER.getTextButton()).callbackData(ABOUT_SHELTER.name()))
                .addRow(new InlineKeyboardButton(WORK_SCHEDULE.getTextButton()).callbackData(WORK_SCHEDULE.name()))
                .addRow(new InlineKeyboardButton(GENERAL_SAFETY_RECOMMENDATIONS.getTextButton()).callbackData(GENERAL_SAFETY_RECOMMENDATIONS.name()))
                .addRow(new InlineKeyboardButton(CONTACT_DETAILS_FOR_COMMUNICATION.getTextButton()).callbackData(CONTACT_DETAILS_FOR_COMMUNICATION.name()))
                .addRow(new InlineKeyboardButton(CALL_VOLUNTEER.getTextButton()).callbackData(CALL_VOLUNTEER.name()));
        return message.replyMarkup(keyboard);
    }

    /**
     * Метод добавляет info меню к изменяемому сообщению
     * @param message {@link EditMessageText}
     * @return изменяемое сообщение с добавленной клавиатурой
     */
    public BaseRequest addInfoMenu(EditMessageText message){
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(
                        new InlineKeyboardButton(ABOUT_SHELTER.getTextButton()).callbackData(ABOUT_SHELTER.name()))
                .addRow(new InlineKeyboardButton(WORK_SCHEDULE.getTextButton()).callbackData(WORK_SCHEDULE.name()))
                .addRow(new InlineKeyboardButton(GENERAL_SAFETY_RECOMMENDATIONS.getTextButton()).callbackData(GENERAL_SAFETY_RECOMMENDATIONS.name()))
                .addRow(new InlineKeyboardButton(CONTACT_DETAILS_FOR_COMMUNICATION.getTextButton()).callbackData(CONTACT_DETAILS_FOR_COMMUNICATION.name()))
                .addRow(new InlineKeyboardButton(CALL_VOLUNTEER.getTextButton()).callbackData(CALL_VOLUNTEER.name()));
        return message.replyMarkup(keyboard);
    }
}
