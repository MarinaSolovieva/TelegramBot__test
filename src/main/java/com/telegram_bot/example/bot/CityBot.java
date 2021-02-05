package com.telegram_bot.example.bot;

import com.telegram_bot.example.exception_handling.exceptions.NoSuchCityException;
import com.telegram_bot.example.model.dto.CityResponseDTO;
import com.telegram_bot.example.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import javax.annotation.PostConstruct;

@Component
public class CityBot extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(CityBot.class);

    @Autowired
    private CityService cityService;

    @Value("${telegrambot.userName}")
    private String botUsername;
    @Value("${telegrambot.botToken}")
    private String botToken;

    @Override
    public void onUpdateReceived(Update update) {
        logger.info("message: {}, text: {}", update.getMessage().getChatId(), update.getMessage().getText());
        String chatId = update.getMessage().getChatId().toString();
        try {
            execute(new SendMessage(chatId, loadCityDescription(update)));
        } catch (TelegramApiException e) {
            logger.error("Exception during loading city desciprtion", e);
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

    private String loadCityDescription(Update update) {
        if (update.getMessage().getText() == null) {
            return "Укажите имя города!";
        }
        CityResponseDTO responseDTO;
        try {
            responseDTO = cityService.findByName(update.getMessage().getText());
        } catch (NoSuchCityException e) {
            return "Города с таким названием в базе нет.";
        }
        return responseDTO.getDescription();
    }
}