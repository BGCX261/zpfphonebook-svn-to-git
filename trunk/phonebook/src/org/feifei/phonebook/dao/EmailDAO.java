package org.feifei.phonebook.dao;

import java.util.List;

import org.feifei.phonebook.model.Email;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class EmailDAO {
	/**
	 * 创建Email
	 * 
	 * @param email
	 *            Email对象
	 * @return 新建对象的id
	 * @throws Exception
	 *             failed
	 */
	public static Long createEmail(Email email) throws Exception {
		return 1L;
	}

	/**
	 * 根据id号获取Email对象
	 * 
	 * @param id
	 *            Email对象的id
	 * @return Email对象
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
	 * 获取Email对象的列表
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
	 * 根据对象删除Email对象
	 * 
	 * @param email
	 *            Email对象
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
	 * 根据原有的Email对象更新
	 * 
	 * @param email
	 *            旧的Email对象
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
