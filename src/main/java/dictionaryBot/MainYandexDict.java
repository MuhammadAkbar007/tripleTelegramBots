package dictionaryBot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class MainYandexDict {
    public static void main(String[] args) throws TelegramApiRequestException {

        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        botsApi.registerBot(new YandexDictionaryBot());
        System.out.println("YandexBot ishga tushdi!");
    }
}
