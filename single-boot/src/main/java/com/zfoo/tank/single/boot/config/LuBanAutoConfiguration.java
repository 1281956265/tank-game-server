/*
 * Copyright (C) 2020 The zfoo Authors
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package com.zfoo.tank.single.boot.config;

import com.zfoo.tank.common.cfg.LuBanConfig;
import com.zfoo.tank.common.cfg.LuBanContext;
import com.zfoo.tank.common.cfg.LuBanManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author godotg
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(LuBanConfig.class)
public class LuBanAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(LuBanConfig.class)
    public LuBanManager luBanManager(LuBanConfig luBanConfig) {
        var luBanManager = new LuBanManager();
        luBanManager.setLuBanConfig(luBanConfig);
        return luBanManager;
    }

    @Bean
    @ConditionalOnMissingBean
    public LuBanContext luBanContext() {
        return new LuBanContext();
    }

}
