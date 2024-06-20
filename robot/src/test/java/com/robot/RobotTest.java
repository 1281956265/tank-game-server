package com.robot;

import com.zfoo.robot.Client;
import com.zfoo.robot.CollectInfo;
import com.zfoo.tank.common.constant.TankDeployEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Slf4j
public class RobotTest {

  static {
    TankDeployEnum.initDefaultEnv();
  }

  @Test
  public void test() {
    var context = new ClassPathXmlApplicationContext("my-tank-application.xml");

    final String name = "robot";
    final String host = "127.0.0.1";
    final int count = 1;
    final Client.ClientType clientType = Client.ClientType.TCP;
    CollectInfo info = new CollectInfo(name, host, count, clientType);

    ExecutorService executor = Executors.newFixedThreadPool(30, new ThreadFactory() {
      @Override
      public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(false);
        return thread;
      }
    });

    if (info.robotCount <= 0) {
      info.robotCount = 1;
    }

    for (int i = 0; i < info.robotCount; i++) {
      final String userName = String.format("%s_%d", info.name, i);
      executor.submit(() -> {
        try {
          Client client = new Client(userName);
          client.start(info);
          client.close();
        } catch (Throwable e) {
          log.error("submit error! ", e);
        }
      });
    }

    try {
      new CountDownLatch(1).await();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

  }
}
