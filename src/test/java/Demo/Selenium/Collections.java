package Demo.Selenium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


public class Collections {
	
	public static void main(String[] args) {
		/*List<String> lst = new ArrayList<String>();
		lst.add("itc");
		lst.add("infotech");
		lst.add("Bangalore");
		
		ListIterator<String> it = lst.listIterator();
		it.add("Bangalore");
		it.add("karnataka");
		while(it.hasPrevious()) {
			System.out.println(it.previous());
		}
		System.out.println(lst);*/
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("WestBengal", "Kolkata");
		map.put("AP", "Vizag");
		map.put("TamilNadu", "Chennai");
		map.put("", "");
		map.put("4", "");
		Map<String,String> map1 = new HashMap<String,String>();
		map1.putAll(map);
		Map<String,String> map2 = new LinkedHashMap<String,String>();
		map2.put("WestBengal", "Kolkata");
		map2.put("AP", "Vizag");
		map2.put("TamilNadu", "Chennai");
		map2.put("", "");
		map2.put("4", "");
		//map1.remove("2");
		//map1.replace("2", "Bengaluru");
		System.out.println("Linked Hash Map: " +map2);
		
		
		
		 Iterator<Entry<String, String>> itr=map1.entrySet().iterator();  
		   while(itr.hasNext()){  
		   System.out.println(itr.next());  
		  }  
		   
		   Map<String,String> mapp = new TreeMap<String, String>();
		   mapp.put("Karnataka", "Bangalore");
		   mapp.put("odisha", "Bhubaneswar");
		   mapp.put("Bihar", "Patna");
		   mapp.put("", "");
		   System.out.println(mapp.entrySet());
		
		
		/*HashSet<String> set=new HashSet<String>();  
		  set.add("Ravi");  
		  set.add("Vijay");  
		  //set.add("Ravi"); 
		  
		  Iterator<String> itr=set.iterator();  
		   while(itr.hasNext()){  
		   System.out.println(itr.next());  
		  }  */
	}

}
