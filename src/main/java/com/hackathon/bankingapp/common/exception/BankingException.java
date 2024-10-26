package com.hackathon.bankingapp.common.exception;

public class BankingException extends RuntimeException{
	
	public BankingException() {
        super();
    }
    
	public BankingException (String msg, Throwable nested) {
        super(msg, nested);
    }
    
	public BankingException (String message) {
        super(message);
    }
    
	public BankingException(Throwable nested) {
        super(nested);
	}
	
	private static final long serialVersionUID = 1L;

}