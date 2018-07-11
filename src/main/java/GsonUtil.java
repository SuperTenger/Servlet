import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
    private static Gson gson = new GsonBuilder().create();

    public static String bean2Json(Object object){
        return gson.toJson(object);
    }

    public static <T> T json2Bean(String jsonStr,Class<T> objClass){
        return gson.fromJson(jsonStr, objClass);
    }

}
