package org.jsp.bank.DAO;

import org.jsp.bank.Bank;

public interface BankDAO {
	void userRegistration(Bank a);
	void credit(String a,String p);
	void debit(String a,String p);
	void changingThePassword(String a,String p);
	void mobileToMobileTranscation(String m1, String m2);
	void checkBalance(String a,String p);
	
}
