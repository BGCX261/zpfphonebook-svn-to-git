package org.feifei.phonebook.dao;

import java.util.List;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class Test {

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Db4o.newConfiguration().objectClass(Friend.class).cascadeOnUpdate(true);
		ObjectContainer oc = Db4o.openFile("D:\\hello.db");
		ObjectSet s=oc.query(new Predicate<Friend>(){

			@Override
			public boolean match(Friend candidate) {
				
				return candidate.getName().equals("≈Û”—1");
			}
			
		});
		Friend  friend=(Friend) s.next();
		friend.getMyCar().add(new Car("zhongguo"));
		oc.store(friend.getMyCar());
		oc.close();
		listResult();
	}

	private static void listResult() {
		ObjectContainer oc = Db4o.openFile("D:\\hello.db");
		ObjectSet s=oc.queryByExample(Friend.class);
		while(s.hasNext()){
			Friend f=(Friend) s.next();
			System.out.println(f.getName());
		}
		System.out.println("________________________________");
		ObjectSet ct=oc.queryByExample(Car.class);
		while(ct.hasNext()){
			Car cm=(Car) ct.next();
			System.out.println(cm.getName());
		}
		oc.close();
	}
	
	

}

class Friend {
	String name;

	List<Car> myCar;

	public List<Car> getMyCar() {
		return myCar;
	}

	public void setMyCar(List<Car> myCar) {
		this.myCar = myCar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
class Car{
	
	Car(String name){
		this.name=name;
	}
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
