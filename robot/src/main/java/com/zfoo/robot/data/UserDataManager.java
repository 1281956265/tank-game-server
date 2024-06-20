package com.zfoo.robot.data;

import com.zfoo.scheduler.anno.Scheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public abstract class UserDataManager {

  private static final Map<Long, UserData> users = new ConcurrentHashMap<>();


  public static void addUser(UserData data) {
    if (data == null) {
      log.error("data is null!");
      return;
    }
    UserData oldData = users.get(data.playerId);
    if (null != oldData) {
      log.error("player {} is in players, refresh", data.playerId);
    }
    users.put(data.playerId, data);
    log.info("add player, {}", data);
  }

  public static UserData getUser(long userId) {
    return users.get(userId);
  }

  public static void removeUser(long userId) {
    users.remove(userId);
    log.info("remove player = {}", userId);
  }

  @Scheduler(cron = "0/30 * * * * ?")
  public void hardBeatScheduler() {

  }
}
