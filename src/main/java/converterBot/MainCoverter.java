package converterBot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class MainCoverter {
    public static void main(String[] args) throws TelegramApiRequestException {

        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        botsApi.registerBot(new ConverterBot());
        System.out.println("Converter bot ishga tushdi !");
    }
}
