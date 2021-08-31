package converterBot.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import converterBot.models.Currency;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CurrencyUtil {

    public static ArrayList<Currency> currencyArrayList;

    public static ArrayList<Currency> getCurrency() throws IOException {
        HttpGet get = new HttpGet("http://cbu.uz/ru/arkhiv-kursov-valyut/json/");
        HttpClient client = HttpClients.createDefault();
        HttpResponse httpResponse = client.execute(get);

        Reader reader = new InputStreamReader(httpResponse.getEntity().getContent());
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Currency>>() {}.getType();
        currencyArrayList = gson.fromJson(reader, type);
        return currencyArrayList;
    }

    public static Currency getCurrencyRate(ArrayList<Currency> currencyArrayList1, String Ccy) {
        Currency result = null;
        for (Currency currency : currencyArrayList1) {
            if (currency.getCcy().equals(Ccy)) {
                result = currency;
            }
        }
        return result;
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

}
