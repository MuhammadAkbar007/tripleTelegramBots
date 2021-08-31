package krilllotinBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class KrillLotinBot extends TelegramLongPollingBot {

    public static final String BOTTOKEN = "1333148193:AAEzl_Ijg2p-DcRTkhX89B49t5rihPXDKGU";
    public static final String BOTUSERNAME = "krillVsLotin_bot";
    int level = 0;

    @Override
    public String getBotToken() {
        return BOTTOKEN;
    }

    @Override
    public String getBotUsername() {
        return BOTUSERNAME;
    }

    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        String text = message.getText();

        if (text.equalsIgnoreCase("/start")) {
            level = 0;
        }

        Long chatId = message.getChatId();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        switch (level) {
            case 0:
                sendMessage.setText("Lotin-Крилл botga xush kelibsiz ! \uD83E\uDD16\n\nMenyudagi buttonlardan birini tanlang \uD83D\uDC47");
                mainMenu(sendMessage);
                level = 1;
                break;
            case 1:
                if (text.equals("Lotindan \uD83D\uDD1C Криллга")) {
                    sendMessage.setText("\uD83D\uDCD3 Matnni kiriting");
                    level = 2;
                } else if (text.equals("Криллдан \uD83D\uDD1C Lotinga")) {
                    sendMessage.setText("\uD83D\uDCD3 Matnni kiriting");
                    level = 3;
                } else {
                    sendMessage.setText("Menyudagi buttonlardan birini tanlang \uD83D\uDC47");
                    mainMenu(sendMessage);
                    level = 1;
                }
                break;
            case 2:
                String res = lotKr(text);
                sendMessage.setText(res);
                mainMenu(sendMessage);
                level = 1;
                break;
            case 3:
                String res1 = krLot(text);
                sendMessage.setText(res1);
                mainMenu(sendMessage);
                level = 1;
                break;
            default:
                sendMessage.setText("Berilgan buttonlardan birini tanlang ! \uD83E\uDDD0");
                mainMenu(sendMessage);
                level = 1;
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void mainMenu(SendMessage sendMessage) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        ArrayList<KeyboardRow> rowsList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        KeyboardButton lotKr = new KeyboardButton("Lotindan \uD83D\uDD1C Криллга");
        KeyboardButton krLot = new KeyboardButton("Криллдан \uD83D\uDD1C Lotinga");

        row.add(lotKr);
        row.add(krLot);

        rowsList.add(row);

        replyKeyboardMarkup.setKeyboard(rowsList);
    }

    public String lotKr(String str) {

        char letter;
        for (int i = 0; i < str.length(); i++) {

            letter = str.charAt(i);
            switch (letter) {
                case 'B':
                    str = str.replace('B', 'Б');
                    break;
                case 'b':
                    str = str.replace('b', 'б');
                    break;
                case 'D':
                    str = str.replace('D', 'Д');
                    break;
                case 'd':
                    str = str.replace('d', 'д');
                    break;
                case 'F':
                    str = str.replace('F', 'Ф');
                    break;
                case 'f':
                    str = str.replace('f', 'ф');
                    break;
                case 'G':
                    str = str.replace('G', 'Г');
                    break;
                case 'g':
                    str = str.replace('g', 'г');
                    break;
                case 'H':
                    str = str.replace('H', 'Ҳ');
                    break;
                case 'h':
                    str = str.replace('h', 'ҳ');
                    break;
                case 'I':
                    str = str.replace('I', 'И');
                    break;
                case 'i':
                    str = str.replace('i', 'и');
                    break;
                case 'J':
                    str = str.replace('J', 'Ж');
                    break;
                case 'j':
                    str = str.replace('j', 'ж');
                    break;
                case 'k':
                    str = str.replace('k', 'к');
                    break;
                case 'L':
                    str = str.replace('L', 'Л');
                    break;
                case 'l':
                    str = str.replace('l', 'л');
                    break;
                case 'm':
                    str = str.replace('m', 'м');
                    break;
                case 'N':
                    str = str.replace('N', 'Н');
                    break;
                case 'n':
                    str = str.replace('n', 'н');
                    break;
                case 'P':
                    str = str.replace('P', 'П');
                    break;
                case 'p':
                    str = str.replace('p', 'п');
                    break;
                case 'Q':
                    str = str.replace('Q', 'Қ');
                    break;
                case 'q':
                    str = str.replace('q', 'қ');
                    break;
                case 'R':
                    str = str.replace('R', 'Р');
                    break;
                case 'r':
                    str = str.replace('r', 'р');
                    break;
                case 'S':
                    str = str.replace('S', 'С');
                    break;
                case 's':
                    str = str.replace('s', 'с');
                    break;
                case 't':
                    str = str.replace('t', 'т');
                    break;
                case 'U':
                    str = str.replace('U', 'У');
                    break;
                case 'u':
                    str = str.replace('u', 'у');
                    break;
                case 'V':
                    str = str.replace('V', 'В');
                    break;
                case 'v':
                    str = str.replace('v', 'в');
                    break;
                case 'Y':
                    str = str.replace('Y', 'Й');
                    break;
                case 'y':
                    str = str.replace('y', 'й');
                    break;
                case 'Z':
                    str = str.replace('Z', 'З');
                    break;
                case 'z':
                    str = str.replace('z', 'з');
                    break;
            }

            if (str.contains("O'")) {
                str = str.replace("O'", "Ў");
            } else if (str.contains("o'")) {
                str = str.replace("o'", "ў");
            } else if (str.contains("G'")) {
                str = str.replace("G'", "Ғ");
            } else if (str.contains("g'")) {
                str = str.replace("g'", "ғ");
            } else if (str.contains("Sh")) {
                str = str.replace("Sh", "Ш");
            } else if (str.contains("sh")) {
                str = str.replace("sh", "ш");
            } else if (str.contains("Ch")) {
                str = str.replace("Ch", "Ч");
            } else if (str.contains("ch")) {
                str = str.replace("ch", "ч");
            }
        }

        return str;
    }

    public String krLot(String str) {
        char letter;
        for (int i = 0; i < str.length(); i++) {
            letter = str.charAt(i);
            switch (letter) {
                case 'Б':
                    str = str.replace('Б', 'B');
                    break;
                case 'б':
                    str = str.replace('б', 'b');
                    break;
                case 'Д':
                    str = str.replace('Д', 'D');
                    break;
                case 'д':
                    str = str.replace('д', 'd');
                    break;
                case 'Ф':
                    str = str.replace('Ф', 'F');
                    break;
                case 'ф':
                    str = str.replace('ф', 'f');
                    break;
                case 'Г':
                    str = str.replace('Г', 'G');
                    break;
                case 'г':
                    str = str.replace('г', 'g');
                    break;
                case 'И':
                    str = str.replace('И', 'I');
                    break;
                case 'и':
                    str = str.replace('и', 'i');
                    break;
                case 'Ж':
                    str = str.replace('Ж', 'J');
                    break;
                case 'ж':
                    str = str.replace('ж', 'j');
                    break;
                case 'к':
                    str = str.replace('к', 'k');
                    break;
                case 'Л':
                    str = str.replace('Л', 'L');
                    break;
                case 'л':
                    str = str.replace('л', 'l');
                    break;
                case 'м':
                    str = str.replace('м', 'm');
                    break;
                case 'Н':
                    str = str.replace('Н', 'N');
                    break;
                case 'н':
                    str = str.replace('н', 'n');
                    break;
                case 'П':
                    str = str.replace('П', 'P');
                    break;
                case 'п':
                    str = str.replace('п', 'p');
                    break;
                case 'Р':
                    str = str.replace('Р', 'R');
                    break;
                case 'р':
                    str = str.replace('р', 'r');
                    break;
                case 'С':
                    str = str.replace('С', 'S');
                    break;
                case 'с':
                    str = str.replace('с', 's');
                    break;
                case 'т':
                    str = str.replace('т', 't');
                    break;
                case 'У':
                    str = str.replace('У', 'U');
                    break;
                case 'у':
                    str = str.replace('у', 'u');
                    break;
                case 'В':
                    str = str.replace('В', 'V');
                    break;
                case 'в':
                    str = str.replace('в', 'v');
                    break;
                case 'Й':
                    str = str.replace('Й', 'Y');
                    break;
                case 'й':
                    str = str.replace('й', 'y');
                    break;
                case 'З':
                    str = str.replace('З', 'Z');
                    break;
                case 'з':
                    str = str.replace('з', 'z');
                    break;
            }

            if (str.contains("Ё")) {
                str = str.replace("Ё", "Yo");
            } else if (str.contains("ё")) {
                str = str.replace("ё", "yo");
            } else if (str.contains("Ц")) {
                str = str.replace("Ц", "Ts");
            } else if (str.contains("ц")) {
                str = str.replace("ц", "ts");
            } else if (str.contains("Ъ")) {
                str = str.replace("Ъ", "'");
            } else if (str.contains("ъ")) {
                str = str.replace("ъ", "'");
            } else if (str.contains("ы")) {
                str = str.replace("ы", "iy");
            } else if (str.contains("Э")) {
                str = str.replace("Э", "E");
            } else if (str.contains("э")) {
                str = str.replace("э", "e");
            } else if (str.contains("Я")) {
                str = str.replace("Я", "Ya");
            } else if (str.contains("я")) {
                str = str.replace("я", "ya");
            } else if (str.contains("ь")) {
                str = str.replace("ь", "");
            } else if (str.contains("Ю")) {
                str = str.replace("Ю", "Yu");
            } else if (str.contains("ю")) {
                str = str.replace("ю", "yu");
            } else if (str.contains("Ш")) {
                str = str.replace("Ш", "Sh");
            } else if (str.contains("ш")) {
                str = str.replace("ш", "sh");
            } else if (str.contains("Ч")) {
                str = str.replace("Ч", "Ch");
            } else if (str.contains("ч")) {
                str = str.replace("ч", "ch");
            }else if (str.contains("Щ")) {
                str = str.replace("Щ", "Shch");
            } else if (str.contains("щ")) {
                str = str.replace("щ", "shch");
            }

        }

        return str;
    }

}
