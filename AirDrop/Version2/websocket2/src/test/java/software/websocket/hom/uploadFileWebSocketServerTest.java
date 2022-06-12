package software.websocket.hom;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class uploadFileWebSocketServerTest {
    uploadFileWebSocketServer fileWebSocket;

    @BeforeEach
    void setUp()
    {
        this.fileWebSocket = new uploadFileWebSocketServer();
    }

    @AfterEach
    void tearDown() {
        this.fileWebSocket = null;
    }

    @Test
    void addOnlineCount() {
        fileWebSocket.addOnlineCount();
        assertEquals(1, fileWebSocket.onlineCount);
    }

//    @Test
//    void onOpen() throws Exception {
//        assertEquals(0, fileWebSocket.onOpen(null, "iufhjwhef1"));
//    }
//
//    @Test
//    void onMessage() {
//        String filejson = "{\"data\":1,\"type\":\"fileCount\"}";
//        assertEquals(0, fileWebSocket.onMessage(filejson, "iufhjwhef1"));
//    }

//    @Test
//    void subOnlineCount() {
//        fileWebSocket.subOnlineCount();
//        assertEquals(-1, fileWebSocket.onlineCount);
//    }

}