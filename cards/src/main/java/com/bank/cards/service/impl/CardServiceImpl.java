package com.bank.cards.service.impl;

import com.bank.cards.constants.CardConstants;
import com.bank.cards.dto.CardDto;
import com.bank.cards.entity.Cards;
import com.bank.cards.exception.CardAlreadyExistsException;
import com.bank.cards.exception.ResourceNotFoundException;
import com.bank.cards.mapper.CardMapper;
import com.bank.cards.repository.CardRepository;
import com.bank.cards.service.IcardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class CardServiceImpl implements IcardsService {

    @Autowired
    CardRepository cardRepository;

    @Override
    public void createCard(String mobileNumber) {

        Cards cards=createNewCard(mobileNumber);
        cardRepository.save(cards);

    }

    public Cards createNewCard(String mobileNumber){

        Optional<Cards> cardsOptional=cardRepository.findByMobileNumber(mobileNumber);
        if (cardsOptional.isPresent())
            throw new CardAlreadyExistsException("Card with Mobile Number "+mobileNumber+" already exists");
        Cards card= new Cards();
        card.setCardNumber(1000000000L+new Random().nextInt(999999999));
        card.setCardType(CardConstants.CREDIT_CARD);
        card.setMobileNumber(mobileNumber);
        card.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
        return card;
    }

    @Override
    public CardDto readCard(String mobileNumber) {

        Optional<Cards> cardsOptional=cardRepository.findByMobileNumber(mobileNumber);
        if (cardsOptional.isEmpty())
            throw new ResourceNotFoundException("card", "mobile number", mobileNumber);
        CardDto cardDto= CardMapper.mapCardEntityToDto(cardsOptional.get(),new CardDto());

        return cardDto;
    }

    @Override
    public boolean updateCard(CardDto cardDto) {
        boolean isUpdated=false;
        Optional<Cards> cardsOptional=cardRepository.findByMobileNumber(cardDto.getMobileNumber());
        if (cardsOptional.isEmpty())
            throw new ResourceNotFoundException("card", "mobile number", cardDto.getMobileNumber());
        Cards cards=CardMapper.mapCardDtoToEntity(cardDto,cardsOptional.get());
        cardRepository.save(cards);
        isUpdated=true;
        return isUpdated;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        boolean isDeleted=false;
        Optional<Cards> cardsOptional=cardRepository.findByMobileNumber(mobileNumber);
        if (cardsOptional.isEmpty())
            throw new ResourceNotFoundException("card", "mobile number", mobileNumber);
        cardRepository.deleteById(cardsOptional.get().getCardId());
        isDeleted=true;
        return isDeleted;
    }
}
