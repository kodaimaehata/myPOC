package client;

import org.junit.Test;

public class SalesforceOAuthClientTest {

	@Test
	public void test() {
		SalesforceOAuthClient cli = new SalesforceOAuthClient();
		try{
			String reflesh_token = cli.getRefreshToken("");
			System.out.println(reflesh_token);
			assert(true);
		}catch(Exception e){
			e.printStackTrace();
			assert(false);
		}

	}
	
	@Test
	public void getaccess_tokentest() {
		String refresh_token = "";
		
		SalesforceOAuthClient cli = new SalesforceOAuthClient();
		
		String access_token = cli.getAccessToken(refresh_token) ;
		
		System.out.println(access_token);
		
	}
	
	@Test
	public void getAccountsTest() {
		String access_token = "";
		
		SalesforceOAuthClient cli = new SalesforceOAuthClient();
		String accounts = cli.getAccounts(access_token);
		
		System.out.println(accounts);
	}

}
