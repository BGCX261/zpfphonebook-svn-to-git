package org.feifei.phonebook.dao;

import java.util.List;

import org.feifei.phonebook.model.PhoneNum;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class PhoneNumDAO {
	/**
	 * ����PhoneNum
	 * 
	 * @param phoneNum
	 *            PhoneNum����
	 * @return �½������id
	 * @throws Exception
	 *             failed
	 */
	public static PhoneNum createPhoneNum(PhoneNum phoneNum) throws Exception {
		ObjectContainer oc = Db4o.openFile(DaoJob.DBPATH);
		try {
			oc.store(phoneNum);
			ObjectSet os = oc.queryByExample(phoneNum);
			return (PhoneNum) os.next();
		} finally {
			oc.close();
		}
	}

	/**
	 * ����id�Ż�ȡPhoneNum����
	 * 
	 * @param id
	 *            PhoneNum�����id
	 * @return PhoneNum����
	 */
	public static PhoneNum getPhoneNum(final String id) {
		ObjectContainer oc = Db4o.openFile(DaoJob.DBPATH);
		try {
			ObjectSet os = oc.query(new Predicate<PhoneNum>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public boolean match(PhoneNum candidate) {
					return candidate.getId().equals(id);
				}
			});
			return (PhoneNum) os.next();
		} finally {
			oc.close();
		}
	}

	/**
	 * ��ȡPhoneNum������б�
	 * 
	 * @return List
	 */
	public static List getAllPhoneNum() {
		ObjectContainer oc = Db4o.openFile(DaoJob.DBPATH);
		try {
			return oc.query(PhoneNum.class);
		} finally {
			oc.close();
		}
	}

	/**
	 * ���ݶ���ɾ��PhoneNum����
	 * 
	 * @param phoneNum
	 *            PhoneNum����
	 */
	@SuppressWarnings("serial")
	public static void deletePhoneNum(final PhoneNum phoneNum) {
		ObjectContainer oc = Db4o.openFile(DaoJob.DBPATH);
		try {
			ObjectSet os = oc.query(new Predicate<PhoneNum>() {
				@Override
				public boolean match(PhoneNum candidate) {
					return candidate.getId().equals(phoneNum.getId());
				}

			});
			oc.delete(os.next());
		} finally {
			oc.close();
		}
	}

	/**
	 * ����ԭ�е�PhoneNum�������
	 * 
	 * @param phoneNum
	 *            �ɵ�PhoneNum����
	 */
	public static void updatePhoneNum(final PhoneNum phoneNum) {
		ObjectContainer oc = Db4o.openFile(DaoJob.DBPATH);
		try {
			oc.store(phoneNum);
		} finally {
			oc.close();
		}
	}
}
