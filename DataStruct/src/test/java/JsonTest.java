import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by SSX on 2017/9/18.
 */
public class JsonTest {

    @Test
    public void testJson2Map() {
       Map<Byte, Integer> map = new HashMap<>();
       map.put((byte) 1, 5);
       map.put((byte) 2, 4);
        String jsonString = JSON.toJSONString(map);
        System.out.println(jsonString);
        Map<Integer, Integer> parse = (Map<Integer, Integer>) JSON.parse(jsonString);

        Set<Map.Entry<Integer, Integer>> entries = parse.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            Integer key = entry.getKey();
            Integer integer = parse.get(key);
        }
        System.out.println(parse.get(1));
        new JSONObject();
    }

    @Test
    public void tt() throws ParseException {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(0, "0");
        map.put(5, "5");
        map.put(10, "10");

        System.out.println(map.lowerEntry(11).getValue());
        System.out.println(map.lowerEntry(10).getValue());
        System.out.println(map.lowerEntry(6).getValue());
        System.out.println(map.lowerEntry(1).getValue());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date parse = dateFormat.parse("20171108");
        System.out.println(parse.getTime());
    }
}

















