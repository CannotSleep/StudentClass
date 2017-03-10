package com.feicui.administrator.studentclass.util;

/**
 * Created by Administrator on 2016/12/28.
 */

public interface HttpApi {

    /**
     * 接口保存数据 public  static
     */
     String ADRESS="http://118.244.212.82:9092/newsClient/";

    String GETNEWS="http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=2&nid=10&stamp=20161013&cnt=20";

     String DESIGN="user_register?";

    String Login="user_login?";

    String HOME="user_home?";

     String VERSION="ver=1";

     String USER="&uid=";

    String MAIL="&email=";

    String PASSWORD="&pwd=";

    String DEVICE="device";

    String USERPOTRAIT="user_image?";

    String NEWSTYPE="news_sort?";

    String NEWS="news_list?";

}
