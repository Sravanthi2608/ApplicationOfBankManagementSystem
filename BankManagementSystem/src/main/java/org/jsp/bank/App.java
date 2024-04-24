package org.jsp.bank;
import java.util.Random;
import java.util.Scanner;

import org.jsp.bank.DAO.BankCustomerDesk;
import org.jsp.bank.DAO.BankDAO;

public class App 
{
    public static void main( String[] args )
    {
    	Random rand=new Random();
    	BankDAO bankDAO = BankCustomerDesk.customerDesk();
    	Scanner sc = new Scanner(System.in);
		System.out.println("Enter \n 1.For registration \n 2.To credit(Deposite) \n 3.To debit(Withdraw) \n 4.To change the password \n 5.For mobile transaction \n 6.To check balance");
		int response = sc.nextInt();
		switch (response) 
		{
		case 1:
			System.out.println("Enter first name: ");
			String fname = sc.next();
			System.out.println("Enter last name: ");
			String lname = sc.next();
			System.out.println("Enter mobile number: ");
			String mobile = sc.next();
			while(mobile.length()<10 || mobile.length()>10) {
				System.out.println("Enter correct mobile number: ");
				mobile = sc.next();
			}
			System.out.println("Enter email id: ");
			String email = sc.next();
			System.out.println("Enter password: ");
			String pass = sc.next();
			while(pass.length()<4 || pass.length()>4) {
				System.out.println("Enter 4 digit password: ");
				pass=sc.next();
			}
			System.out.println("Enter address: ");
			String add = sc.next();
			System.out.println("Enter amount:");
			double amo = sc.nextDouble();
			String accnum = "";
			for (int i = 0; i <=10; i++) {
				String str=Integer.toString(rand.nextInt(10));	
				accnum = accnum.concat(str);
			}
			Bank b = new Bank(fname,lname,mobile,email,pass,add,amo,accnum);
			bankDAO.userRegistration(b);
			break;
			
		case 2:
			System.out.println("Enter account number:");
			boolean accStatus_c=true;
			while(accStatus_c) {
				String c_acc_num = sc.next();
				if(c_acc_num.length()==11) {
					accStatus_c=false;
					
					System.out.println("Enter password: ");
					boolean passStatus_c=true;
					while(passStatus_c) {
						String c_pass = sc.next();
						if(c_pass.length()>=4) {
							passStatus_c=false;
							bankDAO.credit(c_acc_num, c_pass);
						}
						else {
							System.out.println("Enter min 4 digit password: ");
						}
					}
				}
				else {
					System.out.println("Enter 11 digit account number: ");
				}
			}
			break;
			
		case 3: 
			System.out.println("Enter account number:");
			boolean accStatus_d=true;
			while(accStatus_d) {
				String d_acc_num = sc.next();
				if(d_acc_num.length()==11) {
					accStatus_d=false;
					
					System.out.println("Enter password: ");
					boolean passStatus_d=true;
					while(passStatus_d) {
						String d_pass = sc.next();
						if(d_pass.length()>=4) {
							passStatus_d=false;
							bankDAO.debit(d_acc_num, d_pass);
						}
						else {
							System.out.println("Enter min 4 digit password: ");
						}
					}
				}
				else {
					System.out.println("Enter 11 digit account number: ");
				}
			}
			break;
			
		case 4:
			System.out.println("Enter account number:");
			boolean accStatus_CP=true;
			while(accStatus_CP) {
				String acc_num_CP = sc.next();
				if(acc_num_CP.length()==11) {
					accStatus_CP=false;
					
					System.out.println("Enter password: ");
					boolean passStatus_CP=true;
					while(passStatus_CP) {
						String passCP = sc.next();
						if(passCP.length()>=4) {
							passStatus_CP=false;
							bankDAO.changingThePassword(acc_num_CP, passCP);
						}
						else {
							System.out.println("Enter min 4 digit password: ");
						}
					}
				}
				else {
					System.out.println("Enter 11 digit account number: ");
				}
			}
			break;
			
		case 5:
			System.out.println("Enter the Ur mobile num:");
			sc.nextLine();
			String m1=sc.nextLine();
			System.out.println("Enter friends mob num to transfer:");
			String m2=sc.nextLine();
			while(m1.length()!=10) {
				System.out.println("Enter 1st Mob_num exactly 10 numbers ");
				m1=sc.nextLine();
			}
			while(m2.length()!=10) {
				System.out.println("Enter 2nd Mob_num exactly 10 numbers ");
				m2=sc.nextLine();
			}
			bankDAO.mobileToMobileTranscation(m1, m2);
			break;
		
		case 6:
			System.out.println("Enter account number:");
			boolean accStatus=true;
			while(accStatus) {
				String acc_num = sc.next();
				if(acc_num.length()==11) {
					accStatus=false;
					
					System.out.println("Enter password: ");
					boolean passStatus=true;
					while(passStatus) {
						String passCB = sc.next();
						if(passCB.length()>=4) {
							passStatus=false;
							bankDAO.checkBalance(acc_num, passCB);
						}
						else {
							System.out.println("Enter min 4 digit password: ");
						}
					}
				}
				else {
					System.out.println("Enter 11 digit account number: ");
				}
			}
			break;
			
		default:
			break;
		}
	}
}
