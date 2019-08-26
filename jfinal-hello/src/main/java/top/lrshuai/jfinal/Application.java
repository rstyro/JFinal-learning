package top.lrshuai.jfinal;

import com.jfinal.server.undertow.UndertowServer;
import top.lrshuai.jfinal.config.MyJFinalConfig;

public class Application {
    public static void main(String[] args) {
        UndertowServer.start(MyJFinalConfig.class, 80, true);
    }
}
