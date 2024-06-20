package com.zfoo.robot;

import com.zfoo.net.NetContext;
import com.zfoo.net.core.HostAndPort;
import com.zfoo.net.session.Session;
import com.zfoo.protocol.util.JsonUtils;
import com.zfoo.protocol.util.ThreadUtils;
import com.zfoo.robot.client.RobotTcpClient;
import com.zfoo.robot.client.RobotWebsocketClient;
import com.zfoo.tank.common.protocol.cache.ScoreRankRequest;
import com.zfoo.tank.common.protocol.cache.ScoreRankResponse;
import com.zfoo.tank.common.protocol.login.LoginRequest;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Slf4j
public class Client {
  public final String userName;

  public static final String PASSWORD_DEFAULT = "123456";
  private String host;
  private int port;
  public ClientType clientType;

  public Session session;

  public long userId;


  public Client(String userName) {
    this.userName = userName;
  }

  private void op() throws Exception {
    var scoreRankRequest = ScoreRankRequest.valueOf();
    // 发送一个获取分数排行榜的信息 收到ScoreRankResponse后去寻找@PacketReceiver注解调用
    NetContext.getRouter().send(session, scoreRankRequest);
    // 同步发送一个获取分数排行榜的信息  直接返回response
    var scoreRankSyncResponseSync = NetContext.getRouter().syncAsk(session, scoreRankRequest, ScoreRankResponse.class, null).packet();
    // 异步发送一个获取分数排行榜的信息  收到response调用回调方法打印"@@@@@@@@@@@@@@@  get resp={}"
    NetContext.getRouter().asyncAsk(session, scoreRankRequest, ScoreRankResponse.class, null)
      .whenComplete(resp -> log.info("@@@@@@@@@@@@@@@  get resp={}", resp));

    ThreadUtils.sleep(Integer.MAX_VALUE);

  }

  public void start(CollectInfo info) throws Throwable {
    this.host = info.host;
    if (info.port <= 0) {
      this.port = info.clientType.port;
    } else {
      this.port = info.port;
    }
    this.clientType = info.clientType;

    if (clientType == ClientType.TCP) {
      connectTcp();
    } else if (clientType == ClientType.WEB_SOCKET) {
      connectWebSocket();
    } else {
      log.error("client type error! type={}", clientType);
      throw new RuntimeException("client type error!");
    }

    //登录
    NetContext.getRouter().send(session, LoginRequest.valueOf(userName, PASSWORD_DEFAULT), null);

    op();
  }

  public void connectTcp() {
    var client = new RobotTcpClient(HostAndPort.valueOf(host, port));
    session = client.start();
  }

  public void connectWebSocket() {
    HostAndPort hostAndPort = HostAndPort.valueOf(host, port);
    var webSocketClientProtocolConfig = WebSocketClientProtocolConfig.newBuilder()
      .webSocketUri("ws://" + hostAndPort.toHostAndPortStr() + "/websocket")
      .build();

    var client = new RobotWebsocketClient(hostAndPort, webSocketClientProtocolConfig);
    session = client.start();
  }

  public void close() {
    session.close();
  }

  public enum ClientType {
    TCP(16000),
    WEB_SOCKET(18000);

    public int port;

    ClientType(int port) {
      this.port = port;
    }
  }
}
