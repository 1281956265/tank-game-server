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

package com.zfoo.net.consumer.event;

import com.zfoo.event.model.IEvent;
import com.zfoo.net.consumer.registry.Register;
import com.zfoo.net.session.Session;

/**
 * @author godotg
 */
public class ConsumerStartEvent implements IEvent {

    private Register providerRegister;
    private Session session;

    public static ConsumerStartEvent valueOf(Register providerRegister, Session session) {
        var event = new ConsumerStartEvent();
        event.providerRegister = providerRegister;
        event.session = session;
        return event;
    }

    public Register getProviderRegister() {
        return providerRegister;
    }

    public void setProviderRegister(Register providerRegister) {
        this.providerRegister = providerRegister;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
