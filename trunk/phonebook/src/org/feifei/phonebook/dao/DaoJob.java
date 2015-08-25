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
		super("�������ݿ�");
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			Db4o.newConfiguration().objectClass(Contactor.class)
					.cascadeOnDelete(true);
			System.out.println("���ݿ��ʼ�����");
			SearchIndex.indexDoc();
			System.out.println("�������");
			System.out.println("ϵͳ��ʼ����");
			return Status.OK_STATUS;
		} catch (Exception e) {
			e.printStackTrace();
			return Status.CANCEL_STATUS;
		}
	}

}
