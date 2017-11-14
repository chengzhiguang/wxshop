package com.chengzg.wxshop.utils;

import com.chengzg.wxshop.enums.WxMessageType;
import com.chengzg.wxshop.model.vo.Article;
import com.chengzg.wxshop.model.vo.NewsMessage;
import com.chengzg.wxshop.model.vo.TextMessage;
import com.chengzg.wxshop.model.vo.ViewMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/9.
 */
public class WxUtility {
    private static Logger logger = LoggerFactory.getLogger(WxUtility.class);

    public final static String GET_WX_TOKEN="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+PropertiesUtil.getPropValAsString("wx_app_id")+"&secret="+PropertiesUtil.getPropValAsString("wx_app_secret")+"&grant_type=authorization_code";

    public final static String get_WX_USERINFO="https://api.weixin.qq.com/sns/userinfo?lang=zh_CN";


    /**
     * 解析 XML 把解析的数据放到 map 中
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXML(String xmlData) throws Exception {
        Document document = DocumentHelper.parseText(xmlData);
        Element rootElt = document.getRootElement();

        List<Element> list = rootElt.elements();
        Map<String, String> map = new HashMap<String, String>();
        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }
        return map;
    }

    /**
     * 文本信息
     *
     * @param msg
     * @return
     * @throws Exception
     */
    public String handleTextMsg(Map<String, String> msg) {
        String respMsg=null;

        String fromUserName=msg.get("FromUserName");

        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(msg.get("FromUserName"));
        newsMessage.setFromUserName(msg.get("ToUserName"));
        newsMessage.setCreateTime(new Date().getTime());
        String content = msg.get("Content");
        logger.info("handleTextMsg 传入的消息值为："+content);
        return respMsg;
    }


    /**
     * 发送文本信息
     * @param msg
     * @return
     */
    public static String sendTxtMessage(Map<String, String> msg,String content){
        TextMessage textMessage=new TextMessage();
        textMessage.setToUserName(msg.get("FromUserName"));
        logger.info("sendTxtMessage ToUserName:" + textMessage.getToUserName());
        textMessage.setFromUserName(msg.get("ToUserName"));
        logger.info("sendTxtMessage FromUserName:" + textMessage.getFromUserName());
        textMessage.setCreateTime(new Date().getTime());
        logger.info("sendTxtMessage CreateTime:" + textMessage.getCreateTime());

        textMessage.setMsgType(WxMessageType.TEXT.getType());
        logger.info("sendTxtMessage MsgType:" + textMessage.getMsgType());

        textMessage.setContent(content);

//		System.out.println(textMessageToXml(textMessage));
        return textMessageToXml(textMessage);
    }

    /**
     * 文本消息对象转换为 XML
     *
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 文本消息对象转换为 XML
     *
     * @param textMessage
     * @return
     */
    public static String viewMessageToXml(ViewMessage viewMessage) {
        xstream.alias("xml", viewMessage.getClass());
        return xstream.toXML(viewMessage);
    }

    /**
     * 图文消息对象转换为 XML
     *
     * @param newsMessage
     * @return
     */
    public static String newMessageToXml(NewsMessage newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new Article().getClass());
//		xstream.addImplicitCollection(NewsMessage.class, "Articles");
        return xstream.toXML(newsMessage);
    }

    /**
     * 扩展xstream，使其支持CDATA块
     *
     * @date 2013-05-19
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                    logger.info("startNode name:" + name + "    clazz" + clazz.toString());
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    logger.info("writeText text:" + text);
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
}
