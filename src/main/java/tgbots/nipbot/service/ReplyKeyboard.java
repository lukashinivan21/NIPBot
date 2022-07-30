package tgbots.nipbot.service;

import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.stereotype.Service;
import tgbots.nipbot.constants.TextForButtons;

@Service
public class ReplyKeyboard {

    public SendMessage addMainMenu(SendMessage message){
        Keyboard keyboard = new ReplyKeyboardMarkup(
                        new KeyboardButton(TextForButtons.INFO_ABOUT_DOG_SHELTER.getTextButton()),
                        new KeyboardButton(TextForButtons.HOW_TAKE_DOG_FROM_SHELTER.getTextButton()))
                .addRow(new KeyboardButton(TextForButtons.SEND_PET_REPORT.getTextButton()),
                        new KeyboardButton(TextForButtons.CALL_VOLUNTEER.getTextButton()))
                .oneTimeKeyboard(true);

        return message.replyMarkup(keyboard);
    }

    public BaseRequest addInfoMenu(SendMessage message){
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(
                        new InlineKeyboardButton(TextForButtons.ABOUT_SHELTER.getTextButton()).callbackData(TextForButtons.ABOUT_SHELTER.name()))
                .addRow(new InlineKeyboardButton(TextForButtons.WORK_SCHEDULE.getTextButton()).callbackData(TextForButtons.WORK_SCHEDULE.name()))
                .addRow(new InlineKeyboardButton(TextForButtons.GENERAL_SAFETY_RECOMMENDATIONS.getTextButton()).callbackData(TextForButtons.GENERAL_SAFETY_RECOMMENDATIONS.name()))
                .addRow(new InlineKeyboardButton(TextForButtons.CONTACT_DETAILS_FOR_COMMUNICATION.getTextButton()).callbackData(TextForButtons.CONTACT_DETAILS_FOR_COMMUNICATION.name()))
                .addRow(new InlineKeyboardButton(TextForButtons.CALL_VOLUNTEER.getTextButton()).callbackData(TextForButtons.CALL_VOLUNTEER.name()));
        return message.replyMarkup(keyboard);
    }

    public BaseRequest addInfoMenu(EditMessageText message){
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(
                new InlineKeyboardButton(TextForButtons.ABOUT_SHELTER.getTextButton()).callbackData(TextForButtons.ABOUT_SHELTER.name()))
                .addRow(new InlineKeyboardButton(TextForButtons.WORK_SCHEDULE.getTextButton()).callbackData(TextForButtons.WORK_SCHEDULE.name()))
                .addRow(new InlineKeyboardButton(TextForButtons.GENERAL_SAFETY_RECOMMENDATIONS.getTextButton()).callbackData(TextForButtons.GENERAL_SAFETY_RECOMMENDATIONS.name()))
                .addRow(new InlineKeyboardButton(TextForButtons.CONTACT_DETAILS_FOR_COMMUNICATION.getTextButton()).callbackData(TextForButtons.CONTACT_DETAILS_FOR_COMMUNICATION.name()))
                .addRow(new InlineKeyboardButton(TextForButtons.CALL_VOLUNTEER.getTextButton()).callbackData(TextForButtons.CALL_VOLUNTEER.name()));
        return message.replyMarkup(keyboard);
    }
}
