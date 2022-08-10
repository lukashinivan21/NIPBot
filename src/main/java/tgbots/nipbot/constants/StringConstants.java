package tgbots.nipbot.constants;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StringConstants {

    public static final String START = "/start";

//текст первого привественного сообщения, когда пользователь впервые обращается к боту
    public static final String MAIN_GREETING = "Привет, пользователь. Я помогу тебе подобрать самого веселого и замечательного щенка из нашего питомника." +
            " Выбери один из пунктов главного меню";

//    текст сообщения в ситуациях по умолчанию
    public static final String MESS_DEFAULT = "Воспользуйтесь главным меню";

//    callback от кнопки "Позвать волонтера" и текст ответного сообщения
    public static final String CALLBACK_BUTTON4 = "Волонтер";
    public static final String MESS_FOR_BUTTON4 = "Волонтер подключиться к чату в ближайшее время...";

//    текст первой кнопки главного меню
    public static final String TEXT_BUTTON1 = "Узнать информацию о приюте";

//    текст второй кнопки главного меню
    public static final String TEXT_BUTTON2 = "Как взять собаку из приюта";

//    текст третьей кнопки главного меню и текст ответного сообщения пр нажатии на неё
    public static final String TEXT_BUTTON3 = "Прислать отчет о питомце";
    public static final String MESS_FOR_BUTTON3 = "Выберите один из пунктов меню";

//   текст четвертой кнопки главного меню
    public static final String TEXT_BUTTON4 = "Позвать волонтера";

//    текст привественного сообщения после нажатия первой кнопки главного меню
    public static final String GREETING_STEP1 = "Привет, пользователь. В меню ниже ты можешь узнать информацию о приюте. Выбери один из пунктов...";

//    текст привественного сообщения после нажатия второй кнопки главного меню
    public static final String GREETING_STEP2 = "Привет, пользователь. Меню ниже поможет тебе получить ответы на наиболее популярные вопросы. Выбери один из пунктов...";

//    Ниже представлены тексты всех кнопок, которые используются при общении бота с пользователем, callback от этих кнопок и тексты ответных сообщений для каждой кнопки
    public static final String TEXT_BUTTON5 = "Общая информация о приюте";
    public static final String CALLBACK_BUTTON5 = "Инфо";
    public static final String MESS_FOR_BUTTON5 = "Пользователь, в файле ты найдешь полную информацию о нашем приюте.";

    public static final String TEXT_BUTTON6 = "Расписание работы и адрес приюта";
    public static final String CALLBACK_BUTTON6 = "Адрес";
    public static final String MESS_FOR_BUTTON6 = "Пользователь, в файле ты найдешь информацию о графике работы приюта, адрес и номер телефона";

    public static final String TEXT_BUTTON7 = "Техника безопасности на территории приюта";
    public static final String CALLBACK_BUTTON7 = "Рекомендация";
    public static final String MESS_FOR_BUTTON7 = "Пользователь, в файле ты найдешь необходимые рекомендации о технике безопасности на территории приюта.";

    public static final String TEXT_BUTTON8 = "Могу записать ваши контактные данные для связи";
    public static final String CALLBACK_BUTTON8 = "Контакт";
    public static final String MESS_FOR_BUTTON8 = "В ответном сообщении введи свои контакты в виде: \"+7xxxxxxxxxx ФИО e-mail\", где x - цифра от 0 до 9. Ответное сообщение без кавычек." +
            " Номер телефона и ФИО обязательны для заполнения. ФИО может содержать фамилию и имя или же только имя";

    public static final String TEXT_BUTTON9 = "Правила знакомства с собакой";
    public static final String CALLBACK_BUTTON9 = "Знакомство";
    public static final String MESS_FOR_BUTTON9 = "В файле находятся правила успешного знакомства с собакой";

    public static final String TEXT_BUTTON10 = "Список необходимых документов";
    public static final String CALLBACK_BUTTON10 = "Список";
    public static final String MESS_FOR_BUTTON10 = "В файле находится перечень документов, необходимых для того, чтобы забрать собаку из приюта";

    public static final String TEXT_BUTTON11 = "Рекомендации по транспортировке животного";
    public static final String CALLBACK_BUTTON11 = "Транспорт";
    public static final String MESS_FOR_BUTTON11 = "В файле находятся рекомендации для безопасной транспортировки животного";

    public static final String TEXT_BUTTON12 = "Рекомендации по обустройству дома для щенка";
    public static final String CALLBACK_BUTTON12 = "Щенок";
    public static final String MESS_FOR_BUTTON12 = "В файле находятся рекомендации по обустройству дома для щенка";

    public static final String TEXT_BUTTON13 = "Рекомендации по обустройству дома взрослой собаки";
    public static final String CALLBACK_BUTTON13 = "Взрослая";
    public static final String MESS_FOR_BUTTON13 = "В файле находятся рекомендации по обустройству дома для взрослой собаки";

    public static final String TEXT_BUTTON14 = "Рекомендации по обустройству дома для собаки с ограниченными возможностями";
    public static final String CALLBACK_BUTTON14 = "Обустройство";
    public static final String MESS_FOR_BUTTON14 = "В файле находятся рекомендации по обустройству дома для собаки с ограниченными возможностями";

    public static final String TEXT_BUTTON15 = "Советы кинолога по первичному общению с собакой";
    public static final String CALLBACK_BUTTON15 = "Советы";
    public static final String MESS_FOR_BUTTON15 = "В файле находятся рекомендации кинологов для успешного первичного знакомства с собакой";

    public static final String TEXT_BUTTON16 = "Список проверенных кинологов для дальнейших консультаций";
    public static final String CALLBACK_BUTTON16 = "Кинологи";
    public static final String MESS_FOR_BUTTON16 = "В файле находится список кинологов, проверенных сотрудниками нашего питомника";

    public static final String TEXT_BUTTON17 = "Причины отказа в заборе собаки из приюта";
    public static final String CALLBACK_BUTTON17 = "Отказ";
    public static final String MESS_FOR_BUTTON17 = "В файле находятся основные причины отказа в заборе собаки из приюта";

    public static final String TEXT_BUTTON18 = "Форма ежедневного отчета";
    public static final String CALLBACK_BUTTON18 = "Форма";
    public static final String MESS_FOR_BUTTON18 = "Изучите форму отчета. Это поможет вам грамотно заполнить отчет";

    public static final String TEXT_BUTTON19 = "Инструкция по заполнению и отправки отчета";
    public static final String CALLBACK_BUTTON19 = "Заполнение";
    public static final String MESS_FOR_BUTTON19 = "Внимательно ознакомьтесь с инструкцией перед тем, как заполнять и отправлять отчет";

//    массив, содержащий callback от кнопок, при нажатии на которую пользователю отправляется файл, содержащий информацию, в формате JPEG или PDF
    private static final String[] callBacks = {CALLBACK_BUTTON5, CALLBACK_BUTTON6, CALLBACK_BUTTON7, CALLBACK_BUTTON9, CALLBACK_BUTTON10, CALLBACK_BUTTON11, CALLBACK_BUTTON12,
            CALLBACK_BUTTON13, CALLBACK_BUTTON14, CALLBACK_BUTTON15, CALLBACK_BUTTON16, CALLBACK_BUTTON17, CALLBACK_BUTTON18, CALLBACK_BUTTON19};

//    лист callback из массива выше, для удобства дальнейшей работы с ним
    public static final List<String> LIST_CALLBACKS = new ArrayList<>(List.of(callBacks));


    public static final String REPORT_OK = "Ваш отчет полностью соотвествует требованиям. Полученные данные были успешно сохранены." +
            "\nНапоминаю, что отчеты необходимо отправлять ежедневно в течение всего испытательного срока";

    public static final String REPORT_NOT_FULL = "В отправленном отчете не хватает текстового описания. Будьте внимательнее! И в ответном" +
            " сообщении отправьте фото вместе с текстовым описанием одним сообщением";

    public static final String ERROR = "Произошла ошибка. Попробуйте отправить отчет позднее...";

    public static final String MENTION_TO_SEND_REPORT = "Приветствую, пользователь. Я заметил, что с момента отправки тобой последнего отчета прошло более одного дня." +
            "\nХочу напомнить, что в течение всего испытательного срока отчеты необходимо отправлять каждый день. Это является одним из условий успешного прохождения " +
            "испытательного срока, поэтому не забудь отправить отчет в ближайшее время";


}
