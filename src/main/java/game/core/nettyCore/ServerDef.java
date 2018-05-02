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
package game.core.nettyCore;

import game.core.nettyCore.coder.ProtocolType;
import game.core.nettyCore.defaults.DefaultChannelInitializer;
import game.core.nettyCore.proto.IProtocolFactorySelector;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class ServerDef {
    protected final String name;
    public final int port;
    public final ChannelInitializer<SocketChannel> channelInitializer;
    public final ProtocolType protocolType;
    public final IProtocolFactorySelector protocolFactorySelector;
    public final AbstractMessageLogicExecutorBase messageLogicExecutor;// logic
    public final HandlerManager handlerManager;

    public final int maxFrameSize;
    public final int maxConnections;
    public final long clientIdleTimeout;
    public final IExecutorCallBack executorCallBack;

    public ServerDef(String name, int port, int maxFrameSize, int maxConnections,
                     ChannelInitializer<SocketChannel> channelInitializer, long clientIdleTimeout,
                     AbstractMessageLogicExecutorBase messageLogicExecutor, IProtocolFactorySelector protocolFactorySelector,
                     ProtocolType protocolType, HandlerManager handlerManager, IExecutorCallBack executorCallBack) {
        this.name = name;
        this.port = port;

        if (channelInitializer == null) {
            this.channelInitializer = new DefaultChannelInitializer(this);
        } else {
            this.channelInitializer = channelInitializer;
        }
        this.messageLogicExecutor = messageLogicExecutor;
        this.protocolFactorySelector = protocolFactorySelector;
        this.protocolType = protocolType;
        this.handlerManager = handlerManager;


        this.maxFrameSize = maxFrameSize;
        this.maxConnections = maxConnections;
        this.clientIdleTimeout = clientIdleTimeout;
        this.executorCallBack = executorCallBack;
    }

    public static ServerDefBuilder newBuilder() {
        return new ServerDefBuilder();
    }

}