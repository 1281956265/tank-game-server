package com.zfoo.tank.common.cfg;

import java.util.Objects;

public class LuBanConfig {
  private String id;
  /**
   * json文件路径
   */
  private String resourcePath;
  /**
   * gen代码路径
   */
  private String genCodePath;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getResourcePath() {
    return resourcePath;
  }

  public void setResourcePath(String resourcePath) {
    this.resourcePath = resourcePath;
  }

  public String getGenCodePath() {
    return genCodePath;
  }

  public void setGenCodePath(String genCodePath) {
    this.genCodePath = genCodePath;
  }
}
