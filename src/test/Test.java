package test;

import org.json.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONArray;

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
			System.out.println(ja.getJSONObject(0).get("nickname"));
			// String inputLine;
			/*
			while ((inputLine = in.readLine()) != null)
				System.out.println(inputLine);
			in.close();
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
