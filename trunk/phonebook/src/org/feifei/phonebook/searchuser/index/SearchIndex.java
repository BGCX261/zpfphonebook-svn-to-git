package org.feifei.phonebook.searchuser.index;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.feifei.phonebook.dao.DaoJob;
import org.feifei.phonebook.dao.SiteDAO;
import org.feifei.phonebook.model.Contactor;
import org.feifei.phonebook.model.PhoneNum;
import org.feifei.phonebook.model.Site;
import org.feifei.phonebook.util.Co;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;

public class SearchIndex {
	public static String CONTACTORPATH="d:\\test123";
	public static String SITEPATH="d:\\test1234";
	
	public static void main(String[] args) throws Exception {
		System.out.println("-------------start");
		Hits s=searchSiteByKeyword("网易");
		
		for(int i=0;i<s.length();i++){
			Document d=s.doc(i);
			System.out.println(d.get("name"));
		}
		System.out.println("----------------end");
	}
	
	@SuppressWarnings("unchecked")
	public static void indexDoc() throws Exception{
	    List l=getAllContactor();
	    Iterator it=l.iterator();
		IndexWriter writer=new IndexWriter(CONTACTORPATH,new StandardAnalyzer(),true);
		while(it.hasNext()){
			Contactor c=(Contactor)it.next() ;
			Document doc=new Document();
			doc.add(Field.Text("name", Co.getStr(c.getName())));
			doc.add(Field.Text("address", Co.getStr(c.getAddress())));
			doc.add(Field.Text("postNum", Co.getStr(c.getPostNum())));
			doc.add(Field.Text("id", c.getId()));
			doc.add(Field.Text("nation", Co.getStr(c.getNation())));
			doc.add(Field.Text("qq", Co.getStr(c.getQqNum())));
			for(PhoneNum p:c.getPhoneNums()){
				doc.add(Field.Text("phone", Co.getStr(p.getPhoneNum())));
			}
			writer.addDocument(doc);
		}
		writer.close();
		writer=new IndexWriter(SITEPATH,new StandardAnalyzer(),true);
		List<Site> sites=SiteDAO.getAllSite();
		for(Site s:sites){
			Document doc=new Document();
			doc.add(Field.Text("name", s.getName()));
			doc.add(Field.Text("tag", s.getTag()));
			doc.add(Field.Text("url", s.getUrl()));
			writer.addDocument(doc);
		}
		writer.close();
	}
	
	@SuppressWarnings("unchecked")
	public static List getAllContactor(){
		//在单个程序里面启动的时候，路径和rcp的路径不一致
		ObjectContainer oc = Db4o.openFile(DaoJob.DBPATH);
		try {
			return new ArrayList(oc.queryByExample(Contactor.class));
		} finally {
			oc.close();
		}
	}
	
	public static Hits searchUsrByName(String name){
		try {
			IndexSearcher search=new IndexSearcher(CONTACTORPATH);
			Query query1=QueryParser.parse(name,"name", new StandardAnalyzer());
			Query query2=QueryParser.parse(name,"address", new StandardAnalyzer());
			BooleanQuery query=new BooleanQuery();
			query.add(query1,false,false);
			query.add(query2,false,false);
			return search.search(query);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	public static Hits searchSiteByKeyword(String keyword){
		try {
			IndexSearcher search=new IndexSearcher(SITEPATH);
			Query query1 = QueryParser.parse(keyword,"name",new StandardAnalyzer());
			Query query2 = QueryParser.parse(keyword,"tag",new StandardAnalyzer());
			BooleanQuery query=new BooleanQuery();
			query.add(query1,false,false);
			query.add(query2,false,false);

			return search.search(query);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
