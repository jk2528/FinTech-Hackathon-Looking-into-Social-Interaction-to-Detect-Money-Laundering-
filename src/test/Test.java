package test;

import org.json.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;

import test.Account.Type;

public class Test {
	public static void main(String args[]) {
		try {
			URL myURL = new URL(
					"http://api.reimaginebanking.com/enterprise/accounts?key=358d729f3e85f5d73acd2790b0ae32b6");
			URLConnection myURLConnection = myURL.openConnection();
			myURLConnection.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
			String jsonText = readAll(in);
			JSONObject json = new JSONObject(jsonText);
			JSONArray ja = json.getJSONArray("results");
			ArrayList<Account> a = new ArrayList<>();
			for (int i = 0; i < ja.length(); i++) {
				JSONObject temp = ja.getJSONObject(i);
				String id = temp.getString("_id");
				int bal = temp.getInt("balance");
				String c_id = temp.getString("customer_id");
				String nickname = temp.getString("nickname");
				int rewards = temp.getInt("rewards");
				String type = temp.getString("type");
				Type t = null;
				switch (type) {
				case "savings":
					t = Type.SAVINGS;
					break;
				case "checking":
					t = Type.CHECKING;
					break;
				case "credit_card":
					t = Type.CREDIT_CARD;
					break;
				}

				a.add(new Account(id, bal, c_id, nickname, rewards, t));
			}
			System.out.println(ja.getJSONObject(0).get("nickname"));
			// String inputLine;
			/*
			 * while ((inputLine = in.readLine()) != null)
			 * System.out.println(inputLine); in.close();
			 */
		} catch (MalformedURLException e) {
			// new URL() failed
			// ...
		} catch (IOException e) {
			// openConnection() failed
			// ...
		}
	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
}
