package com.chengzg.wxshop.utils;


import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * http请求工具类
 *
 * Created by zhanjunwei on 15/8/4..
 */
public class HttpUtil {


    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static String getTextContent(InputStream inputStream, String encoding) throws Exception {
        byte[] data = readStream(inputStream);
        return new String(data, encoding);
    }

    public static byte[] readStream(InputStream inputStream) {
        ByteArrayOutputStream outputSteam = null;

        try {
            outputSteam = new ByteArrayOutputStream();
            byte[] e = new byte[1024];
            boolean len = true;

            int len1;
            while((len1 = inputStream.read(e)) != -1) {
                outputSteam.write(e, 0, len1);
            }

            byte[] var4 = outputSteam.toByteArray();
            return var4;
        } catch (Exception var14) {
            logger.error("将输入流转为byte数组出错。", var14);
        } finally {
            try {
                if(inputStream != null) {
                    inputStream.close();
                }

                if(outputSteam != null) {
                    outputSteam.close();
                }
            } catch (IOException var13) {
                logger.error("流关闭出错", var13);
            }

        }

        return null;
    }

    
    /**
     * 获取参数
     * @param name
     * @return
     */
    public static String getParameter(HttpServletRequest request, String name) {
        if(request != null){
            try {
                request.setCharacterEncoding("GBK");
            } catch (Exception e) {
                logger.error("getRequest 编码异常", e);
            }
            if (!Strings.isNullOrEmpty(name)) {
                return request.getParameter(name);
            }
        }
        return null;
    }



    /**
     * 获取参数map
     * @return
     */
    public static Map<?, ?> getParametersMap(HttpServletRequest request) {
        if(request != null){
            try {
                request.setCharacterEncoding("UTF-8");
            } catch (Exception e) {
                logger.error("getRequest 编码异常", e);
            }
            Map rtnMap = request.getParameterMap();
            return rtnMap;
        }
        return null;
    }

    /**
     *  获取参数
     * @param defaultValue
     * @return
     */
    public static String getParameter(HttpServletRequest request, String name, String defaultValue) {
        String value = getParameter(request, name);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    /**
     * 获取integer参数
     * @param name
     * @return
     */
    public static Integer getIntegerParameter(HttpServletRequest request, String name) {
        String value = getParameter(request, name);
        if (!Strings.isNullOrEmpty(value)) {
            try{
                return Integer.valueOf(Integer.parseInt(value));
            } catch (Exception e) {
                logger.error("参数不是数字类型", e);
            }
        }
        return null;
    }

    /**
     * 获取integer参数
     * @param name
     * @param defaultValue
     * @return
     */
    public static Integer getIntegerParameter(HttpServletRequest request, String name, Integer defaultValue) {
        Integer value = getIntegerParameter(request, name);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    /**
     * 获取Long参数
     * @param name
     * @return
     */
    public static Long getLongParameter(HttpServletRequest request, String name) {
        String value = getParameter(request, name);
        if (!Strings.isNullOrEmpty(value)) {
            return Long.valueOf(Long.parseLong(value));
        }
        return null;
    }

    /**
     * 获取Long参数
     * @param name
     * @param defaultValue
     * @return
     */
    public static Long getLongParameter(HttpServletRequest request, String name, Long defaultValue) {
        Long value = getLongParameter(request, name);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }



    /**
     * 获取Float参数
     * @param name
     * @return
     */
    public static Float getFloatParameter(HttpServletRequest request, String name) {
        String value = getParameter(request, name);
        if (!Strings.isNullOrEmpty(value)) {
            return Float.valueOf(Float.parseFloat(value));
        }
        return null;
    }

    /**
     * 获取Float参数
     * @param name
     * @param defaultValue
     * @return
     */
    public static Float getFloatParameter(HttpServletRequest request, String name, Float defaultValue) {
        Float value = getFloatParameter(request, name);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    /**
     * 获取Double参数
     * @param name
     * @return
     */
    public static Double getDoubleParameter(HttpServletRequest request, String name) {
        String value = getParameter(request, name);
        if (!Strings.isNullOrEmpty(value)) {
            return Double.valueOf(Double.parseDouble(value));
        }
        return null;
    }

    /**
     * 获取Double参数
     * @param name
     * @param defaultValue
     * @return
     */
    public static Double getDoubleParameter(HttpServletRequest request, String name, Double defaultValue) {
        Double value = getDoubleParameter(request, name);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    /**
     * 获取Boolean参数
     * @param name
     * @return
     */
    public static Boolean getBooleanParameter(HttpServletRequest request, String name) {
        String value = getParameter(request, name);
        if (value != null) {
            Boolean reval = Boolean.valueOf(value);
            return reval;
        }
        return null;
    }

    /**
     * 获取Boolean参数
     * @param name
     * @param defaultValue
     * @return
     */
    public static Boolean getBooleanParameter(HttpServletRequest request, String name, Boolean defaultValue) {
        Boolean value = getBooleanParameter(request, name);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }


    public static String[] getParameterValues(HttpServletRequest request, String name) {
        if (Strings.isNullOrEmpty(name)) {
            throw new NullPointerException("参数名不能为空！");
        }

        return request.getParameterValues(name);
    }

    public static Date getDateParameter(HttpServletRequest request, String name) throws ParseException {
        String value = getParameter(request, name);
        if (!Strings.isNullOrEmpty(value)) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.parse(value);
        }
        return null;
    }

    public static Date getDateParameter(HttpServletRequest request, String name, Date defaultValue)
            throws ParseException {
        Date value = getDateParameter(request, name);
        if (value == null)
            return defaultValue;
        return value;
    }


    /**
     * request添加参数
     * @param name
     * @param o
     */
    public static void setAttribute(HttpServletRequest request, String name, Object o) {
        if(request != null){
            request.setAttribute(name, o);
        }
    }


    /**
     * 向session加值
     * @param name
     * @param o
     */
//    public static void addSessionAttribute(HttpServletRequest request, String name, Object o) {
//
//        if(request != null){
//            HttpSession session = request.getSession();
//            if (session != null) {
//                session.setAttribute(name, o);
//            }
//        }
//    }

    /**
     * session删除值
     * @param name
     */
    public static void delSessionAttribute(HttpServletRequest request, String name) {

        if(request != null){
            HttpSession session = request.getSession();
            if (session != null) {
                session.removeAttribute(name);
            }
        }
    }

    /**
     * 获取session值
     * @param name
     * @return
     */
    public static Object getSessionAttribute(HttpServletRequest request, String name) {
        if(request != null){
            HttpSession session = request.getSession();
            if (session != null) {
                return session.getAttribute(name);
            }
        }
        return null;
    }
    
    
    public static BigDecimal getBigDecimal(HttpServletRequest request, String name) {
    	String value = getParameter(request, name);
    	BigDecimal result = castToBigDecimal(value);
         if( result == null){
        	 return BigDecimal.ZERO;
        }else{
        	return result;
        }
    }
    
    public static final BigDecimal castToBigDecimal(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }

        if (value instanceof BigInteger) {
            return new BigDecimal((BigInteger) value);
        }

        String strVal = value.toString();
        if (strVal.length() == 0) {
            return null;
        }

        return new BigDecimal(strVal);
    }
}
