package com.zfoo.tank.common.util.randomcode;

public interface IdGenerator {
    String nextCode();
    long nextSerial();
    long getSerial(String code);
    String serialToCode(long serial);
}
