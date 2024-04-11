package com.serviceimpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.config.HibernateUtil;
import com.model.Account;
import com.servicei.RBI;

public class SBI implements RBI {

	Scanner sc=new Scanner(System.in);
	
	public void createAccount(){
		Session session=HibernateUtil.getSessionFactory().openSession();
		Account a=new Account();
			while(true)
			{	
				try
				{
					System.out.println("Enter account number : ");
					long x=sc.nextLong();
					int c=0;
					for(long i=x;i>0;i=i/10)
						c++;
					if(c==10)
					{
						a.setAccno(x);
						break;
					}
					else
					{
					System.out.println("Please Enter 10 Digit Account Number");
					}
				}
				catch(InputMismatchException e)
					{
						e.getStackTrace();
						System.out.println("Account number must include only numbers");
						sc.next();
					}
			}
		
		while(true)
		{	
			System.out.println("Enter your name");
			String s1=sc.next()+sc.nextLine();
			String s2="`!@#$%^&*()_+=-|}]{[;:',.<>/?";
			String str;
			boolean b1=false;
			for(int i=0;i<s1.length();i++)
			{
				str=Character.toString(s1.charAt(i));
				if(s2.contains(str))
				{
					b1=true;
					break;
				}
			}
			if(b1)
			{
				System.out.println("Name should not contain special characters");
			}
			else
			{
				a.setName(s1);
				break;
			}
		}
		
		while(true)
		{
				try
				{
					System.out.println("Enter your mobile number : ");
						long y=sc.nextLong();
						int c1=0;
						for(long i=y;i>0;i=i/10)
							c1++;
						if(c1==10)
						{
							a.setMbno(y);
							break;
						}
						else
						{
						System.out.println("Enter Your Valid Mobile number");
						}
				}	
				catch(InputMismatchException e)
				{
					e.getStackTrace();
					System.out.println("Mobile number should not contain Characters");
					sc.next();
				}
		}
		
		while(true)
		{
				try
				{
				System.out.println("Enter your Aadhaar number of 12 digits : ");
						long q=sc.nextLong();
						int c2=0;
						for(long i=q;i>0;i=i/10)
							c2++;
						if(c2==12)
						{
							a.setAdharno(q);
							break;
						}
						else
						{
						System.out.println("Aadhar number must be of 12 digit");
						}
				}
				catch(InputMismatchException e)
				{
					e.getStackTrace();
					System.out.println("Aadhaar number should not contain Characters");
					sc.next();
				}		
		}
		
		boolean b=true;
		while(b)
		{
			System.out.println("Enter your gender\n"+"1. Male\n"+"2. Female\n");
			int t=sc.nextInt();
			switch(t)
			{
			case 1: a.setGender("Male");
			b=false;
			break;
			case 2: a.setGender("Female");
			b=false;
			break;
			default : System.out.println("Invalid choice for Gender");
			break;
			}
		}
		
		while(true)
		{
			System.out.println("Enter your age");
			int z=sc.nextInt();
			if(z>=18)
			{
				a.setAge(z);
				break;
			}
			else
			{
				System.out.println("Your age is Less than required age");
			}
		}
		
		while(true)
		{
			System.out.println("Enter account balance greater than 1000");
			double p=sc.nextDouble();
			if(p>=1000)
			{	
				a.setBalance(p);
				break;
			}
			else
			{
				System.out.println("Minimum Balance must be greater than 1000");
			}
		}
		
		Transaction tx=session.beginTransaction();
		session.save(a);
		tx.commit();
		session.close();
	}

	
	public void displayAllDetails()
	{	
		Session session=HibernateUtil.getSessionFactory().openSession();
		int count=0;
		boolean brek = true;
		while(true)
		{
			if(count>0)
			{
				System.out.println("Account No. Doesnt Exist");
			
			}
		System.out.println("Enter YOUR ACCOUNT number");
		long acc=sc.nextLong();
		List<Account> alist=session.createQuery("from Account").getResultList();
		for(Account a : alist)
		{
			if(acc==a.getAccno())
			{
				System.out.println("Account No. : "+a.getAccno());
				System.out.println("Acct Holder Name : "+a.getName());
				System.out.println("Mobile No. : "+a.getMbno());
				System.out.println("Aadhaar No. : "+a.getAdharno());
				System.out.println("Gender : "+a.getGender());
				System.out.println("Age : "+a.getAge());
				System.out.println("**BALANCE** : "+a.getBalance());
				session.close();
				brek = true;
				break;
			}
			else {
				count++;
				brek = false;
			}
		}
			if(brek)
			{
				break;
			}

		}
	}

	public void depositMoney()
	{
		Session session=HibernateUtil.getSessionFactory().openSession();
		System.out.println("Enter YOUR ACCOUNT number");
		long acc=sc.nextLong();
		List<Account> alist=session.createQuery("from Account").getResultList();
		alist.forEach(a->{
			if(acc==a.getAccno())
			{
				System.out.println("Enter amount of money deposited (greater than 500) ");
				double dp=sc.nextDouble();
				if(dp>=500 && dp<=100000) 
				{
					double dm=a.getBalance()+dp;
					a.setBalance(dm);
					Transaction tx=session.beginTransaction();
					session.update(a);
					tx.commit();
				}
				else 
				{
					System.out.println("Deposited amount must be greater than 500 or less than 1 lakh");
				}
			}
			else {
				System.out.println("Account Number Does Not Exist");
			}
		});
		
			session.close();
	}
		

	public void withdrawal()
	{
		Session session=HibernateUtil.getSessionFactory().openSession();
		System.out.println("Enter YOUR ACCOUNT number");
		long acc=sc.nextLong();
		List<Account> alist=session.createQuery("from Account").getResultList();
		alist.forEach(a->{
			if(acc==a.getAccno())
			{
				System.out.println("Enter amount of money withdrwan (less than 10000) ");
				double wb=sc.nextDouble();
				if(wb<=10000 && wb<a.getBalance()) 
				{
					double wm=a.getBalance()-wb;
					a.setBalance(wm);
					Transaction tx=session.beginTransaction();
					session.update(a);
					tx.commit();
				}
				else 
				{
					System.out.println("Withdrawing money should be less than 10k OR Insufficient Balance");
				}
			}
			else {
				System.out.println("Account Number Does Not Exist");
			}
		});
			session.close();
	}

	public void balanceCheck()
	{
		Session session=HibernateUtil.getSessionFactory().openSession();
		boolean b=true;
		while(b==true)
		{
			try
			{
				System.out.println("Enter YOUR ACCOUNT number");
				long acc=sc.nextLong();
				List<Account> alist=session.createQuery("from Account").getResultList();
				
				  for(Account a : alist) {
					 if(acc==a.getAccno())
					 {
						 System.out.println("BALANCE : "+a.getBalance()); 
						 b=false; 
						 break;
					 } 
					 if(b==false)
					 {
						 System.out.println("Account Number Does Not Exist"); 
					 } 
				}
				 
			}
			catch(InputMismatchException e)
			{
				e.getStackTrace();
				System.out.println("Account number should not contain Characters");
				sc.next();
			}
		session.close();
		}
	}
}