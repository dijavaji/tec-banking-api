package com.hackathon.bankingapp.mapper;

import com.hackathon.bankingapp.dto.AccountDto;
import com.hackathon.bankingapp.entity.Account;

public class AccountMapper {
	
	public static Account mapToAccount(AccountDto userDto) {
		Account account = new Account();
		//user.setCreatedBy(BankingConstants.CREATED_BY);
		return account;
	}
	
	public static AccountDto mapToAccountDto(AccountDto acc) {
		AccountDto accountDto = new AccountDto();
		return accountDto;
	}

}
