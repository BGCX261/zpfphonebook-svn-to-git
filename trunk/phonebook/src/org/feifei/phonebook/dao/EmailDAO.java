package org.feifei.phonebook.dao;

import java.util.List;

import org.feifei.phonebook.model.Email;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class EmailDAO {
	/**
	 * ����Email
	 * 
	 * @param email
	 *            Email����
	 * @return �½������id
	 * @throws Exception
	 *             failed
	 */
	public static Long createEmail(Email email) throws Exception {
		return 1L;
	}

	/**
	 * ����id�Ż�ȡEmail����
	 * 
	 * @param id
	 *            Email�����id
	 * @return Email����
	 */
	public static Email getEmail(final String id) {
		ObjectContainer oc = Db4o.openFile(DaoJob.DBPATH);
		try {
			ObjectSet os = oc.query(new Predicate<Email>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public boolean match(Email candidate) {
					return candidate.getId().equals(id);
				}
			});
			return (Email) os.next();
		} finally {
			oc.close();
		}
	}

	/**
	 * ��ȡEmail������б�
	 * 
	 * @return List
	 */
	public static List getAllEmail() {
		ObjectContainer oc = Db4o.openFile(DaoJob.DBPATH);
		try {
			return oc.queryByExample(Email.class);
		} finally {
			oc.close();
		}
	}

	/**
	 * ���ݶ���ɾ��Email����
	 * 
	 * @param email
	 *            Email����
	 */
	public static void deleteEmail(final Email email) {
		ObjectContainer oc = Db4o.openFile(DaoJob.DBPATH);
		try {
			ObjectSet os = oc.query(new Predicate<Email>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public boolean match(Email candidate) {
					return candidate.getId().equals(email.getId());
				}

			});
			oc.delete(os.next());
		} finally {
			oc.close();
		}
	}

	/**
	 * ����ԭ�е�Email�������
	 * 
	 * @param email
	 *            �ɵ�Email����
	 */
	public static void updateEmail(final Email email) {
		ObjectContainer oc = Db4o.openFile(DaoJob.DBPATH);
		try {
			oc.store(email);
		} finally {
			oc.close();
		}
	}
}
