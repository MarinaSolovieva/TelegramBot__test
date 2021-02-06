package com.telegram_bot.example.bot;

import com.telegram_bot.example.exception_handling.exception.NoSuchCityException;
import com.telegram_bot.example.model.dto.CityResponseDTO;
import com.telegram_bot.example.service.CityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class CityBot extends TelegramLongPollingBot {
    private static final Logger logger = LogManager.getLogger(CityBot.class);

    private final CityService cityService;
    private final String botUsername;
    private final String botToken;

    @Autowired
    public CityBot(CityService cityService,
                   @Value("${telegram.bot.userName}") String botUsername,
                   @Value("${telegram.bot.botToken}") String botToken) {
        this.cityService = cityService;
        this.botUsername = botUsername;
        this.botToken = botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        logger.info("Start method onUpdateReceived with text = {}", update.getMessage().getText());
        final String chatId = update.getMessage().getChatId().toString();
        try {
            execute(new SendMessage(chatId, loadCityDescription(update)));
        } catch (TelegramApiException e) {
            logger.error("Exception during loading city description", e);
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