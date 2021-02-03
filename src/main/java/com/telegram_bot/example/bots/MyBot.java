package com.telegram_bot.example.bots;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component
public class MyBot extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(MyBot.class);

    @Value("${telegrambot.userName}")
    private String botUsername;

    @Value("${telegrambot.botToken}")
    private String botToken;


    //    private static final String USERNAME = "Tularem1a_bot";
//    private static final String TOKEN = "1643219739:AAG3l1qHvmunl_XWJX9_7G9kD9bOUU9xiBA";


    @Override
    public void onUpdateReceived(Update update) {
        logger.info("message: {}, text: {}", update.getMessage().getChatId(), update.getMessage().getText());
        String chatId = update.getMessage().getChatId().toString();

        try {
            SendMessage message = SendMessage.builder().chatId(chatId).build();
            execute(message).setText("Hi! " + update.getMessage().getText());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @PostConstruct
    public void start() {
        logger.info("username: {}, token: {}", botUsername, botToken);
    }
}