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

/**
 * 服务器定义
 */
public class ServerDef {

    protected final String name;
    public final int port;
    public final int bossThreads;
    public final int workThreads;
    public final ProtocolType protocolType;
    public final IMessageLogicExecutorBase messageLogicExecutor;// logic
    public final HandlerManager handlerManager;

    public final int maxFrameSize;
    public final int maxConnections;
    public final long clientIdleTimeout;
    public final IHandlerListener listener;

    public ServerDef(int bossThreads, int workThreads, String name, int port, int maxFrameSize, int maxConnections,
                     long clientIdleTimeout,
                     IMessageLogicExecutorBase messageLogicExecutor,
                     ProtocolType protocolType, HandlerManager handlerManager,
                     IHandlerListener listener) {
        this.bossThreads = bossThreads;
        this.workThreads = workThreads;
        this.name = name;
        this.port = port;
        this.messageLogicExecutor = messageLogicExecutor;
        this.protocolType = protocolType;
        this.handlerManager = handlerManager;
        this.maxFrameSize = maxFrameSize;
        this.maxConnections = maxConnections;
        this.clientIdleTimeout = clientIdleTimeout;
        this.listener = listener;

    }

    public static ServerDefBuilder newBuilder() {
        return new ServerDefBuilder();
    }

}
