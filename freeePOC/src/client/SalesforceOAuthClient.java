package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class SalesforceOAuthClient {
	
	URL url ;
	HttpURLConnection con ;
	
	
		
	public String getRefreshToken(String code){
//		String s_url = "https://login.salesforce.com/services/oauth2/token?grant_type=authorization_code&client_id=3MVG9YDQS5WtC11rOAJI.dCrM__xjpK8l.vH8pTONv4ICS_0EvJjHali6tHrJS1YXglW1_qvJ9cbrQcj82JLi&client_secret=6665015134456256708&redirect_uri=https://localhost/RefreshTokenGetter&code=";
//		String s_url = "https://login.salesforce.com/services/oauth2/token?grant_type=authorization_code&client_id=3MVG9YDQS5WtC11rOAJI.dCrM__xjpK8l.vH8pTONv4ICS_0EvJjHali6tHrJS1YXglW1_qvJ9cbrQcj82JLi&client_secret=6665015134456256708&redirect_uri=https://localhost:8443/freeePOC/RefreshTokenGetter&code=";
		String s_url = "https://kmcustomercommunity-developer-edition.ap4.force.com/customerservice/services/oauth2/token?grant_type=authorization_code&client_id=3MVG9YDQS5WtC11rOAJI.dCrM__xjpK8l.vH8pTONv4ICS_0EvJjHali6tHrJS1YXglW1_qvJ9cbrQcj82JLi&client_secret=6665015134456256708&redirect_uri=https://localhost:8443/freeePOC/RefreshTokenGetter&code=";

		s_url = s_url + code;

		System.out.println("url = " + s_url);
		
		openPOSTConnection(s_url);
		String json = sendRequest();

		String refresh_token = "";
		try {
			JSONObject jsonObj = new JSONObject(json);
			refresh_token = jsonObj.getString("refresh_token");
			System.out.println("Refresh_token is " + refresh_token);
		}catch(JSONException e) {
			e.printStackTrace();
		}

		return refresh_token;

	}
	
	public String getAccessToken(String refresh_token) {
		
//		String s_url = "https://login.salesforce.com/services/oauth2/token?grant_type=refresh_token&client_id=3MVG9YDQS5WtC11rOAJI.dCrM__xjpK8l.vH8pTONv4ICS_0EvJjHali6tHrJS1YXglW1_qvJ9cbrQcj82JLi&refresh_token=";
		String s_url = "https://kmcustomercommunity-developer-edition.ap4.force.com/customerservice/services/oauth2/token?grant_type=refresh_token&client_id=3MVG9YDQS5WtC11rOAJI.dCrM__xjpK8l.vH8pTONv4ICS_0EvJjHali6tHrJS1YXglW1_qvJ9cbrQcj82JLi&refresh_token=";

		s_url = s_url + refresh_token;
		
		System.out.println(s_url);
		
		openPOSTConnection(s_url);
		String response = sendRequest();

		
		String accessToken = "";

		try {
			JSONObject jsonObj = new JSONObject(response);
			accessToken = jsonObj.getString("access_token");
			System.out.println("accessToken is " + accessToken);
		}catch(JSONException e) {
			e.printStackTrace();
		}
		
		return accessToken;
	}
	
	public String getAccounts(String access_token) {
		String accountsJSON = "";
		
//		String s_url = "https://ap4.salesforce.com/services/apexrest/Act/";
		String s_url = "https://kmcustomercommunity-developer-edition.ap4.force.com/customerservice/services/apexrest/Act/";

		openGETConnection(s_url);
		con.setRequestProperty("Authorization", " Bearer " + access_token);
		
		accountsJSON = sendRequest();
		
		return accountsJSON;
	}
	
	private void openPOSTConnection(String s_url) {
		try {
			url = new URL(s_url);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
		} catch (MalformedURLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
	}
	
	private void openGETConnection(String s_url) {
		try {
			url = new URL(s_url);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
		} catch (MalformedURLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
	}
	
	public String sendRequest() {
//		URL url;
//		HttpURLConnection con;
		String json = "";
		try {
//			url = new URL(s_url);
//			con = (HttpURLConnection)url.openConnection();
//			con.setRequestMethod("POST");
//			con.setRequestProperty("Content-Type","text/json;charset=utf-8");

			con.connect();

			int status = con.getResponseCode();

			if(status == HttpURLConnection.HTTP_OK) {
				InputStream in = con.getInputStream();
				String encoding = con.getContentEncoding();

	            if(null == encoding){
	                encoding = "UTF-8";
	            }
	            InputStreamReader inReader = new InputStreamReader(in, encoding);
				BufferedReader bufReader = new BufferedReader(inReader);
				json = bufReader.readLine();
				bufReader.close();
				inReader.close();
				in.close();



			}else {
				System.out.println("Error code : " + status);
				System.out.println("Error message : " + con.getResponseMessage());

				InputStream in = con.getInputStream();
				String encoding = con.getContentEncoding();
	            InputStreamReader inReader = new InputStreamReader(in, encoding);
				BufferedReader bufReader = new BufferedReader(inReader);
				System.out.println(bufReader.readLine());
				bufReader.close();
				inReader.close();
				in.close();
				
				
				return "";
			}
			
			


		} catch (MalformedURLException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		System.out.println("Response JSON is " + json);
		
		return json;


	}
}
