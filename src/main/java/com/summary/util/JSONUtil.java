package com.summary.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
//import com.summary.entity.Answer;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class JSONUtil {

    public static String getJson(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = null;
        String jsonString = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
        stringBuilder = new StringBuilder();
        String str = "";
        while ((str = reader.readLine()) != null) {
            stringBuilder.append(str);
        }
        //获取到了json
        jsonString = stringBuilder.toString();
        reader.close();
        return jsonString;
    }

//    public static Answer jsonToAnswer(String jsonString){
//        Gson gson = new Gson();
//        return gson.fromJson(jsonString,Answer.class);
//
//    }
//
//    public static int getStatusFromJson(String jsonString) throws IOException {
//        JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
//
//        JsonObject jsonObject1 = jsonObject.get("data").getAsJsonObject();
//        JsonObject jsonObject2 = jsonObject.get("user").getAsJsonObject();
//        return jsonObject2.get("status").getAsInt();
//    }


}
