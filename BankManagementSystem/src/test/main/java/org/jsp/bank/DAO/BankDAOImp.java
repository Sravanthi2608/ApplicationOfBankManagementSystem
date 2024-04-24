package org.jsp.bank.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.jsp.bank.Bank;

public class BankDAOImp implements BankDAO {
	Scanner sc=new Scanner(System.in);
	 DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy(HH:mm:ss)");  
	 LocalDateTime now = LocalDateTime.now();  
	Connection con;
	@Override
	public void userRegistration(Bank b) {
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/teca52?user=root&password=12345");
			PreparedStatement ps = con.prepareStatement("insert into teca52.bank(first_name,last_name,mob_num,email,pass,address,amount,acc_num) values(?,?,?,?,?,?,?,?)");
			ps.setString(1, b.getUserFirstName());
			ps.setString(2, b.getUserLastName());
			ps.setString(3, b.getMobileNumber());
			ps.setString(4, b.getEmailId());
			ps.setString(5, b.getPassword());
			ps.setString(6, b.getAddress());
			ps.setDouble(7, b.getAmount());
			ps.setString(8,b.getAccountNum());
			int result=ps.executeUpdate();
			if(result!=0) {
				System.out.println("Registration Successful");
			}
			else {
				System.out.println("Registration Unsuccessful");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void credit(String acc_num,String pass) {
		Scanner sc = new Scanner(System.in);
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/teca52?user=root&password=12345");
			PreparedStatement ps = con.prepareStatement("select * from teca52.bank where acc_num=? and pass=?");
			ps.setString(1,acc_num);
			ps.setString(2,pass);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				System.out.println("Enter the amount you want to deposite:");
				double amo=sc.nextDouble();
				while(amo<0) {
					System.out.println("enter amount>0");
					amo=sc.nextDouble();
				}
				if(rs.getDouble("amount")>=0||rs.getDouble("amount")<0) {
				double c_amo = rs.getDouble("amount")+amo;
				PreparedStatement ps1 = con.prepareStatement("update teca52.bank set amount=? where acc_num=? and pass=?");
				ps1.setDouble(1, c_amo);
				ps1.setString(2, acc_num);
				ps1.setString(3, pass);
				int result=ps1.executeUpdate();
					if(result==1) {
						System.out.println("Rs "+amo+" Credited to your A/c on "+df.format(now)+" (UPI Ref No "+acc_num+")-Unknown Bank(:D)" );
					}
					else {
						System.out.println("Technical Issue");
					}
				}
			}
			else {
				System.out.println("Invalid account");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Override
	public void debit(String acc_num,String pass) {
		Scanner sc = new Scanner(System.in);
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/teca52?user=root&password=12345");
			PreparedStatement ps = con.prepareStatement("select * from teca52.bank where acc_num=? and pass=?");
			ps.setString(1,acc_num);
			ps.setString(2,pass);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				System.out.println("Current amount"+rs.getDouble("amount"));
				System.out.println("Enter the amount:");
				double amo=sc.nextDouble();
				while(amo<0) {
					System.out.println("enter amount>0");
					amo=sc.nextDouble();
				}
				if(rs.getDouble("amount")>=amo) {
				double d_amo = rs.getDouble("amount")-amo;
				PreparedStatement ps1 = con.prepareStatement("update teca52.bank set amount=? where acc_num=? and pass=?");
				ps1.setDouble(1, d_amo);
				ps1.setString(2, acc_num);
				ps1.setString(3, pass);
				int result=ps1.executeUpdate();
				if(result==1) {
					System.out.println("Rs "+amo+" debited from your A/c using UPI on "+df.format(now)+" (UPI Ref No "+acc_num+")-Unknown Bank(:D)" );
				}
				else {
					System.out.println("Technical Issue");
				}
				}
				else {
					System.out.println("Insufficient amount");
				}
			}
			else {
				System.out.println("Invalid account");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void changingThePassword(String acc_num,String pass) {
		Scanner sc = new Scanner(System.in);
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/teca52?user=root&password=12345");
			PreparedStatement ps = con.prepareStatement("select * from teca52.bank where acc_num=? and pass=?");
			ps.setString(1,acc_num);
			ps.setString(2,pass);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				System.out.print("Enter your new password:");
				String newPass=sc.nextLine();
				while(newPass.length()<4) {
					System.out.println("Enter min 4 digit password: ");
					newPass=sc.nextLine();
				}
				PreparedStatement ps1 = con.prepareStatement("update teca52.bank set pass=? where acc_num=? and pass=?");
				ps1.setString(1, newPass);
				ps1.setString(2, acc_num);
				ps1.setString(3, pass);
				int result=ps1.executeUpdate();
				if(result==1) {
					System.out.println("Password Updation is Successful");
				}
				else {
					System.out.println("Technical Issue");
				}
			}
			else {
				System.out.println("Invalid account");
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void mobileToMobileTranscation(String m1,String m2) {
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/teca52?user=root&password=12345");
			PreparedStatement ps = con.prepareStatement("select * from teca52.bank where mob_num=?");
			PreparedStatement ps1 = con.prepareStatement("select * from teca52.bank where mob_num=?");
			ps.setString(1,m1);
			ps1.setString(1,m2);
			ResultSet rs =ps.executeQuery();
			ResultSet rs1=ps1.executeQuery();
			boolean mob1=false;
			boolean mob2=false;
			if(rs.next()) {
				mob1=true;
				if(rs1.next()) {
					mob2=true;
				System.out.println("My Current amount "+rs.getDouble("amount"));
				System.out.println("Enter the amount:");
				double amo=sc.nextDouble();
				while(amo<0) {
					System.out.println("enter amount>0");
					amo=sc.nextDouble();
				}
				while(amo>rs.getDouble("amount")) {
					System.out.println("Entering amount must be less than your current amount");
					amo=sc.nextDouble();
				}
				System.out.println("Enter ur password:");
				sc.nextLine();
				String pass1=sc.nextLine();
				while(pass1.equals(rs.getString("pass"))==false) {
					System.out.println("Incorrect Password try again");
					pass1=sc.nextLine();
				}
					if(amo<=rs.getDouble("amount")) {
						double m1_amo = rs.getDouble("amount")-amo;
						PreparedStatement ps2 = con.prepareStatement("update teca52.bank set amount=? where mob_num=?");
						ps2.setDouble(1, m1_amo);
						ps2.setString(2, m1);
						double m2_amo=rs1.getDouble("amount")+amo;
						PreparedStatement ps3 = con.prepareStatement("update teca52.bank set amount=? where mob_num=?");
						ps3.setDouble(1, m2_amo);
						ps3.setString(2, m2);
						int result=ps2.executeUpdate();
						ps3.executeUpdate();
						if(result==1) {
							System.out.println("Rs "+amo+" debited from your A/c using UPI on "+df.format(now)+" (UPI Ref No "+rs.getString("acc_num")+")-Unknown Bank(:D)" );
						}
						else {
							System.out.println("Technical Issue");
						}
						
					}
					else {
						System.out.println("Insufficient Balance");
					}
				}
				else {
					System.out.println("Mobile num not found!!!");
				}
			}
			else {
				System.out.println("You should know your Mob Num!!!");
			}
			if(mob1==false&&mob2==false) {
				System.out.println("Both the Numbers were wrong plz check again!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void checkBalance(String acc_num,String pass) {
		Scanner sc = new Scanner(System.in);
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/teca52?user=root&password=12345");
			PreparedStatement ps = con.prepareStatement("select * from teca52.bank where acc_num=? and pass=?");
			ps.setString(1,acc_num);
			ps.setString(2,pass);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				double balance=rs.getDouble("amount");
				System.out.println("Balance:"+balance);
			}	
			else {
				System.out.println("Invalid account");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
