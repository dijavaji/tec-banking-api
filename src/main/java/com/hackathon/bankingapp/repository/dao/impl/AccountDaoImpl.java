package com.hackathon.bankingapp.repository.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hackathon.bankingapp.common.exception.BankingException;
import com.hackathon.bankingapp.entity.Account;
import com.hackathon.bankingapp.entity.User;
import com.hackathon.bankingapp.repository.dao.IAccountDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class AccountDaoImpl implements IAccountDao{
	
	@Autowired
	private EntityManager em;
	
	@Override
	public Account findOneAccounByMailUser(String mail) throws BankingException {
		Account result = null;
		log.info("busco Acc findOneAccounByMailUser ");
		try {
			CriteriaBuilder cb = this.em.getCriteriaBuilder();
			CriteriaQuery<Account> query = cb.createQuery(Account.class);
			Root<Account> root = query.from(Account.class);
			Join<Account, User> join = root.join("user", JoinType.INNER);

			List <Predicate>filterPredicates = new ArrayList<>();
			//filterPredicates.add(cb.equal(root.get("stock.country.id"), countryId));
			//filterPredicates.add(cb.and(cb.equal(join.get("id") , root.get("stock"))));
			filterPredicates.add(cb.equal(join.get("email"), mail));
			
			query.where(filterPredicates.toArray(new Predicate[0]));
			result = em.createQuery(query).getSingleResult();
			log.info("consulta findByStockId " + result);
			
		
		}catch(Exception e) {
			log.error("Error al momento de consultar la linea de inventario " ,e);
			throw new BankingException("Error al momento de consultar la linea de inventario" ,e);
		}
		return result;
	}

}
