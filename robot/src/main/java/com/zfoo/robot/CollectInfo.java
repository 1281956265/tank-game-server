package com.zfoo.robot;

public class CollectInfo {

  public String name;
  public String host;
  public int port;
  public int robotCount;
  public Client.ClientType clientType;

  public CollectInfo(String name, String host, int robotCount, Client.ClientType clientType) {
    this.name = name;
    this.host = host;
    this.robotCount = robotCount;
    this.clientType = clientType;
  }
}
