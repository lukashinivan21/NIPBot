package tgbots.nipbot.constants;

import org.springframework.stereotype.Repository;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import java.io.File;

/**
 * Класс, содержащий файлы с инструкциями и справочной информацией, для отправки пользователю
 */
@Repository
public class Files {

    /**
     * Рассказ о приюте
     */
    public static final InputFile FULL_INFO = new InputFile(new File("Вставить нужный путь к файлу"));


    /**
     * Расписание работы и адрес приюта
     */
    public static final InputFile FULL_ADDRESS = new InputFile(new File("Вставить нужный путь к файлу"));


    /**
     * Общие рекомендации по технике безопасности на территории приюта
     */
    public static final InputFile FULL_REC = new InputFile(new File("Вставить нужный путь к файлу"));


    /**
     * Правила знакомства с собакой до того, как забрать ее из приюта
     */
    public static final InputFile FULL_CONTACT = new InputFile(new File("Вставить нужный путь к файлу"));

    /**
     * Список документов необходимых для того, чтобы взять собаку из приюта
     */
    public static final InputFile LIST_DOC = new InputFile(new File("Вставить нужный путь к файлу"));

    /**
     * Список рекомендаций по транспортировке животного
     */
    public static final InputFile LIST_REC = new InputFile(new File("Вставить нужный путь к файлу"));

    /**
     * Рекомендации по обустройству дома для щенка
     */
    public static final InputFile PUPPY = new InputFile(new File("Вставить нужный путь к файлу"));

    /**
     * Рекомендации по обустройству дома для взрослой собаки
     */
    public static final InputFile DOG = new InputFile(new File("Вставить нужный путь к файлу"));

    /**
     * Рекомендации по обустройству дома для собаки с ограниченными возможностями
     */
    public static final InputFile LIMIT = new InputFile(new File("Вставить нужный путь к файлу"));

    /**
     * Советы кинологов по первичному общению с собакой
     */
    public static final InputFile ADVICES = new InputFile(new File("Вставить нужный путь к файлу"));

    /**
     * Список проверенных кинологов
     */
    public static final InputFile EXPERTS  = new InputFile(new File("Вставить нужный путь к файлу"));

    /**
     * Причины отказа в заборе собаки из приюта
     */
    public static final InputFile REASONS = new InputFile(new File("Вставить нужный путь к файлу"));

    /**
     * Форма отчета
     */
    public static final InputFile FORM = new InputFile(new File("Вставить нужный путь к файлу"));

    /**
     * Инструкция по заполнению отчета
     */
    public static final InputFile INST = new InputFile(new File("Вставить нужный путь к файлу"));





}
