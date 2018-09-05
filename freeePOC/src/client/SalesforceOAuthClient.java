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

	public String getRefreshToken(String code){
		String s_url = "https://login.salesforce.com/services/oauth2/token?grant_type=authorization_code&client_id=3MVG9YDQS5WtC11rOAJI.dCrM__xjpK8l.vH8pTONv4ICS_0EvJjHali6tHrJS1YXglW1_qvJ9cbrQcj82JLi&client_secret=6665015134456256708&redirect_uri=https://localhost/RefreshTokenGetter&code=";

		s_url = s_url + code;

		System.out.println("url = " + s_url);

		URL url;
		HttpURLConnection con;
		String json = "";
		try {
			url = new URL(s_url);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type","text/json;charset=utf-8");

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

				System.out.println("Response JSON is " + json);

			}else {
				System.out.println("Error" + status);
			}


		} catch (MalformedURLException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

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
}
