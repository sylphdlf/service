package com.dlf.common.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class PayUtils {

    public static String getParams(Map<String,String[]> requestParams) throws UnsupportedEncodingException {
        Map<String,String> params = new HashMap<String,String>();
        for (String name : requestParams.keySet()) {
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
                System.out.println(valueStr);
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        return JSONObject.toJSONString(params);
    }

    public static String getJsonParams(InputStream inputStream){
        try {
            BufferedReader streamReader = new BufferedReader( new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
            return jsonObject.toJSONString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
