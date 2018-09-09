package client;

import org.junit.Test;

public class SalesforceOAuthClientTest {

	@Test
	public void test() {
		SalesforceOAuthClient cli = new SalesforceOAuthClient();
		try{
			String reflesh_token = cli.getRefreshToken("aPrxFYRxG6g7t8oBX6FqguMfdYJGwBgO5kCavp7tmtaOYhuZ8e1q.9Y_9DeX_JVPOyfBR9jF3w%3D%3D");
			System.out.println(reflesh_token);
			assert(true);
		}catch(Exception e){
			e.printStackTrace();
			assert(false);
		}

	}
	
	@Test
	public void getaccess_tokentest() {
		String refresh_token = "5Aep861..zRMyCurAUqXuPX5uJN1Yk7ghc6h4Cv7m6IPMhhMvOivwnD7dLeOFes5eM6el.JJ8pgW8h3tTQ3ngrr";
		
		SalesforceOAuthClient cli = new SalesforceOAuthClient();
		
		String access_token = cli.getAccessToken(refresh_token) ;
		
		System.out.println(access_token);
		
	}
	
	@Test
	public void getAccountsTest() {
		String access_token = "00D6F000002Tjy4!AQgAQMyw9pAcGsoKLTvrfRRICgxLjpcxREr.JvELo3BBNDog58rARvEW.PfdcyUP4x3rBHl.71DUa_hsDsVgq4KxWWgEBTEQ";
		
		SalesforceOAuthClient cli = new SalesforceOAuthClient();
		String accounts = cli.getAccounts(access_token);
		
		System.out.println(accounts);
	}

}
