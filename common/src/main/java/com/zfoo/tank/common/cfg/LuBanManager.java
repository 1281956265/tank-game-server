/*
 * Copyright (C) 2020 The zfoo Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package com.zfoo.tank.common.cfg;

import cfg.Tables;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author godotg
 */
public class LuBanManager implements ILuBanManager {

    private static final Logger logger = LoggerFactory.getLogger(LuBanContext.class);

    private LuBanConfig luBanConfig;

    private Tables tables;

    @Override
    public Tables getTables() {
        return tables;
    }

    public LuBanConfig getLuBanConfig() {
        return luBanConfig;
    }

    public void setLuBanConfig(LuBanConfig luBanConfig) {
        this.luBanConfig = luBanConfig;
    }

    @Override
    public void initBefore() {

    }

    @Override
    public void inject() {
        try {

            PathMatchingResourcePatternResolver resoutceBuilder = new PathMatchingResourcePatternResolver();
            tables = new Tables(file -> {
                Resource resource = Arrays.stream(resoutceBuilder.getResources(luBanConfig.getResourcePath() + file + ".json")).findFirst().get();
                String jsonStr = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                return JsonParser.parseString(jsonStr);
            });
            // 上面这种方式已经成功了！！！！！！！！！！！   但是有更好的加载resources的方法？？？
//            ResourceUtils.getFile(luBanConfig.getResourcePath() + "common_tbglobalconfig.json");
//            Tables tables = new Tables(file -> JsonParser.parseString(
//              new String(Files.readAllBytes(Paths.get(luBanConfig.getResourcePath(), file + ".json")), "UTF-8")));
//            System.out.println(tables);
        } catch (IOException e) {
            logger.error("init luban tables error!!!!!", e);
            throw new RuntimeException();
        }
    }

    @Override
    public void initAfter() {

    }
}
