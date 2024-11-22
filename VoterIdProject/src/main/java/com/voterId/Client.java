package com.voterId;

import java.util.ArrayList;
import java.util.List;

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
		Transaction tx = session.beginTransaction();

		Person p1 = new Person(3102, "Abhishek");
		VotingCard v1 = new VotingCard("Ab123", "Buldhana");
		Address a1 = new Address(443301, "Mehkar");

		Person p2 = new Person(1234, "Vipul");
		VotingCard v2 = new VotingCard("VP134", "Buldhana");
		Address a2 = new Address(58684, "Mehkar");

		List<Address> list = new ArrayList<Address>();
		list.add(a1);
		list.add(a2);

		p2.setAddress(a2);
		p2.setVoter_id(v2);
		p1.setAddress(a1);
		p1.setVoter_id(v1);

		// Insert Data
		session.save(p1);
		session.save(p2);
		

		// Fetch Data
		//Person person = session.load(Person.class, 3102);
	//	System.out.println(person);
		tx.commit();
		session.close();
		factory.close();

	}
}
