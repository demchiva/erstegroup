package cz.erstegroup.example.demo.utils.converter;

import com.google.gson.Gson;

public class JsonConverter {
    private static Gson converter = new Gson();

    public static String convertObjectToJson(final Object object) {
        return converter.toJson(object);
    }
}
