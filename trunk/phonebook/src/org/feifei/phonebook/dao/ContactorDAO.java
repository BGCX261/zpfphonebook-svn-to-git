package org.feifei.phonebook.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.feifei.phonebook.model.Contactor;
import org.feifei.phonebook.model.PhoneNum;
import org.feifei.phonebook.util.UUIDutil;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
/**
 * 
 * @author shichong
 *
 * @date 2009-7-9
 * @version $Id:ContactorDAO.java 2009-7-9 上午09:06:30  shichong
 */
public class ContactorDAO {
	/**
	 * 创建Contactor
	 * 
	 * @param contactor Contactor对象
	 * @return 新建对象的id
	 * @throws Exception failed
	 */
	public static Contactor createContactor(Contactor contactor) throws Exception {
		contactor.setId(UUIDutil.geneUUID().toString());
		ObjectContainer oc = Db4o.openFile(DaoJob.DBPATH);
		try {
			oc.store(contactor);
			ObjectSet os = oc.queryByExample(contactor);
			return (Contactor) os.next();
		} finally {
			oc.close();
		}
	}

	/**
	 * 根据id号获取Contactor对象
	 * 
	 * @param id Contactor对象的id
	 * @return Contactor对象
	 * @throws Exception failed
	 */
	public static Contactor getContactor(final String id) throws Exception {
		ObjectContainer oc = Db4o.openFile(DaoJob.DBPATH);
		try {
			ObjectSet os = oc.query(new Predicate<Contactor>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public boolean match(Contactor candidate) {
					return candidate.getId().equals(id);
				}
			});
			return (Contactor) os.next();
		} finally {
			oc.close();
		}
	}

	/**
	 * 获取Contactor对象的列表
	 * 
	 * @return List
	 * @throws Exception failed
	 */
	@SuppressWarnings("unchecked")
	public static List getAllContactor() throws Exception {
		ObjectContainer oc = Db4o.openFile(DaoJob.DBPATH);
		try {
			return new ArrayList(oc.queryByExample(Contactor.class));
		} finally {
			oc.close();
		}
	}

	/**
	 * 根据对象删除Contactor对象
	 * 
	 * @param customer Contactor对象
	 * @throws Exception failed
	 */
	@SuppressWarnings("serial")
	public static void deleteContactor(final Contactor contactor) throws Exception {

		ObjectContainer oc = Db4o.openFile(DaoJob.DBPATH);
		try {
			ObjectSet os = oc.query(new Predicate<Contactor>() {
				@Override
				public boolean match(Contactor candidate) {
					return candidate.getId().equals(contactor.getId());
				}

			});
			oc.delete(os.next());
		} finally {
			oc.close();
		}
	}

	/**
	 * 根据原有的Contactor对象更新
	 * 
	 * @param contactor 旧的Contactor对象
	 * @throws Exception failed
	 */
	@SuppressWarnings("serial")
	public static void updateContactor(final Contactor contactor) throws Exception {
		Db4o.newConfiguration().objectClass(Contactor.class).cascadeOnUpdate(true);
		ObjectContainer oc = Db4o.openFile(DaoJob.DBPATH);
		
		Contactor c=new Contactor();
		c.setId(contactor.getId());
		ObjectSet s = oc.queryByExample(c);
		Contactor con = (Contactor) s.next();
		con.setName(contactor.getName());
		con.setAge(contactor.getAge());
		con.setNation(contactor.getNation());
		//con.setEmails(contactor.getEmails());
		//con.setPhoneNums(contactor.getPhoneNums());
		con.setAddress(contactor.getAddress());
		con.setPostNum(contactor.getPostNum());
		con.setQqNum(contactor.getQqNum());
		oc.store(con.getPhoneNums().addAll(contactor.getPhoneNums()));
		oc.store(con);
		oc.close();
	}

	public static Contactor searchContactor(final String name, final String qqNum, final String emailAddress, final String phoneNum) {
		final Contactor c = new Contactor();
		c.setName(name);
		c.setQqNum(qqNum);

		ObjectContainer oc = Db4o.openFile(DaoJob.DBPATH);
		try {
			ObjectSet os = oc.query(new Predicate<Contactor>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public boolean match(Contactor candidate) {
					return candidate.getName().equals(c.getName()) && candidate.getQqNum().equals(c.getQqNum());

				}

			});
			if (os.hasNext()) {
				return (Contactor) os.next();
			} else {
				return null;
			}
		} finally {
			oc.close();
		}
	}
}
