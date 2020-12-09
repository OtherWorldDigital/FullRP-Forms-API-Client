import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String req = new JSONArray()
                .put(new JSONObject().put("question", "Ник").put("answer", "Homosanians"))
                .put(new JSONObject().put("question", "Возраст").put("answer", "1337"))
                .put(new JSONObject().put("question", "Автобиография").put("answer", "Це просто сигарки."))
                .put(new JSONObject().put("question", "Персонаж").put("answer", "Це просто сигарки."))
                .put(new JSONObject().put("question", "Опыт").put("answer", "Це просто сигарки."))
                .put(new JSONObject().put("question", "Планы").put("answer", "Це просто сигарки."))
                .put(new JSONObject().put("question", "Страна").put("answer", "Це просто сигарки."))
                .put(new JSONObject().put("question", "Время").put("answer", "Це просто сигарки."))
                .toString();

        JSONObject res = getResponseOrNull("http://HIDDEN/predict", req);

        System.out.println(res.get("error"));
        System.out.println(res.get("formatted"));
        System.out.println(res.get("prediction"));
    }

    public static JSONObject getResponseOrNull(String url, String req) {
        JSONObject res = null;
        try {
            String resJson = Request.Post(url)
                    .bodyString(req, ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString();

            res = new JSONObject(resJson);
        }
        finally {
            return res;
        }
    }
}
