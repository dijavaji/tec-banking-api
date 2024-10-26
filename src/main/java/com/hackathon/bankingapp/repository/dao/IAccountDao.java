package com.hackathon.bankingapp.repository.dao;

import com.hackathon.bankingapp.common.exception.BankingException;
import com.hackathon.bankingapp.entity.Account;

public interface IAccountDao {
	
	Account findOneAccounByMailUser(String mail) throws BankingException;

}
