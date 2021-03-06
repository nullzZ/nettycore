/*
 * Copyright (C) 2012-2013 Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package game.core.nettyCore.serverDef;

import game.core.nettyCore.HandlerManager;
import game.core.nettyCore.IHandlerListener;
import game.core.nettyCore.IMessageLogicExecutorBase;
import game.core.nettyCore.coder.ProtocolType;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class ServerDefBuilderBase<T extends ServerDefBuilderBase<T>> {
    private static final AtomicInteger ID = new AtomicInteger(1);
    protected int bossThreads;
    protected int workThreads;
    protected String name = "netty-" + ID.getAndIncrement();
    protected int serverPort = 8081;
    protected ChannelInitializer<SocketChannel> channelInitializer;
    protected int maxFrameSize = MAX_FRAME_SIZE;
    protected int maxConnections;
    protected long clientIdleTimeout;// hasDefault
    protected ProtocolType protocolType;
    protected IMessageLogicExecutorBase messageLogicExecutor;// hasDefault
    protected HandlerManager handlerManager;
    protected String hanlderPackageName;
    protected IHandlerListener listener;
    protected boolean isSpring;

    // private HttpResourceHandler httpResourceHandler;// hasDefault
    // private HttpHandlerFactory httpHandlerFactory;// hasDefault

    /**
     * The default maximum allowable size for a single incoming thrift request
     * or outgoing thrift response. A server can configure the actual maximum to
     * be much higher (up to 0x7FFFFFFF or almost 2 GB). This default could also
     * be safely bumped up, but 64MB is chosen simply because it seems
     * reasonable that if you are sending requests or responses larger than
     * that, it should be a conscious decision (something you must manually
     * configure).
     */
    private static final int MAX_FRAME_SIZE = 64 * 1024 * 1024;


    public ServerDefBuilderBase() {
        this.handlerManager = new HandlerManager();
    }


    @SuppressWarnings("unchecked")
    public T name(String name) {
        this.name = name;
        return (T) this;
    }

    public T bossThreads(int num) {
        this.bossThreads = num;
        return (T) this;
    }

    public T workThreads(int num) {
        this.workThreads = num;
        return (T) this;
    }

    /**
     * Listen to this port.
     */
    @SuppressWarnings("unchecked")
    public T listen(int serverPort) {
        this.serverPort = serverPort;
        return (T) this;
    }


    @SuppressWarnings("unchecked")
    public T messageLogicExecutor(IMessageLogicExecutorBase messageLogicExecutor) {
        this.messageLogicExecutor = messageLogicExecutor;
        return (T) this;
    }


    @SuppressWarnings("unchecked")
    public T limitFrameSizeTo(int maxFrameSize) {
        this.maxFrameSize = maxFrameSize;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T limitConnectionsTo(int maxConnections) {
        this.maxConnections = maxConnections;
        return (T) this;
    }

    public T isSpring(boolean isSpring) {
        this.isSpring = isSpring;
        return (T) this;
    }


    /**
     * @param clientIdleTimeout 秒
     * @return
     */
    @SuppressWarnings("unchecked")
    public T clientIdleTimeout(long clientIdleTimeout) {
        this.clientIdleTimeout = clientIdleTimeout;
        return (T) this;
    }


    /**
     * hanlder存放的包名
     *
     * @param hanlderPackageName
     * @return
     */
    @SuppressWarnings("unchecked")
    public T handlerPackage(String hanlderPackageName) {
        this.hanlderPackageName = hanlderPackageName;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T protocolType(ProtocolType protocolType) {
        this.protocolType = protocolType;
        return (T) this;
    }


    public T handlerListener(IHandlerListener listener) {
        this.listener = listener;
        return (T) this;
    }


    public ServerDef build() throws Exception {
        try {
            checkState(hanlderPackageName != null, "hanlderPackageName not defined!");

            checkState(protocolType != null, "potocolType not defined!");

            checkState(messageLogicExecutor != null, "messageLogicExecutor not defined!");

            this.handlerManager.init(hanlderPackageName, isSpring);

            return new ServerDef(bossThreads, workThreads, name, serverPort, maxFrameSize, maxConnections, clientIdleTimeout,
                    messageLogicExecutor, protocolType,
                    handlerManager, listener);
        } catch (Exception e) {
            throw e;
        }
    }

    public static void checkState(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalStateException(String.valueOf(errorMessage));
        }
    }
}