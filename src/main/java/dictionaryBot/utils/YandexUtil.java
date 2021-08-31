package dictionaryBot.utils;

import com.google.gson.Gson;
import dictionaryBot.models.Result;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class YandexUtil {

    public static final String APIKEY = "dict.1.1.20201118T093418Z.450cba2756ab25d6.7b8f3bfa9b6d9f737346e224beb8e62c4ebb1a65";

    public static Result getDictionary(String text, String lang) throws IOException {

        HttpGet httpGet = new HttpGet("https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key=dict.1.1.20201118T093418Z.450cba2756ab25d6.7b8f3bfa9b6d9f737346e224beb8e62c4ebb1a65&lang=" + lang + "&text=" + text);
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(httpGet);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        Gson gson = new Gson();
        Result result = gson.fromJson(bufferedReader, Result.class);

        System.out.println(result);
        return result;
    }
}
