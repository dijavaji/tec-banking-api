package com.hackathon.bankingapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.bankingapp.common.exception.BankingException;
import com.hackathon.bankingapp.dto.AccountDto;
import com.hackathon.bankingapp.entity.Account;
import com.hackathon.bankingapp.repository.dao.IAccountDao;
import com.hackathon.bankingapp.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService{
	
	@Autowired
	private IAccountDao accountDao;

	@Override
	public AccountDto findOneByMailUser(String mail) throws BankingException {
		Account account = accountDao.findOneAccounByMailUser(mail);
		return null;
	}

}
