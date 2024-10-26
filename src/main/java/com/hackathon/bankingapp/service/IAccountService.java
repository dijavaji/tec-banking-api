package com.hackathon.bankingapp.service;

import com.hackathon.bankingapp.common.exception.BankingException;
import com.hackathon.bankingapp.dto.AccountDto;

public interface IAccountService {
	
	//AccountDto createAccount(AccountDto account) throws BankingException;
	//AccountDto updateAccount(AccountDto account, int id)throws BankingException;
	//void deleteAccount(Integer code)throws BankingException;
	//List<AccountDto> getListAccounts()throws BankingException;
	//AccountDto findOneByAccNumber(Integer code);
	//AccountDto getAccountById(Integer code) throws BankingException;
	
	AccountDto findOneByMailUser(String mail) throws BankingException;
}
