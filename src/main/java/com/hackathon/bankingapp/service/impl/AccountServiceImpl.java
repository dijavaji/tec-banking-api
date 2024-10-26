package com.hackathon.bankingapp.service.impl;

import org.springframework.stereotype.Service;

import com.hackathon.bankingapp.common.exception.BankingException;
import com.hackathon.bankingapp.dto.AccountDto;
import com.hackathon.bankingapp.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService{

	@Override
	public AccountDto findOneByMailUser(String mail) throws BankingException {
		// TODO Auto-generated method stub
		return null;
	}

}
