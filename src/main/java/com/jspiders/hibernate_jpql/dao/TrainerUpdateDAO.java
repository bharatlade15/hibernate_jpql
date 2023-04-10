package com.jspiders.hibernate_jpql.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jspiders.hibernate_jpql.dto.TrainerDTO;

public class TrainerUpdateDAO {

	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	private static Query query;
	private static String jpqlQuery;
	private static int result;
	
	private static void openConnection() {
		factory=Persistence.createEntityManagerFactory("jpql");
		manager=factory.createEntityManager();
		transaction=manager.getTransaction();
		
		
	}
	private static void closeConnection() {
		if (factory !=null) {
			factory.close();
		}
		if (manager !=null) {
			manager.close();
		}
		if (transaction.isActive()) {
			transaction.rollback();
		}
	}
	
	public static void main(String[] args) {
		try {
			
			openConnection();
			
			transaction.begin();
			
			jpqlQuery="update TrainerDTO set subject='Selenium' where id=1";
			query=manager.createQuery(jpqlQuery);
			result=query.executeUpdate();
			System.out.println("Query OK, "+result+" row(s) selected.");
			transaction.commit();
			
		} finally {
			closeConnection();
		}
	}
}
