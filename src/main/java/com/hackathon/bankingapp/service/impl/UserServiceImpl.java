package com.hackathon.bankingapp.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackathon.bankingapp.common.BankingConstants;
import com.hackathon.bankingapp.common.exception.BankingException;
import com.hackathon.bankingapp.dto.SignUpDto;
import com.hackathon.bankingapp.dto.UserDto;
import com.hackathon.bankingapp.entity.Account;
import com.hackathon.bankingapp.entity.User;
import com.hackathon.bankingapp.entity.UserType;
import com.hackathon.bankingapp.mapper.UserMapper;
import com.hackathon.bankingapp.repository.IAccountRepository;
import com.hackathon.bankingapp.repository.IUserRepository;
import com.hackathon.bankingapp.repository.IUserTypeRepository;
import com.hackathon.bankingapp.repository.dao.IAccountDao;
import com.hackathon.bankingapp.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private IUserTypeRepository userTypeRepo;
	@Autowired
	private IAccountRepository accRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
	private IAccountDao accountDao;
	
	@Override
	@Transactional
	public UserDto createUser(SignUpDto signUpDto) throws BankingException {
		
		//check for email exists in a DB
        if(userRepository.existsByPhoneNumber(signUpDto.getPhoneNumber())){
           throw new BankingException("Phone Number is already taken");
        }

        // add check for email exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
        	throw new BankingException("Email is already taken!"); 
        }
		try {
			signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword())); 
			Optional<UserType> userType = userTypeRepo.findByName("user");
			User user = UserMapper.mapToUser(signUpDto);
			
			user.setUserType(userType.get());
			log.info("inserta user {}", signUpDto.getName());
			User newUser = userRepository.save(user);
			
			Account account = new Account();
			account.setBalance(0.0);
			account.setCreatedBy(BankingConstants.CREATED_BY);
			account.setUser(newUser);
			account.setAccountNumber(UUID.randomUUID());
			
			Account newAcc = this.accRepository.save(account);
			
			return UserMapper.mapToUserDto(newUser,newAcc.getAccountNumber());
		}catch(Exception e) {
			log.error("Error al momento de crear usuario ", e);
			throw new BankingException("Error al momento de crear usuario",e);
		}
	}

	@Override
	public UserDto getUserByEmail(String email) throws BankingException {
		Account acc= this.accountDao.findOneAccounByMailUser(email);
		return UserMapper.mapToUserDto(acc.getUser(), acc.getAccountNumber());
	}

}
