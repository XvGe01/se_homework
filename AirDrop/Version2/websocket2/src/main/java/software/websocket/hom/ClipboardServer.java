package software.websocket.hom;
import java.awt.datatransfer.Clipboard;
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.time.*;
import java.lang.Thread;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.Toolkit;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
public class ClipboardServer {
    public static void main(String[] args) {
        SocketServie();
    }

    private static LocalDateTime dt = LocalDateTime.now();
    //创建socket
    private static ServerSocket socket;

    static {
        try {
            socket = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class SubRunnable implements Runnable {
        /**
         * 监听客户端的连接
         */
        public void run() {
            ListenClientConnect();
        }
    }

    public static int SocketServie() {
        /**启动服务器
         */
        System.out.println(dt.toString() + "  服务端已启动");
        String host = GetLocalIP();
        int port = 8888;
        System.out.println(dt.toString() + "  服务端IP:" + host);
        System.out.println(dt.toString() + "  服务端监听端口:" + port);
//监听客户端连接
        Thread myThread = new Thread(new SubRunnable());
        myThread.start();
        new Scanner(System.in).nextLine();
        return 0;
    }

    public static String GetLocalIP() {
        /**获取本地的IP地址
         */
        String AddressIP = "";
        try {
            InetAddress addr = InetAddress.getLocalHost();
            AddressIP = addr.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return AddressIP;
    }

    static int ListenClientConnect() {
        /**监听客户端的连接
         */
        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = socket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Socket finalClientSocket = clientSocket;
            Thread receiveThread = new Thread() {
                public void run() {
                    try {
                        ReceiveMessage(finalClientSocket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            receiveThread.start();
            return 0;
        }
    }

    public static int setClipboardString(String text) {
        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 封装文本内容
        Transferable trans = new StringSelection(text);
        // 把文本内容设置到系统剪贴板
        clipboard.setContents(trans, null);
        return 0;
    }

    private static void ReceiveMessage(Socket clientSocket) throws IOException {
        Socket myClientSocket = clientSocket;
        while (true) {
            try {
                //通过myClientSocket接收数据
                int receiveNumber = 0;
                try {
                    receiveNumber = myClientSocket.getReceiveBufferSize();
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                if (receiveNumber == 0) {
                    return;
                }
                //读取Client的消息
                BufferedReader br = new BufferedReader(new InputStreamReader(myClientSocket.getInputStream(), "utf-8"));
                String data = br.readLine();
                System.out.println(dt.toString() + "  接收客户端 " + myClientSocket.getRemoteSocketAddress().toString() + "的消息：\n" + data);
                //将客户端复制的内容设置到系统的剪切板
                setClipboardString(data);

                //给Client端返回信息
                String sendStr = "已成功接到您发送的消息";
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(myClientSocket.getOutputStream(), "utf-8"));
                bw.write(sendStr);
                //关闭Socket,释放资源
                myClientSocket.close();
                new Scanner(System.in).nextLine();
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
                //关闭Socket,释放资源
                myClientSocket.close();
                break;
            }
        }
    }
}

