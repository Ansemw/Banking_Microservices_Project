package com.bank.cards.mapper;

import com.bank.cards.dto.CardDto;
import com.bank.cards.entity.Cards;

public class CardMapper {

    public static CardDto mapCardEntityToDto(Cards entity, CardDto dto) {
        dto.setCardId(entity.getCardId());
        dto.setMobileNumber(entity.getMobileNumber());
        dto.setCardNumber(entity.getCardNumber());
        dto.setCardType(entity.getCardType());
        dto.setTotalLimit(entity.getTotalLimit());
        dto.setAmountUsed(entity.getAmountUsed());
        dto.setAvailableAmount(entity.getAvailableAmount());
        return dto;
    }

    public static Cards mapCardDtoToEntity(CardDto dto, Cards entity) {
        entity.setCardId(dto.getCardId());
        entity.setMobileNumber(dto.getMobileNumber());
        entity.setCardNumber(dto.getCardNumber());
        entity.setCardType(dto.getCardType());
        entity.setTotalLimit(dto.getTotalLimit());
        entity.setAmountUsed(dto.getAmountUsed());
        entity.setAvailableAmount(dto.getAvailableAmount());
        return entity;
    }


}
