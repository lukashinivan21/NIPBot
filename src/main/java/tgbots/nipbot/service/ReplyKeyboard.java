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
        return message.replyMarkup(createMainMenu());
    }

    /**
     * Метод добавляет info меню к сообщению
     * @param message {@link SendMessage}
     * @return сообщение с добавленной клавиатурой
     */
    public BaseRequest addInfoMenu(SendMessage message){
        return message.replyMarkup(createInfoMenu());
    }

    /**
     * Метод добавляет info меню к изменяемому сообщению
     * @param message {@link EditMessageText}
     * @return изменяемое сообщение с добавленной клавиатурой
     */
    public BaseRequest addInfoMenu(EditMessageText message){
        return message.replyMarkup(createInfoMenu());
    }

    /**
     * Метод добавляет take_dog меню к сообщению
     * @param message {@link SendMessage}
     * @return сообщение с добавленной клавиатурой
     */
    public BaseRequest addTakeDogMenu(SendMessage message){
        return message.replyMarkup(createTakeDogMenu());
    }

    /**
     * Метод добавляет take_dog меню к изменяемому сообщению
     * @param message {@link EditMessageText}
     * @return изменяемое сообщение с добавленной клавиатурой
     */
    public BaseRequest addTakeDogMenu(EditMessageText message){
        return message.replyMarkup(createTakeDogMenu());
    }

    /**
     * Метод добавляет recommendation меню к сообщению
     * @param message {@link SendMessage}
     * @return сообщение с добавленной клавиатурой
     */
    public BaseRequest addRecommendationMenu(SendMessage message) {
        return message.replyMarkup(createRecommendationMenu());
    }

    /**
     * Метод добавляет recommendation меню к изменяемому сообщению
     * @param message {@link EditMessageText}
     * @return изменяемое сообщение с добавленной клавиатурой
     */
    public BaseRequest addRecommendationMenu(EditMessageText message){
        return message.replyMarkup(createRecommendationMenu());
    }

    /**
     * Метод добавляет reports меню к сообщению
     * @param message {@link SendMessage}
     * @return сообщение с добавленной клавиатурой
     */
    public BaseRequest addReportMenu(SendMessage message) {
        return message.replyMarkup(createReportsMenu());
    }

    /**
     * Метод добавляет reports меню к изменяемому сообщению
     * @param message {@link EditMessageText}
     * @return изменяемое сообщение с добавленной клавиатурой
     */
    public BaseRequest addReportMenu(EditMessageText message) {
        return message.replyMarkup(createReportsMenu());
    }

    private Keyboard createMainMenu(){
        return new ReplyKeyboardMarkup(
                        new KeyboardButton(INFO_ABOUT_DOG_SHELTER.getTextButton()))
                .addRow(new KeyboardButton(HOW_TAKE_DOG_FROM_SHELTER.getTextButton()))
                .addRow(new KeyboardButton(SEND_PET_REPORT.getTextButton()))
                .addRow(new KeyboardButton(CALL_VOLUNTEER.getTextButton()))
                .oneTimeKeyboard(true);
    }

    private InlineKeyboardMarkup createInfoMenu(){
        return new InlineKeyboardMarkup(
                        new InlineKeyboardButton(ABOUT_SHELTER.getTextButton()).callbackData(ABOUT_SHELTER.name()))
                .addRow(new InlineKeyboardButton(WORK_SCHEDULE.getTextButton()).callbackData(WORK_SCHEDULE.name()))
                .addRow(new InlineKeyboardButton(GENERAL_SAFETY_RECOMMENDATIONS.getTextButton()).callbackData(GENERAL_SAFETY_RECOMMENDATIONS.name()))
                .addRow(new InlineKeyboardButton(CONTACT_DETAILS_FOR_COMMUNICATION.getTextButton()).callbackData(CONTACT_DETAILS_FOR_COMMUNICATION.name()))
                .addRow(new InlineKeyboardButton(CALL_VOLUNTEER.getTextButton()).callbackData(CALL_VOLUNTEER.name()));
    }

    private InlineKeyboardMarkup createTakeDogMenu(){
        return new InlineKeyboardMarkup(
                        new InlineKeyboardButton(DATING_RULES.getTextButton()).callbackData(DATING_RULES.name()))
                .addRow(new InlineKeyboardButton(LIST_OF_DOCUMENTS.getTextButton()).callbackData(LIST_OF_DOCUMENTS.name()))
                .addRow(new InlineKeyboardButton(LIST_RECOMMENDATIONS.getTextButton()).callbackData(LIST_RECOMMENDATIONS.name()))
                .addRow(new InlineKeyboardButton(TIPS_FROM_DOG_HANDLER.getTextButton()).callbackData(TIPS_FROM_DOG_HANDLER.name()))
                .addRow(new InlineKeyboardButton(PROVEN_DOG_HANDLERS.getTextButton()).callbackData(PROVEN_DOG_HANDLERS.name()))
                .addRow(new InlineKeyboardButton(LIST_REASONS_FOR_REFUSAL.getTextButton()).callbackData(LIST_REASONS_FOR_REFUSAL.name()))
                .addRow(new InlineKeyboardButton(CONTACT_DETAILS_FOR_COMMUNICATION.getTextButton()).callbackData(CONTACT_DETAILS_FOR_COMMUNICATION.name()))
                .addRow(new InlineKeyboardButton(CALL_VOLUNTEER.getTextButton()).callbackData(CALL_VOLUNTEER.name()));
    }

    private InlineKeyboardMarkup createRecommendationMenu(){
        return new InlineKeyboardMarkup(
                        new InlineKeyboardButton(LIST_RECOMMENDATIONS_TRANSPORTATION.getTextButton()).callbackData(LIST_RECOMMENDATIONS_TRANSPORTATION.name()))
                .addRow(new InlineKeyboardButton(LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_PUPPY.getTextButton()).callbackData(LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_PUPPY.name()))
                .addRow(new InlineKeyboardButton(LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_DOG.getTextButton()).callbackData(LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_DOG.name()))
                .addRow(new InlineKeyboardButton(LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_DOG_WITH_DISABILITIES.getTextButton()).callbackData(LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_DOG_WITH_DISABILITIES.name()))
                .addRow(new InlineKeyboardButton(BACK_TO_HOW_TAKE_DOG_FROM_SHELTER.getTextButton()).callbackData(BACK_TO_HOW_TAKE_DOG_FROM_SHELTER.name()));
    }

    private InlineKeyboardMarkup createReportsMenu(){
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton(DAILY_REPORT_FORM.getTextButton()).callbackData(DAILY_REPORT_FORM.name()))
                .addRow(new InlineKeyboardButton(PROBATION_PERIOD.getTextButton()).callbackData(PROBATION_PERIOD.name()))
                .addRow(new InlineKeyboardButton(CALL_VOLUNTEER.getTextButton()).callbackData(CALL_VOLUNTEER.name()));
    }
}
