package cz.erstegroup.example.demo.utils.converter;

import com.google.gson.Gson;

/**
 * The converter to JSON for objects.
 */
final public class JsonConverter {
    private static Gson converter = new Gson();

    /**
     * Converter to JSON
     * @param object
     * @return object converted to JSON format
     */
    public static String convertObjectToJson(final Object object) {
        return converter.toJson(object);
    }
}
