package wu.sun.org.sunapp.gson;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import wu.sun.org.sunapp.util.ITry;

/**
 * Created by hzsunjianshun on 17/7/25.
 */

public class GsonTry implements ITry {
    @Override
    public void doTry(Context context) {
        String resultStr = "{'code':'00','personList':[{\"id\":\"123456\",\"product_category\":\"101\",\"name\":\"xiaofeifei\",\"age\":\"25\",\"ranking\":\"0\"},{\"id\":\"123456\",\"product_category\":\"201\",\"name\":\"xiaofeifei\",\"age\":\"25\",\"ranking\":\"1\"}]}";

        Gson gson = new GsonBuilder().registerTypeAdapter(RANKING.class, new JsonDeserializer<RANKING>() {
            @Override
            public RANKING deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                int ranking = json.getAsJsonPrimitive().getAsInt();
                return RANKING.values()[ranking];
            }
        }).registerTypeAdapter(new TypeToken<List<BasePerson>>(){}.getType(), new JsonDeserializer<List<BasePerson>>() {
            @Override
            public List<BasePerson> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                List list = new ArrayList<BasePerson>();
                JsonArray ja = json.getAsJsonArray();
                for (JsonElement je : ja) {
                    String type = je.getAsJsonObject().get("product_category").getAsString();
                    list.add(context.deserialize(je, PersonType.getByProductCategory(type).getType()));
                }
                return list;
            }
        }).create();
        ResultEntity result = gson.fromJson(resultStr,ResultEntity.class);
        Log.d("GsonTry", "");
    }
}


class BasePerson{
    private Long id;
    private String product_category;
    private RANKING ranking;

    public RANKING getRanking() {
        return ranking;
    }

    public void setRanking(RANKING ranking) {
        this.ranking = ranking;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

class PersonWithName extends BasePerson {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class PersonWithAge extends BasePerson{
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

enum PersonType {
    WITHNAME("名称", PersonWithName.class, "101"),
    WITHAGE("年龄", PersonWithAge.class, "201");

    private String displayName;
    private Class<? extends BasePerson> type;
    private String product_category;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Class<? extends BasePerson> getType() {
        return type;
    }

    public void setType(Class<? extends BasePerson> type) {
        this.type = type;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    private PersonType(String displayName, Class<? extends BasePerson> type , String product_category) {
        this.displayName = displayName;
        this.type = type;
        this.product_category = product_category;
    }
    public static PersonType getByProductCategory(String category){
        for(PersonType t : values()){
            if(t.product_category.equals(category)){
                return t;
            }
        }
        return null;
    }
}

enum RANKING {
    TOP, BOTTOM;
}

class ResultEntity{
    String code;
    List<BasePerson> personList;

    public List<BasePerson> getPersonList() {
        return personList;
    }

    public void setPersonList(List<BasePerson> personList) {
        this.personList = personList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

