package com.xiongyx.my.simple.json.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class JackSonUtil {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();

        // 全部字段序列化
        //对象的所有字段全部列入
        objectMapper.setSerializationInclusion(Include.ALWAYS);
        //取消默认转换timestamps形式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        //忽略空Bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 反序列化时，保留精度
        objectMapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
    }

    /**
     * @param obj
     * @return
     */
    public static <T> String obj2String(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("obj2StringPretty parse error",e);
        }
    }

    /**
     * 有格式的
     *
     * @param obj
     * @return
     */
    public static <T> String obj2StringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            DefaultPrettyPrinter printer = new DefaultPrettyPrinter();

            // 自定义缩进：使用4个空格
            DefaultIndenter indenter = new DefaultIndenter("    ", "\n");
            printer.indentObjectsWith(indenter);   // 对象缩进
            printer.indentArraysWith(indenter);    // 数组缩进

            return obj instanceof String ? (String) obj : objectMapper.writer(printer).writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("obj2StringPretty parse error",e);
        }
    }

    /**
     * 字符串转对象
     *
     * @param str
     * @param clazz
     * @return
     */
    public static <T> T string2Obj(String str, Class<T> clazz) {
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            throw new RuntimeException("string2Obj parse error",e);
        }
    }

    /**
     * 字段符转List之类的集合
     *
     * @param str
     * @param typeReference
     * @return
     */
    public static <T> T string2Obj(String str, TypeReference<T> typeReference) {
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (Exception e) {
            throw new RuntimeException("string2Obj parse error",e);
        }
    }

    /**
     * 差不多同上
     *
     * @param str
     * @param collectionClass 集合的类型.
     * @param elementClasses  元素的类型.
     * @return
     */
    public static <T> T string2Obj(String str, Class<?> collectionClass, Class<?>... elementClasses) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (Exception e) {
            throw new RuntimeException("string2Obj parse error",e);
        }
    }

    static class GlobalDoubleDeserializer extends JsonDeserializer<Double> {
        @Override
        public Double deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            double value = p.getDoubleValue();
            BigDecimal bd = BigDecimal.valueOf(value);
            return bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
    }

    public static void main(String[] args) {
        String json = "{\"number\":548608e942107732568}";
        try {
            Object obj = JackSonUtil.string2Obj(json, Object.class);
            System.out.println(obj);

            System.out.println(JackSonUtil.obj2String(obj));
            System.out.println(JackSonUtil.obj2StringPretty(obj));
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
