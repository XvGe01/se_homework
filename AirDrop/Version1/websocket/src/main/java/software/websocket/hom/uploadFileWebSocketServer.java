package software.websocket.hom;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.net.InetAddress;

/**
 * ClassName: uploadFileWebSocketServer <br/>
 * Description: <br/>
 * date: 2022/5/15<br/>
 * @since JDK 1.8
 */
@ServerEndpoint("/upload/{sid}")
@Component
public class uploadFileWebSocketServer {
    private static final Logger LOG = LoggerFactory.getLogger(uploadFileWebSocketServer.class);
    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    static int onlineCount = 0;

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static CopyOnWriteArraySet<uploadFileWebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 保证文件对象和文件路径的唯一性
     */
    private HashMap docUrl;

    /**
     * 结束标识判断
     */
    private String endupload = "over";

    /**
     * 连接建立成功时调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) throws Exception{
        this.session = session;
        //加入set中
        webSocketSet.add(this);
        //在线人数加1
        addOnlineCount();
        System.out.println(sid + "连接成功" + "----当前在线人数为：" + onlineCount);
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println(addr.getHostAddress());
        /**
        try {
            //this.sendMessage(addr.getHostAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
         **/
//        return 0;
    }

    /**
     * 连接关闭时调用的方法
     */
    @OnClose
    public void onClose(@PathParam("sid") String sid) {
        //在线人数减1
        subOnlineCount();
        //从set中删除
        webSocketSet.remove(this);
        System.out.println(sid + "已关闭连接" + "----剩余在线人数为：" + onlineCount);
    }

    /**
     * 接收客户端发送的消息时调用的方法
     *
     * @param message 接收的字符串消息。该消息应当为json字符串
     */
    @OnMessage
    public void onMessage(String message, @PathParam("sid") String sid) {
        //前端传过来的消息都是一个json
        JSONObject jsonObject = JSON.parseObject(message);
        //消息类型
        String type = jsonObject.getString("type");
        //消息内容
        String data = jsonObject.getString("data");
        //判断类型是否为文件名
        if ("fileName".equals(type)) {
            LOG.info("传输文件为:" + data);
            System.out.println(data);
            //此处的 “.”需要进行转义
            /*String[] split = data.split("\\.");*/
            try {
                HashMap<String, Object> map = new HashMap<>();
                //根据时间生成文件夹路径
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String DocUrl = simpleDateFormat.format(date);
                //文件保存地址
                String path = "/websocketData/files/" + DocUrl;
                //创建文件
                File dest = new File(path+"/" +data);
                //如果文件已经存在就先删除掉
                if (dest.getParentFile().exists()) {
                    dest.delete();
                }
                map.put("dest", dest);
                map.put("path", path+"/" + data);
                map.put("nginxPath","/"+DocUrl+"/"+data);
                docUrl = (HashMap) map;
                this.sendMessage("ok");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if ("fileCount".equals(type)){
                LOG.info("传输第"+data+"份");
        }
        //判断是否结束
        else if (endupload.equals(type)) {
            LOG.info("===============>传输成功");
            //返回一个文件下载地址
            String path = (String) docUrl.get("path");
            //返回客户端文件地址
            try {
                this.sendMessage(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        return 0;
    }

    /**
     * 该方法用于接收字节流数组
     * @param message 文件字节流数组
     * @param session 会话
     */
    @OnMessage
    public void onMessage(byte[] message, Session session) {
        //群发消息
        try {
            FileOutputStream fstream = null;
            //从map中获取file对象
            File file = (File) docUrl.get("dest");
            //判断路径是否存在，不存在就创建
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                fstream = new FileOutputStream(file, true);
                fstream.write(message);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fstream != null) {
                    try {
                        fstream.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            this.sendMessage("ok");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 服务器主动提推送消息
     *
     * @param message 消息内容
     * @throws IOException io异常抛出
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 原子性的++操作
     */
    public static synchronized void addOnlineCount() {
        uploadFileWebSocketServer.onlineCount++;
    }

    /**
     * 原子性的--操作
     */
    public static synchronized void subOnlineCount() {
        uploadFileWebSocketServer.onlineCount--;
    }

}
