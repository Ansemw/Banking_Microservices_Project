package com.bank.cards.service;

import com.bank.cards.dto.CardDto;

import java.net.InterfaceAddress;

public interface IcardsService {

    public void createCard(String mobileNumber);
    public CardDto readCard(String mobileNumber);
    public boolean updateCard(CardDto cardDto);
    public boolean deleteCard(String mobileNumber);


}
