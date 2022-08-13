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

    public SendMessage addStartMenu(SendMessage message){
        return message.replyMarkup(createStartMenu());
    }

    /**
     * Метод добавляет главную клавиатуру
     * @param message {@link SendMessage}
     * @return сообщение с добавленной клавиатурой
     */
    public SendMessage addMainMenuDog(SendMessage message){
        return message.replyMarkup(createMainMenuDog());
    }

    /**
     * Метод добавляет info меню к сообщению
     * @param message {@link SendMessage}
     * @return сообщение с добавленной клавиатурой
     */
    public BaseRequest addInfoMenuDog(SendMessage message){
        return message.replyMarkup(createInfoMenuDog());
    }

    /**
     * Метод добавляет info меню к изменяемому сообщению
     * @param message {@link EditMessageText}
     * @return изменяемое сообщение с добавленной клавиатурой
     */
    public BaseRequest addInfoMenuDog(EditMessageText message){
        return message.replyMarkup(createInfoMenuDog());
    }

    /**
     * Метод добавляет take_dog меню к сообщению
     * @param message {@link SendMessage}
     * @return сообщение с добавленной клавиатурой
     */
    public BaseRequest addTakeMenuDog(SendMessage message){
        return message.replyMarkup(createTakeMenuDog());
    }

    /**
     * Метод добавляет take_dog меню к изменяемому сообщению
     * @param message {@link EditMessageText}
     * @return изменяемое сообщение с добавленной клавиатурой
     */
    public BaseRequest addTakeMenuDog(EditMessageText message){
        return message.replyMarkup(createTakeMenuDog());
    }

    /**
     * Метод добавляет recommendation меню к сообщению
     * @param message {@link SendMessage}
     * @return сообщение с добавленной клавиатурой
     */
    public BaseRequest addRecommendationMenuDog(SendMessage message) {
        return message.replyMarkup(createRecommendationMenuDog());
    }

    /**
     * Метод добавляет recommendation меню к изменяемому сообщению
     * @param message {@link EditMessageText}
     * @return изменяемое сообщение с добавленной клавиатурой
     */
    public BaseRequest addRecommendationMenuDog(EditMessageText message){
        return message.replyMarkup(createRecommendationMenuDog());
    }

    /**
     * Метод добавляет reports меню к сообщению
     * @param message {@link SendMessage}
     * @return сообщение с добавленной клавиатурой
     */
    public BaseRequest addReportMenuDog(SendMessage message) {
        return message.replyMarkup(createReportsMenuDog());
    }

    /**
     * Метод добавляет reports меню к изменяемому сообщению
     * @param message {@link EditMessageText}
     * @return изменяемое сообщение с добавленной клавиатурой
     */
    public BaseRequest addReportMenuDog(EditMessageText message) {
        return message.replyMarkup(createReportsMenuDog());
    }

    public SendMessage addMainMenuCat(SendMessage message){
        return message.replyMarkup(createMainMenuCat());
    }

    /**
     * Метод добавляет info меню к сообщению
     * @param message {@link SendMessage}
     * @return сообщение с добавленной клавиатурой
     */
    public BaseRequest addInfoMenuCat(SendMessage message){
        return message.replyMarkup(createInfoMenuCat());
    }

    /**
     * Метод добавляет info меню к изменяемому сообщению
     * @param message {@link EditMessageText}
     * @return изменяемое сообщение с добавленной клавиатурой
     */
    public BaseRequest addInfoMenuCat(EditMessageText message){
        return message.replyMarkup(createInfoMenuCat());
    }

    /**
     * Метод добавляет take_dog меню к сообщению
     * @param message {@link SendMessage}
     * @return сообщение с добавленной клавиатурой
     */
    public BaseRequest addTakeMenuCat(SendMessage message){
        return message.replyMarkup(createTakeMenuCat());
    }

    /**
     * Метод добавляет take_dog меню к изменяемому сообщению
     * @param message {@link EditMessageText}
     * @return изменяемое сообщение с добавленной клавиатурой
     */
    public BaseRequest addTakeMenuCat(EditMessageText message){
        return message.replyMarkup(createTakeMenuCat());
    }

    /**
     * Метод добавляет recommendation меню к сообщению
     * @param message {@link SendMessage}
     * @return сообщение с добавленной клавиатурой
     */
    public BaseRequest addRecommendationMenuCat(SendMessage message) {
        return message.replyMarkup(createRecommendationMenuCat());
    }

    /**
     * Метод добавляет recommendation меню к изменяемому сообщению
     * @param message {@link EditMessageText}
     * @return изменяемое сообщение с добавленной клавиатурой
     */
    public BaseRequest addRecommendationMenuCat(EditMessageText message){
        return message.replyMarkup(createRecommendationMenuCat());
    }

    /**
     * Метод добавляет reports меню к сообщению
     * @param message {@link SendMessage}
     * @return сообщение с добавленной клавиатурой
     */
    public BaseRequest addReportMenuCat(SendMessage message) {
        return message.replyMarkup(createReportsMenuCat());
    }

    /**
     * Метод добавляет reports меню к изменяемому сообщению
     * @param message {@link EditMessageText}
     * @return изменяемое сообщение с добавленной клавиатурой
     */
    public BaseRequest addReportMenuCat(EditMessageText message) {
        return message.replyMarkup(createReportsMenuCat());
    }

    private Keyboard createStartMenu(){
        return new ReplyKeyboardMarkup(
                        new KeyboardButton(DOG_SHELTER.getTextButton()))
                .addRow(new KeyboardButton(CAT_SHELTER.getTextButton()))
                .oneTimeKeyboard(true);
    }

    private Keyboard createMainMenuDog(){
        return new ReplyKeyboardMarkup(
                        new KeyboardButton(DOG_INFO_ABOUT_SHELTER.getTextButton()))
                .addRow(new KeyboardButton(DOG_HOW_TAKE_FROM_SHELTER.getTextButton()))
                .addRow(new KeyboardButton(DOG_SEND_PET_REPORT.getTextButton()))
                .addRow(new KeyboardButton(DOG_CALL_VOLUNTEER.getTextButton()))
                .oneTimeKeyboard(true);
    }

    private InlineKeyboardMarkup createInfoMenuDog(){
        return new InlineKeyboardMarkup(
                        new InlineKeyboardButton(DOG_ABOUT_SHELTER.getTextButton()).callbackData(DOG_ABOUT_SHELTER.name()))
                .addRow(new InlineKeyboardButton(DOG_WORK_SCHEDULE.getTextButton()).callbackData(DOG_WORK_SCHEDULE.name()))
                .addRow(new InlineKeyboardButton(DOG_GENERAL_SAFETY_RECOMMENDATIONS.getTextButton()).callbackData(DOG_GENERAL_SAFETY_RECOMMENDATIONS.name()))
                .addRow(new InlineKeyboardButton(DOG_CONTACT_DETAILS_FOR_COMMUNICATION.getTextButton()).callbackData(DOG_CONTACT_DETAILS_FOR_COMMUNICATION.name()))
                .addRow(new InlineKeyboardButton(DOG_CALL_VOLUNTEER.getTextButton()).callbackData(DOG_CALL_VOLUNTEER.name()));
    }

    private InlineKeyboardMarkup createTakeMenuDog(){
        return new InlineKeyboardMarkup(
                        new InlineKeyboardButton(DOG_DATING_RULES.getTextButton()).callbackData(DOG_DATING_RULES.name()))
                .addRow(new InlineKeyboardButton(DOG_LIST_OF_DOCUMENTS.getTextButton()).callbackData(DOG_LIST_OF_DOCUMENTS.name()))
                .addRow(new InlineKeyboardButton(DOG_LIST_RECOMMENDATIONS.getTextButton()).callbackData(DOG_LIST_RECOMMENDATIONS.name()))
                .addRow(new InlineKeyboardButton(DOG_TIPS_FROM_DOG_HANDLER.getTextButton()).callbackData(DOG_TIPS_FROM_DOG_HANDLER.name()))
                .addRow(new InlineKeyboardButton(DOG_PROVEN_DOG_HANDLERS.getTextButton()).callbackData(DOG_PROVEN_DOG_HANDLERS.name()))
                .addRow(new InlineKeyboardButton(DOG_LIST_REASONS_FOR_REFUSAL.getTextButton()).callbackData(DOG_LIST_REASONS_FOR_REFUSAL.name()))
                .addRow(new InlineKeyboardButton(DOG_CONTACT_DETAILS_FOR_COMMUNICATION.getTextButton()).callbackData(DOG_CONTACT_DETAILS_FOR_COMMUNICATION.name()))
                .addRow(new InlineKeyboardButton(DOG_CALL_VOLUNTEER.getTextButton()).callbackData(DOG_CALL_VOLUNTEER.name()));
    }

    private InlineKeyboardMarkup createRecommendationMenuDog(){
        return new InlineKeyboardMarkup(
                        new InlineKeyboardButton(DOG_LIST_RECOMMENDATIONS_TRANSPORTATION.getTextButton()).callbackData(DOG_LIST_RECOMMENDATIONS_TRANSPORTATION.name()))
                .addRow(new InlineKeyboardButton(DOG_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_PUPPY.getTextButton()).callbackData(DOG_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_PUPPY.name()))
                .addRow(new InlineKeyboardButton(DOG_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_DOG.getTextButton()).callbackData(DOG_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_DOG.name()))
                .addRow(new InlineKeyboardButton(DOG_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_DOG_WITH_DISABILITIES.getTextButton()).callbackData(DOG_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_DOG_WITH_DISABILITIES.name()))
                .addRow(new InlineKeyboardButton(DOG_BACK_TO_DOG_HOW_TAKE_FROM_SHELTER.getTextButton()).callbackData(DOG_BACK_TO_DOG_HOW_TAKE_FROM_SHELTER.name()));
    }

    private InlineKeyboardMarkup createReportsMenuDog(){
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton(DOG_DAILY_REPORT_FORM.getTextButton()).callbackData(DOG_DAILY_REPORT_FORM.name()))
                .addRow(new InlineKeyboardButton(DOG_PROBATION_PERIOD.getTextButton()).callbackData(DOG_PROBATION_PERIOD.name()))
                .addRow(new InlineKeyboardButton(DOG_CALL_VOLUNTEER.getTextButton()).callbackData(DOG_CALL_VOLUNTEER.name()));
    }

    private Keyboard createMainMenuCat(){
        return new ReplyKeyboardMarkup(
                new KeyboardButton(CAT_INFO_ABOUT_SHELTER.getTextButton()))
                .addRow(new KeyboardButton(CAT_HOW_TAKE_FROM_SHELTER.getTextButton()))
                .addRow(new KeyboardButton(CAT_SEND_PET_REPORT.getTextButton()))
                .addRow(new KeyboardButton(CAT_CALL_VOLUNTEER.getTextButton()))
                .oneTimeKeyboard(true);
    }

    private InlineKeyboardMarkup createInfoMenuCat(){
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton(CAT_ABOUT_SHELTER.getTextButton()).callbackData(CAT_ABOUT_SHELTER.name()))
                .addRow(new InlineKeyboardButton(CAT_WORK_SCHEDULE.getTextButton()).callbackData(CAT_WORK_SCHEDULE.name()))
                .addRow(new InlineKeyboardButton(CAT_GENERAL_SAFETY_RECOMMENDATIONS.getTextButton()).callbackData(CAT_GENERAL_SAFETY_RECOMMENDATIONS.name()))
                .addRow(new InlineKeyboardButton(CAT_CONTACT_DETAILS_FOR_COMMUNICATION.getTextButton()).callbackData(CAT_CONTACT_DETAILS_FOR_COMMUNICATION.name()))
                .addRow(new InlineKeyboardButton(CAT_CALL_VOLUNTEER.getTextButton()).callbackData(CAT_CALL_VOLUNTEER.name()));
    }

    private InlineKeyboardMarkup createTakeMenuCat(){
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton(CAT_DATING_RULES.getTextButton()).callbackData(CAT_DATING_RULES.name()))
                .addRow(new InlineKeyboardButton(CAT_LIST_OF_DOCUMENTS.getTextButton()).callbackData(CAT_LIST_OF_DOCUMENTS.name()))
                .addRow(new InlineKeyboardButton(CAT_LIST_RECOMMENDATIONS.getTextButton()).callbackData(CAT_LIST_RECOMMENDATIONS.name()))
                .addRow(new InlineKeyboardButton(CAT_LIST_REASONS_FOR_REFUSAL.getTextButton()).callbackData(CAT_LIST_REASONS_FOR_REFUSAL.name()))
                .addRow(new InlineKeyboardButton(CAT_CONTACT_DETAILS_FOR_COMMUNICATION.getTextButton()).callbackData(CAT_CONTACT_DETAILS_FOR_COMMUNICATION.name()))
                .addRow(new InlineKeyboardButton(CAT_CALL_VOLUNTEER.getTextButton()).callbackData(CAT_CALL_VOLUNTEER.name()));
    }

    private InlineKeyboardMarkup createRecommendationMenuCat(){
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton(CAT_LIST_RECOMMENDATIONS_TRANSPORTATION.getTextButton()).callbackData(CAT_LIST_RECOMMENDATIONS_TRANSPORTATION.name()))
                .addRow(new InlineKeyboardButton(CAT_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_PUPPY.getTextButton()).callbackData(CAT_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_PUPPY.name()))
                .addRow(new InlineKeyboardButton(CAT_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_CAT.getTextButton()).callbackData(CAT_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_CAT.name()))
                .addRow(new InlineKeyboardButton(CAT_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_CAT_WITH_DISABILITIES.getTextButton()).callbackData(CAT_LIST_RECOMMENDATIONS_HOME_IMPROVEMENT_CAT_WITH_DISABILITIES.name()))
                .addRow(new InlineKeyboardButton(CAT_BACK_TO_CAT_HOW_TAKE_FROM_SHELTER.getTextButton()).callbackData(CAT_BACK_TO_CAT_HOW_TAKE_FROM_SHELTER.name()));
    }

    private InlineKeyboardMarkup createReportsMenuCat(){
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton(CAT_DAILY_REPORT_FORM.getTextButton()).callbackData(CAT_DAILY_REPORT_FORM.name()))
                .addRow(new InlineKeyboardButton(CAT_PROBATION_PERIOD.getTextButton()).callbackData(CAT_PROBATION_PERIOD.name()))
                .addRow(new InlineKeyboardButton(CAT_CALL_VOLUNTEER.getTextButton()).callbackData(CAT_CALL_VOLUNTEER.name()));
    }
}
