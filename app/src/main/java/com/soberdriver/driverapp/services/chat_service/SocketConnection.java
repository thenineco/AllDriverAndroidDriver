package com.soberdriver.driverapp.services.chat_service;

/**
 * Created by zest .
 */
public interface SocketConnection {

    void openConnection();

    void closeConnection();

    boolean isConnected();
}
