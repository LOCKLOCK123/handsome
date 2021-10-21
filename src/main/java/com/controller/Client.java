package com.bootdo.system.controller;

import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by linla on 2021/10/13.
 */
public class Client {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 8088;
        Socket socket = new Socket(host, port);
        OutputStream outputStream = socket.getOutputStream();
        String message = "你好  yiwangzhibujian";
        //首先需要计算得知消息的长度
        byte[] sendBytes = message.getBytes("UTF-8");
        //然后将消息再次发送出去
        outputStream.write(sendBytes);
        outputStream.flush();

    }
}
