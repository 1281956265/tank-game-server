package com.zfoo.robot.handler;

import com.zfoo.net.anno.PacketReceiver;
import com.zfoo.net.session.Session;
import com.zfoo.robot.data.UserData;
import com.zfoo.robot.data.UserDataManager;
import com.zfoo.tank.common.protocol.login.GetPlayerInfoResponse;
import com.zfoo.tank.common.protocol.login.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jaysunxiao
 */
@Component
@Slf4j
public class LoginController {

    // 保存token
    private static final Map<Long, String> tokens = new ConcurrentHashMap<>();

    @PacketReceiver
    public void atLoginResponse(Session session, LoginResponse response) {
        var token = response.getToken();
        log.info("客户端收到服务器的登录令牌返回[token:{}]", token);

        tokens.put(session.getSid(), token);
    }


    @PacketReceiver
    public void atGetPlayerInfoResponse(Session session, GetPlayerInfoResponse response) {
        var playerInfo = response.getPlayerInfo();
        var currencyVo = response.getCurrencyVo();

        log.info("玩家基础信息[id:{}][name:{}]", playerInfo.getId(), playerInfo.getName());

        UserData userData = new UserData();
        userData.playerId = playerInfo.getId();
        userData.token = tokens.get(session.getSid());
        userData.playerInfo = playerInfo;
        userData.currencyVo = currencyVo;
        UserDataManager.addUser(userData);
    }

}
