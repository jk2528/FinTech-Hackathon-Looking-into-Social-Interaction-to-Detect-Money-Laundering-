package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class Customer {
	public Customer(String c, String fn, ArrayList<Account> aid, String ln, String city, 
			int sn, int zip, String state, String street_name){
		customer_id = c;
		first_name = fn;
		account_id=aid;
		last_name = ln;
		this.city =city;
		street_number = sn; 
		this.zip =zip;
		this.state = state;
		this.street_name=street_name;
		
	}

	private String customer_id;
	private String first_name;
	private ArrayList<Account> account_id;
	private String last_name;
	private String city;
	private int street_number;
	private int zip;
	private String state;
	private String street_name;
	

	public String getCustomer_id() {return customer_id;}
	public String getfirst_name() {return first_name;}
	public ArrayList<Account> getAccounts() {return account_id;}
	public String getlast_name() {return last_name;}
	public String getcity() {return city;}

	public int getStreet_number() {return street_number;}
	public int getZip() {return zip;}
	public String getstate() {return state;}
	public String getstreet() {return street_name; }

	
	public static HashMap<String,Customer> createMap(URL url) throws IOException{
		URLConnection myURLConnection = url.openConnection();
		try {
			myURLConnection.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
		String jsonText = readAll(in);
		JSONObject json = new JSONObject(jsonText);
		return createMap(json);
		}
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	
	public static HashMap<String, Customer> createMap(JSONObject json){
		JSONArray ja = json.getJSONArray("results");
		HashMap<String, Customer> c = new HashMap<String, Customer>();
		
		for (int i = 0; i < ja.length(); i++) {
			JSONObject temp = ja.getJSONObject(i);
			String id = temp.getString("_id");
			String fn = temp.getString("first_name");
			ArrayList<Account> a = new ArrayList<Account>();
			String ln = temp.getString("last_name");
			String g = temp.getString("city");
			String n1 = temp.getString("street_number");
			int y = Integer.parseInt(n1.substring(0));
			String n2 = temp.getString("zip");
			int m = Integer.parseInt(n2.substring(0));
			String sta = temp.getString("state");
			String str = temp.getString("street_name");
			Customer cust= new Customer(id,fn, a, ln, g,y,m,sta,str);
			c.put(id,cust);
			
		}
		return c;
	}
	
	public int getTotalAccNum() {
		return account_id.size();
	}
	
	public void addAccount(Account a) {
		account_id.add(a);
		
	}
	
	
	
}
