package com.code.service;

import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@Service
public class TelegramBot {

    public void sendMessage(String message, String tokenId, String chatId)
    {
        String urlString = "https://api.telegram.org/bot" +  tokenId
                + "/sendMessage?parse_mode=html&chat_id="
                + chatId + "&text=" + message;

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
