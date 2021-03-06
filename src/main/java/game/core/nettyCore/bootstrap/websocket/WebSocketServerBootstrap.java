package game.core.nettyCore.bootstrap.websocket;

import game.core.nettyCore.bootstrap.IServerBootstrap;
import game.core.nettyCore.serverDef.ServerDef;
import game.core.nettyCore.websocket.DefaultWebSocketChannelInstaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketServerBootstrap extends WebSocketCommonServer implements IServerBootstrap {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServerBootstrap.class);

    private final ServerDef serverDef;

    public WebSocketServerBootstrap(ServerDef serverDef) {
        this.serverDef = serverDef;
        Runtime.getRuntime().addShutdownHook(new Thread(this));
    }


    @Override
    public void run() {
        logger.info("server closing...");
        close();
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public void start() throws Exception {
//        if (serverDef.messageLogicExecutor != null) {
//            super.start(serverDef.port, new DefaultWebSocketChannelInstaller(serverDef.messageLogicExecutor,
//                    serverDef.handlerManager, serverDef.protocolType, serverDef.listener));
//        } else {
        super.start(serverDef.port, new DefaultWebSocketChannelInstaller(serverDef.messageLogicExecutor,
                        serverDef.handlerManager,
                        serverDef.protocolType,
                        serverDef.listener),
                serverDef.bossThreads, serverDef.workThreads);
//        }
    }

}
