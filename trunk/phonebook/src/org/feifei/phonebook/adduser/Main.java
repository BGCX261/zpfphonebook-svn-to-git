package org.feifei.phonebook.adduser;

import org.feifei.phonebook.dao.DaoJob;
import org.feifei.phonebook.model.PhoneNum;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Main {
	public static void main(String[] args) {
		ObjectContainer oc = Db4o.openFile(DaoJob.DBPATH);
		try {
			ObjectSet os = oc.queryByExample(PhoneNum.class);
			while (os.hasNext()) {
				PhoneNum t = (PhoneNum) os.next();
				System.out.println(t.getPhoneNum());
			}
		} finally {
			oc.close();
		}
	}

}
