package software.websocket.hom;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;

class ClipboardServerTest {
    ClipboardServer clipboardServer;
    @BeforeEach
    void setUp() {
        this.clipboardServer = new ClipboardServer();
    }

    @AfterEach
    void tearDown() {
        this.clipboardServer = null;
    }

    @Test
    void getLocalIP() {
        String LoaclIp = this.clipboardServer.GetLocalIP();
        // 判断获取本地IP地址是否正确，“192.168.166.129”cmd窗口ipconfig获取
        assertEquals("192.168.166.129",LoaclIp);
    }

    @Test
    void setClipboardString() {
        this.clipboardServer.setClipboardString("aaa");
        // 获取剪贴板中的内容
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable trans = clipboard.getContents(null);
        String text="";
        try {
               text = (String) trans.getTransferData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //判断写入剪切板的内容和读取剪切板的内容是否一致
        assertEquals("aaa",text);
    }

     @Test
     void ListenClientConnect() {
     //判断是否与客户端建立socket连接，并接收客户端数据
     assertEquals(0,this.clipboardServer.ListenClientConnect());
     }

}