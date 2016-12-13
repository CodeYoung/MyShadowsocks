/**
 * 
 */
package com.young.shadowsocks.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Administrator
 *
 */
public class JSONUtilNew {
	/**
     * ��һ��ʵ�������ת����Json���ݸ�ʽ
     * 
     * @param bean
     *            ��Ҫת����ʵ�������
     * @return ת�����Json��ʽ�ַ���
     */
    public static String beanToJson(Object bean) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        PropertyDescriptor[] props = null;
        try {
            props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();
        } catch (IntrospectionException e) {
        }
        if (props != null) {
            for (int i = 0; i < props.length; i++) {
                try {
                    String name = objectToJson(props[i].getName());
                    String value = objectToJson(props[i].getReadMethod().invoke(bean));
                    json.append(name);
                    json.append(":");
                    json.append(value);
                    json.append(",");
                } catch (Exception e) {
                }
            }
            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }
        return json.toString();
    }

    /**
     * ��һ��List����ת����Json���ݸ�ʽ����
     * 
     * @param list
     *            ��Ҫ����ת����List����
     * @return ת�����Json���ݸ�ʽ�ַ���
     */
    public static String listToJson(List<?> list) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (list != null && list.size() > 0) {
            for (Object obj : list) {
                json.append(objectToJson(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

    /**
     * ��һ����������ת����Json���ݸ�ʽ����
     * 
     * @param array
     *            ��Ҫ����ת�����������
     * @return ת�����Json���ݸ�ʽ�ַ���
     */
    public static String arrayToJson(Object[] array) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (array != null && array.length > 0) {
            for (Object obj : array) {
                json.append(objectToJson(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

    /**
     * ��һ��Map����ת����Json���ݸ�ʽ����
     * 
     * @param map
     *            ��Ҫ����ת����Map����
     * @return ת�����Json���ݸ�ʽ�ַ���
     */
    public static String mapToJson(Map<?, ?> map) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        if (map != null && map.size() > 0) {
            for (Object key : map.keySet()) {
                json.append(objectToJson(key));
                json.append(":");
                json.append(objectToJson(map.get(key)));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }
        return json.toString();
    }

    /**
     * ��һ��Set����ת����Json���ݸ�ʽ����
     * 
     * @param set
     *            ��Ҫ����ת����Set����
     * @return ת�����Json���ݸ�ʽ�ַ���
     */
    public static String setToJson(Set<?> set) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (set != null && set.size() > 0) {
            for (Object obj : set) {
                json.append(objectToJson(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

    private static String numberToJson(Number number) {
        return number.toString();
    }

    private static String booleanToJson(Boolean bool) {
        return bool.toString();
    }

    private static String nullToJson() {
        return "";
    }

    public static String stringToJson(String s) {
        if (s == null) {
            return nullToJson();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
            case '"':
                sb.append("\\\"");
                break;
            case '\\':
                sb.append("\\\\");
                break;
            case '\b':
                sb.append("\\b");
                break;
            case '\f':
                sb.append("\\f");
                break;
            case '\n':
                sb.append("\\n");
                break;
            case '\r':
                sb.append("\\r");
                break;
            case '\t':
                sb.append("\\t");
                break;
            case '/':
                sb.append("\\/");
                break;
            default:
                if (ch >= '\u0000' && ch <= '\u001F') {
                    String ss = Integer.toHexString(ch);
                    sb.append("\\u");
                    for (int k = 0; k < 4 - ss.length(); k++) {
                        sb.append('0');
                    }
                    sb.append(ss.toUpperCase());
                } else {
                    sb.append(ch);
                }
            }
        }
        return sb.toString();
    }

    private static String objectToJson(Object obj) {
        StringBuilder json = new StringBuilder();
        if (obj == null) {
            json.append("\"\"");
        } else if (obj instanceof Number) {
            json.append(numberToJson((Number) obj));
        } else if (obj instanceof Boolean) {
            json.append(booleanToJson((Boolean) obj));
        } else if (obj instanceof String) {
            json.append("\"").append(stringToJson(obj.toString())).append("\"");
        } else if (obj instanceof Object[]) {
            json.append(arrayToJson((Object[]) obj));
        } else if (obj instanceof List) {
            json.append(listToJson((List<?>) obj));
        } else if (obj instanceof Map) {
            json.append(mapToJson((Map<?, ?>) obj));
        } else if (obj instanceof Set) {
            json.append(setToJson((Set<?>) obj));
        }else if(obj instanceof Date){
        	 json.append("\"").append(stringToJson(obj.toString())).append("\"");
        } else {
            json.append(beanToJson(obj));
        }
        return json.toString();
    }


    /**
     * ��Json��ʽ���ַ���ת����ָ���Ķ��󷵻�
     * 
     * @param jsonString
     *            Json��ʽ���ַ���
     * @param pojoCalss
     *            ת����Ķ�������
     * @return ת����Ķ���
     */
    @SuppressWarnings("rawtypes")
    public static Object jsonToObject(String jsonString, Class pojoCalss) {
        Object pojo;
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        pojo = JSONObject.toBean(jsonObject, pojoCalss);
        return pojo;
    }

    /**
     * ��Json��ʽ���ַ���ת����Map<String,Object>���󷵻�
     * 
     * @param jsonString
     *            ��Ҫ����ת����Json��ʽ�ַ���
     * @return ת�����Map<String,Object>����
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, Object> jsonToMap(String jsonString) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        Iterator keyIter = jsonObject.keys();
        String key;
        Object value;
        Map<String, Object> valueMap = new HashMap<String, Object>();
        while (keyIter.hasNext()) {
            key = (String) keyIter.next();
            value = jsonObject.get(key);
            valueMap.put(key, value);
        }
        return valueMap;
    }

    /**
     * ��Json��ʽ���ַ���ת���ɶ������鷵��
     * 
     * @param jsonString
     *            ��Ҫ����ת����Json��ʽ�ַ���
     * @return ת����Ķ�������
     */
    public static Object[] jsonToObjectArray(String jsonString) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        return jsonArray.toArray();
    }

    /**
     * ��Json��ʽ���ַ���ת����ָ��������ɵ�List����
     * 
     * @param jsonString
     *            Json��ʽ���ַ���
     * @param pojoClass
     *            ת�����List�ж�������
     * @return ת�����List����
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> jsonToList(String jsonString, Class<T> pojoClass) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        JSONObject jsonObject;
        T pojoValue;
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            pojoValue = (T) JSONObject.toBean(jsonObject, pojoClass);
            list.add(pojoValue);
        }
        return list;
    }

    /**
     * ��Json��ʽ���ַ���ת�����ַ������鷵��
     * 
     * @param jsonString
     *            ��Ҫ����ת����Json��ʽ�ַ���
     * @return ת������ַ�������
     */
    public static String[] jsonToStringArray(String jsonString) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        String[] stringArray = new String[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            stringArray[i] = jsonArray.getString(i);
        }
        return stringArray;
    }

    /**
     * ��Json��ʽ���ַ���ת����Long���鷵��
     * 
     * @param jsonString
     *            ��Ҫ����ת����Json��ʽ�ַ���
     * @return ת�����Long����
     */
    public static Long[] jsonToLongArray(String jsonString) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Long[] longArray = new Long[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            longArray[i] = jsonArray.getLong(i);
        }
        return longArray;
    }

    /**
     * ��Json��ʽ���ַ���ת����Integer���鷵��
     * 
     * @param jsonString
     *            ��Ҫ����ת����Json��ʽ�ַ���
     * @return ת�����Integer����
     */
    public static Integer[] jsonToIntegerArray(String jsonString) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Integer[] integerArray = new Integer[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            integerArray[i] = jsonArray.getInt(i);
        }
        return integerArray;
    }

    /**
     * ��Json��ʽ���ַ���ת����Double���鷵��
     * 
     * @param jsonString
     *            ��Ҫ����ת����Json��ʽ�ַ���
     * @return ת�����Double����
     */
    public static Double[] jsonToDoubleArray(String jsonString) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Double[] doubleArray = new Double[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            doubleArray[i] = jsonArray.getDouble(i);
        }
        return doubleArray;
    }

    public static void main(String[] args) {
        String json = "[{\"STATUS\":\"exception\"}]";
       System.out.println(jsonToStringArray(json));
        // System.out.println(jsonToList( json , SysUser.class ));
    }

}
