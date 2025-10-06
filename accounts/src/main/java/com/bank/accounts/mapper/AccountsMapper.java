package com.bank.accounts.mapper;

import com.bank.accounts.dto.AccountsDto;
import com.bank.accounts.entity.Account;

public class AccountsMapper {

    public static AccountsDto mapToAccountsDto(Account account, AccountsDto accountsDto){

        accountsDto.setAccountNumber(account.getAccountNumber());
        accountsDto.setAccountType(account.getAccountType());
        accountsDto.setBranchAddress(account.getBranchAddress());

        return accountsDto;
    }

    public static Account mapToAccount(Account account, AccountsDto accountsDto){

        account.setAccountNumber(accountsDto.getAccountNumber());
        account.setAccountType(accountsDto.getAccountType());
        account.setBranchAddress(accountsDto.getBranchAddress());

        return account;
    }
}
