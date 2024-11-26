package com.voterId;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Client {
	public static void main(String[] args) {
		Configuration cif = new Configuration();
		cif.configure();
		cif.addAnnotatedClass(Person.class);
		cif.addAnnotatedClass(VotingCard.class);
		cif.addAnnotatedClass(Address.class);

		SessionFactory factory = cif.buildSessionFactory();
		Session session = factory.openSession();

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("Welcome to Voter_id Management Portal");
			System.out.println();

			System.out.println("1. Insert/Save a Person into Database");
			System.out.println("2. Fetch Person using Aadhar_ID");
			System.out.println("3. Delete Person using Aadhar_ID");
			System.out.println("4. Get all Person Details");
			System.err.println("5. Exits");

			int key = sc.nextInt();
			switch (key) {
			case 1:
				Person p1 = new Person();
				System.out.println("Enter Aadhar Number");
				p1.setAadhar_id(sc.nextInt());
				System.out.println("Enter name of Person");
				p1.setName(sc.next());

				VotingCard v1 = new VotingCard();
				System.out.println("Enter Voting ID");
				v1.setVoter_id(sc.next());
				System.out.println("Enter Constituency");
				v1.setConstituency(sc.next());

				Address a1 = new Address();
				System.out.println("Enter Pincode");
				a1.setPin_code(sc.nextInt());
				System.out.println("Enter City");
				a1.setCity(sc.next());

				// Create a List of Address
				List<Address> addressList = new ArrayList<>();
				addressList.add(a1);
				Transaction tx = session.beginTransaction();

				p1.setAddress(addressList);
				p1.setVoter_id(v1);
				session.save(p1);
				tx.commit();
			
				break;

			case 2:
				System.out.println("Enter Aadhar ID to fetch Details");
				Person person = session.get(Person.class, sc.nextInt());
				System.out.println(person);
				
				break;

			case 3:
				Transaction tx1 = session.beginTransaction();
				System.out.println("Enter Aadhar ID to Delete Person");
				Person p2 = new Person();
				p2.setAadhar_id(sc.nextInt());
				session.delete(p2);
				tx1.commit();
				break;

			case 4:
				System.out.println("Get All Person Details");
				// Fetch all Person records using JPQL
				List<Person> persons = session.createQuery("FROM Person", Person.class).getResultList();
				for (Person p : persons) {
					System.out.println(p);
				}
				session.close();
				factory.close();
				break;

			case 5:
				System.out.println("Exits");
			session.close();
			factory.close();
			System.exit(0);
				break;
			}
		}
	}
}
