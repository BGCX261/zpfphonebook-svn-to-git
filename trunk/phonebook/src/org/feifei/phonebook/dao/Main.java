package org.feifei.phonebook.dao;

import org.feifei.phonebook.model.Contactor;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ObjectContainer oc = Db4o.openFile("hello.db");
		Contactor c = new Contactor();
		c.setName("helelddddfdsafd");
		c.setAddress("dfafda");
		try {
			oc.store(c);
			ObjectSet os = oc.queryByExample(c);
			Contactor contactor = (Contactor) os.next();
			System.out.println(contactor.getName());
		} finally {
			oc.close();
		}

	}

}
