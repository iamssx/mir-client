import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.List;

/**
 * Created by SSX on 2017/11/20.
 */
public class Main {


    public static void main(String[] args) {
        String json = "{1:{\"MinMoAttack\":26,\"MaxMoAttack\":53},2:{\"MaxWuDefend\":33,\"MinWuDefend\":16},3:{\"MinWuAttack\":6,\"MaxWuAttack\":13},4:{\"CritProb\":22}}";


        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println(jsonObject);

        JsonParser jsonParser = new JsonParser();
        JsonElement parse = jsonParser.parse(json);
        System.out.println(parse);
        List.of()
    }
}
