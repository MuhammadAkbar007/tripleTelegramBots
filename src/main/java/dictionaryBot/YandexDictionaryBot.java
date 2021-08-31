package dictionaryBot;

import dictionaryBot.models.DefItem;
import dictionaryBot.models.Result;
import dictionaryBot.models.TrItem;
import dictionaryBot.utils.YandexUtil;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class YandexDictionaryBot extends TelegramLongPollingBot {

    public static final String BOTTOKEN = "1478338402:AAEOBQmY8-aM7MOQv-zgzLksZGw55UTQcUE";
    public static final String BOTNAME = "YandexDictionaryAkbarjon_Bot";
    int level = 0;
    String language = null;

    public String getBotToken() {
        return BOTTOKEN;
    }

    public String getBotUsername() {
        return BOTNAME;
    }

    public void onUpdateReceived(Update update) {

        SendMessage sendMessage = new SendMessage();
        Long chatId;
        String text = null;
        String data = null;
        if (update.hasMessage()) {
            chatId = update.getMessage().getChatId();
            text = update.getMessage().getText();

            if (text.equalsIgnoreCase("/start")) {
                sendMessage.setText("Yandex botga xush kelibsiz!");
                level = 0;
            } else {
                level = 2;
            }
        } else {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            data = update.getCallbackQuery().getData();
        }
        sendMessage.setChatId(chatId);

        switch (level) {
            case 0:
                chooseLang(sendMessage);
                level = 1;
                break;
            case 1:
                if (data.equalsIgnoreCase("en-ru")) {
                    language = "en-ru";
                    sendMessage.setText("Qidirilayotgan so'zni kiriting");
                } else if (data.equalsIgnoreCase("ru-en")) {
                    language = "ru-en";
                    sendMessage.setText("Qidirilayotgan so'zni kiriting");
                } else if (data.equalsIgnoreCase("tr-en")) {
                    language = "tr-en";
                    sendMessage.setText("Qidirilayotgan so'zni kiriting");
                } else if (data.equalsIgnoreCase("tr-ru")) {
                    language = "tr-ru";
                    sendMessage.setText("Qidirilayotgan so'zni kiriting");
                } else if (data.equalsIgnoreCase("en-tr")) {
                    language = "en-tr";
                    sendMessage.setText("Qidirilayotgan so'zni kiriting");
                } else if (data.equalsIgnoreCase("ru-tr")) {
                    language = "ru-tr";
                    sendMessage.setText("Qidirilayotgan so'zni kiriting");
                }
                break;
            case 2:
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    Result dictionary = YandexUtil.getDictionary(text, language);
                    List<DefItem> def = dictionary.getDef();
                    for (DefItem defItem : def) {
                        List<TrItem> tr = defItem.getTr();
                        for (TrItem trItem : tr) {
                            stringBuilder.append(trItem.getText()).append("\n");
                        }
                    }
                    level = 1;
                    sendMessage.setChatId(chatId);
                    sendMessage.setText(String.valueOf(stringBuilder));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                break;
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void chooseLang(SendMessage sendMessage) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> lists = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();

        InlineKeyboardButton enruButton = new InlineKeyboardButton();
        InlineKeyboardButton entrButton = new InlineKeyboardButton();
        InlineKeyboardButton ruenButton = new InlineKeyboardButton();
        InlineKeyboardButton rutrButton = new InlineKeyboardButton();
        InlineKeyboardButton trenButton = new InlineKeyboardButton();
        InlineKeyboardButton trruButton = new InlineKeyboardButton();

        enruButton.setCallbackData("en-ru");
        entrButton.setCallbackData("en-tr");
        ruenButton.setCallbackData("ru-en");
        rutrButton.setCallbackData("ru-tr");
        trenButton.setCallbackData("tr-en");
        trruButton.setCallbackData("tr-ru");

        enruButton.setText("\uD83C\uDDEC\uD83C\uDDE7 Eng = > Ru \uD83C\uDDF7\uD83C\uDDFA");
        entrButton.setText("\uD83C\uDDEC\uD83C\uDDE7 Eng = > Turk \uD83C\uDDF9\uD83C\uDDF7");
        ruenButton.setText(" \uD83C\uDDF7\uD83C\uDDFA Ru = > Eng \uD83C\uDDEC\uD83C\uDDE7");
        rutrButton.setText(" \uD83C\uDDF7\uD83C\uDDFA Ru = > Turk \uD83C\uDDF9\uD83C\uDDF7");
        trenButton.setText(" \uD83C\uDDF9\uD83C\uDDF7 Turk = > Eng \uD83C\uDDEC\uD83C\uDDE7");
        trruButton.setText(" \uD83C\uDDF9\uD83C\uDDF7 Turk = > Ru \uD83C\uDDF7\uD83C\uDDFA");


        row1.add(enruButton);
        row2.add(entrButton);
        row1.add(ruenButton);
        row2.add(rutrButton);
        row1.add(trenButton);
        row2.add(trruButton);

        lists.add(row1);
        lists.add(row2);
        inlineKeyboardMarkup.setKeyboard(lists);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
    }

}
