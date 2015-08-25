package org.feifei.phonebook.dao;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.feifei.phonebook.model.Contactor;
import org.feifei.phonebook.searchuser.index.SearchIndex;

import com.db4o.Db4o;

public class DaoJob extends Job {
	public static final String DBPATH = "DBFILE";

	public DaoJob() {
		super("连接数据库");
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			Db4o.newConfiguration().objectClass(Contactor.class)
					.cascadeOnDelete(true);
			System.out.println("数据库初始化完毕");
			SearchIndex.indexDoc();
			System.out.println("索引完毕");
			System.out.println("系统开始启动");
			return Status.OK_STATUS;
		} catch (Exception e) {
			e.printStackTrace();
			return Status.CANCEL_STATUS;
		}
	}

}
