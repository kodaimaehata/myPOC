package client;

import org.junit.Test;

public class SalesforceOAuthClientTest {

	@Test
	public void test() {
		SalesforceOAuthClient cli = new SalesforceOAuthClient();
		try{
			String reflesh_token = cli.getRefreshToken("aPrxFYRxG6g7t8oBX6FqguMfdbgPH.qXMc78WqbfrltLkG4bcVhd1YyQdnL9E73R2afTZ7ChWA%3D%3D");
			System.out.println(reflesh_token);
			assert(true);
		}catch(Exception e){
			e.printStackTrace();
			assert(false);
		}

	}

}
