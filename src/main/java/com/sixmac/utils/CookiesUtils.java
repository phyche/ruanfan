package com.sixmac.utils;

import com.sixmac.core.Constant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class CookiesUtils {

    /**
     * 设置cookie（接口方法）
     *
     * @param response
     * @param name
     *            cookie名字
     * @param value
     *            cookie值
     * @param maxAge
     *            cookie生命周期 以秒为单位
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        try {
            Cookie cookie = new Cookie(name, URLEncoder.encode(value, Constant.ENCODING) );
            cookie.setPath("/");
            if (maxAge > 0){
                cookie.setMaxAge(maxAge);
            }
            response.addCookie(cookie);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 将cookie封装到Map里面（非接口方法）
     *
     * @param request
     * @return 返回之后走自动登陆流程
     */
    public static Map<String, Object> ReadCookieMap(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                String attname = cookie.getName();
                params.put(attname, cookie.getValue());
            }
        }
        return params;
    }

    public static String readCookie(HttpServletRequest request,String name){
        try {
            Cookie[] cookies = request.getCookies();
            if (null == cookies) {
                return null;
            }
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(name)){
                    return URLDecoder.decode(cookie.getValue(),Constant.ENCODING) ;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 清空cookie
     *
     * @param request
     * @param response
     * @return
     * @author 涂奕恒
     * @Date 2014-12-15 上午11:13:32
     */
    public static void clearCookie(HttpServletRequest request, HttpServletResponse response,String name) {
        Cookie[] cookies = request.getCookies();
        try {
            for (int i = 0; i < cookies.length; i++) {
                if(name.equals(cookies[i].getName())){
                    Cookie cookie = new Cookie(cookies[i].getName(), null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");// 根据你创建cookie的路径进行填写
                    response.addCookie(cookie);
                }
            }
        }
        catch (Exception ex) {
            System.out.println("清空Cookies发生异常！");
            ex.printStackTrace();
        }
    }
}