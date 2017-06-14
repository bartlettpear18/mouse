package com.bartlettpear18gmail.mouse;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Joel.Bartlett18 on 6/11/2017.
 */
public class Device extends RealmObject {
    @PrimaryKey
    private String name;

    private int ipAddress;
    private int port;
    private double dimensionX;
    private double dimensionY;

    public Device() {
        System.out.println("New Device Made");
    }

    //Accessor methods
    public String getName() { return name; }
    public int getIp() { return ipAddress; }
    public int getPort() { return port; }
    public double getX() { return dimensionX; }
    public double getY() { return dimensionY; }

    //Mutator methods
    public void setName(String name) { this.name = name; }
    public void setIpAddress(int ipAddress) { this.ipAddress = ipAddress; }
    public void setPort(int port) { this.port = port; }
    public void setDimensionX(double x) { dimensionX = x; }
    public void setDimensionY(double y) { dimensionY = y; }

}
