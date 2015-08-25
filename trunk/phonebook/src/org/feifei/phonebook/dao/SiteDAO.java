package org.feifei.phonebook.dao;

import java.util.ArrayList;
import java.util.List;

import org.feifei.phonebook.model.Site;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;

/**
 * վ������ݲ�
 * @author shichong
 *
 * @date 2009-7-9
 * @version $Id:SiteDAO.java 2009-7-9 ����05:13:18  shichong
 */
public class SiteDAO {
	/**
	 * ��ȡContactor������б�
	 * 
	 * @return List
	 * @throws Exception failed
	 */
	@SuppressWarnings("unchecked")
	public static List getAllSite() throws Exception {
		ObjectContainer oc = Db4o.openFile(DaoJob.DBPATH);
		try {
			return new ArrayList(oc.queryByExample(Site.class));
		} finally {
			oc.close();
		}
	}
	
	public static void create(Site site)throws Exception{
		ObjectContainer oc = Db4o.openFile(DaoJob.DBPATH);
		try{
			oc.store(site);
		}finally{
			oc.close();
		}
	}

}
