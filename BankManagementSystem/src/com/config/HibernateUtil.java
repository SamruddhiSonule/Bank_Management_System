package com.config;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.model.Account;


public class HibernateUtil {
	private static SessionFactory sf=null;
	private static StandardServiceRegistry registry;
	
	public static SessionFactory getSessionFactory()
	{
	
	      try {
			if(sf==null) {
				Map<String,String> m=new HashMap<>();
			    // connection mapping commands
		m.put(Environment.DRIVER,"com.mysql.jdbc.Driver");	
		m.put(Environment.URL,"jdbc:mysql://localhost:3306/bmsapp");	
		m.put(Environment.USER,"root");	
		m.put(Environment.PASS,"root");	
		
		  // hibernate mapping commads
		m.put(Environment.DIALECT,"org.hibernate.dialect.MySQLDialect");
		m.put(Environment.HBM2DDL_AUTO,"update");
		m.put(Environment.SHOW_SQL,"true");
		// create Object of StandardServiceRegistry
		registry=new StandardServiceRegistryBuilder().applySettings(m).build();
		// create Object of metadatasources
		MetadataSources mds=new MetadataSources(registry);
		// entity class mapping
		     mds.addAnnotatedClass(Account.class);
		   // create Object of MetaData
		      Metadata md =mds.getMetadataBuilder().build();
	        // create Object of SessionFactorty
		     sf=md.getSessionFactoryBuilder().build();
			}
		} 
	      catch (Exception e) {
			System.out.println("sessionfactry creation fails     "+e);
		}	
		
		return sf;
	}

}