package converterBot;

import converterBot.models.Currency;
import converterBot.utils.CurrencyUtil;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class ConverterBot extends TelegramLongPollingBot {

    public static final String BOTTOKEN = "1457382795:AAE5hzyY8KPkYOCSBtS05BL8OUFuWn4ZpI4";
    public static final String BOTUSERNAME = "ConverterBotAkbarjon_Bot";
    String Ccy = null;
    Currency currency = null;
    boolean convertionType = true;
    int level = 0;

    public String getBotToken() {
        return BOTTOKEN;
    }

    public String getBotUsername() {
        return BOTUSERNAME;
    }

    @SneakyThrows
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        String text = message.getText();

        if (text.equals("/start")) {
            level = 0;
        }

        Long chatId = message.getChatId();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        switch (level) {
            case 0:
                sendMessage.setText("Konverter botga xush kelibsiz ! \uD83E\uDD16");
                mainButton(sendMessage);
                level = 1;
                break;
            case 1:
                if (text.equals("Kurslar haqida ma'lumot ! \uD83D\uDCB4")) {
                    sendMessage.setText("Qaysi valyuta haqida ma'lumot olmoqchisiz ? \uD83E\uDDD0");
                    currencyInfoButton(sendMessage);
                    level = 2;
                } else if (text.equals("Konvertatsiya \uD83D\uDCB7 \uD83D\uDD04 \uD83D\uDCB6")) {
                    sendMessage.setText("Konvertatsiya turini tanlang");
                    convertationButton(sendMessage);
                    level = 3;
                } else {
                    sendMessage.setText("Berilgan buttonlardan birini tanlang ! \uD83D\uDE2C");
                }
                break;
            case 2:
                ArrayList<Currency> list = CurrencyUtil.getCurrency();

                if (text.equalsIgnoreCase("Orqaga")) {
                    sendMessage.setText("Orqaga qaytish");
                    mainButton(sendMessage);
                    level = 1;
                } else if (text.equalsIgnoreCase("USD \uD83C\uDDFA\uD83C\uDDF8")) {
                    text = "USD";
                    sendMessage.setText(String.valueOf(CurrencyUtil.getCurrencyRate(list, text)));
                } else if (text.equalsIgnoreCase("EUR \uD83C\uDDEA\uD83C\uDDFA")) {
                    text = "EUR";
                    sendMessage.setText(String.valueOf(CurrencyUtil.getCurrencyRate(list, text)));
                } else if (text.equalsIgnoreCase("RUB \uD83C\uDDF7\uD83C\uDDFA")) {
                    text = "RUB";
                    sendMessage.setText(String.valueOf(CurrencyUtil.getCurrencyRate(list, text)));
                } else if (text.equalsIgnoreCase("GBP \uD83C\uDDEC\uD83C\uDDE7")) {
                    text = "GBP";
                    sendMessage.setText(String.valueOf(CurrencyUtil.getCurrencyRate(list, text)));
                } else {
                    sendMessage.setText("Berilgan buttonlardan birini bosing!");
                }
                    break;
            case 3:
                switch (text) {
                    case "Orqaga":
                        sendMessage.setText("Orqaga qaytish");
                        mainButton(sendMessage);
                        level = 1;
                        break;
                    case "UZS-USD":
                        sendMessage.setText("Summani kiriting :");
                        convertionType = true;
                        Ccy = "USD";
                        level = 4;
                        break;
                    case "USD-UZS":
                        sendMessage.setText("Summani kiriting :");
                        convertionType = false;
                        Ccy = "USD";
                        level = 4;
                        break;
                    case "UZS-RUB":
                        sendMessage.setText("Summani kiriting :");
                        convertionType = true;
                        Ccy = "RUB";
                        level = 4;
                        break;
                    case "RUB-UZS":
                        sendMessage.setText("Summani kiriting :");
                        convertionType = false;
                        Ccy = "RUB";
                        level = 4;
                        break;
                }
                break;
            case 4:
                list = CurrencyUtil.getCurrency();

                if (CurrencyUtil.isNumeric(text)) {
                    Double sum = Double.parseDouble(text);
                    Currency currency;
                    currency = CurrencyUtil.getCurrencyRate(list, Ccy);

                    Double rate = Double.valueOf(currency.getRate());
                    double nominal = Double.parseDouble(currency.getNominal());

                    if (convertionType) {
                        sendMessage.setText(sum + " UZS = " + sum / rate * nominal + " " + Ccy);
                    } else {
                        sendMessage.setText(sum + " " + Ccy + " = " + sum * rate / nominal + " UZS");
                    }
                } else {
                    sendMessage.setText("Tanlang");
                }
                mainButton(sendMessage);
                convertationButton(sendMessage);
                level = 3;
                break;
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void mainButton(SendMessage sendMessage) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        ArrayList<KeyboardRow> rowsList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        KeyboardButton currencyInfoButton = new KeyboardButton("Kurslar haqida ma'lumot ! \uD83D\uDCB4");
        KeyboardButton converterButton = new KeyboardButton("Konvertatsiya \uD83D\uDCB7 \uD83D\uDD04 \uD83D\uDCB6");
        row.add(currencyInfoButton);
        row.add(converterButton);

        rowsList.add(row);

        replyKeyboardMarkup.setKeyboard(rowsList);
    }

    public void currencyInfoButton(SendMessage sendMessage) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        ArrayList<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();

        KeyboardButton button1 = new KeyboardButton("USD \uD83C\uDDFA\uD83C\uDDF8");
        KeyboardButton button2 = new KeyboardButton("EUR \uD83C\uDDEA\uD83C\uDDFA");
        KeyboardButton button3 = new KeyboardButton("RUB \uD83C\uDDF7\uD83C\uDDFA");
        KeyboardButton button4 = new KeyboardButton("GBP \uD83C\uDDEC\uD83C\uDDE7");

        row1.add(button1);
        row1.add(button2);
        row2.add(button3);
        row2.add(button4);
        row3.add(new KeyboardButton("Orqaga"));

        rows.add(row1);
        rows.add(row2);
        rows.add(row3);

        replyKeyboardMarkup.setKeyboard(rows);

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }

    public void convertationButton(SendMessage sendMessage) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        ArrayList<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();

        KeyboardButton button1 = new KeyboardButton("UZS-USD");
        KeyboardButton button2 = new KeyboardButton("USD-UZS");
        KeyboardButton button3 = new KeyboardButton("UZS-RUB");
        KeyboardButton button4 = new KeyboardButton("RUB-UZS");

        row1.add(button1);
        row1.add(button2);
        rows.add(row1);

        row2.add(button3);
        row2.add(button4);
        rows.add(row2);

        row3.add(new KeyboardButton("Orqaga"));
        rows.add(row3);

        replyKeyboardMarkup.setKeyboard(rows);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }

//    public void infoCurrency(SendMessage sendMessage) {
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("Valyuta turi : " + currency.getCcy())
//                .append("\n")
//                .append("Valyuta nomi : " + currency.getCcyNm_UZ())
//                .append("\n")
//                .append("Valyuta kursi : " + currency.getRate())
//                .append("\n")
//                .append("Valyuta o'zgargan sana : " + currency.getDate());
//
//        sendMessage.setText(String.valueOf(stringBuilder));
//    }
}
