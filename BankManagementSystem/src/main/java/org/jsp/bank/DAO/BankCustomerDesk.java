package org.jsp.bank.DAO;

public class BankCustomerDesk {
	public static BankDAO customerDesk() {
		BankDAO bankDAO=new BankDAOImp();
		return bankDAO;
		
	}
}
