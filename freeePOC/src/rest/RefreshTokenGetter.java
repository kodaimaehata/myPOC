package rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import sun.net.www.protocol.http.HttpURLConnection;

/**
 * Servlet implementation class RefreshTokenGetter
 */
@WebServlet(description = "リフレッシュトークンを取得する", urlPatterns = { "/RefreshTokenGetter" })
public class RefreshTokenGetter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefreshTokenGetter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String s_url = "https://login.salesforce.com/services/oauth2/token?grant_type=authorization_code&client_id=3MVG9YDQS5WtC11rOAJI.dCrM__xjpK8l.vH8pTONv4ICS_0EvJjHali6tHrJS1YXglW1_qvJ9cbrQcj82JLi&client_secret=6665015134456256708&redirect_uri=https://localhost/RefreshTokenGetter&code=";
		String code = request.getParameter("code");
		s_url = s_url + code;
		
		URL url = new URL(s_url);
		
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("Get");
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
			String json = bufReader.readLine();
			bufReader.close();
			inReader.close();
			in.close();
			
			try {
				JSONObject jsonObj = new JSONObject(json);
				String refresh_token = jsonObj.getString("refresh_token");
				System.out.println(refresh_token);
			}catch(JSONException e) {
				
			}
			
			
		}else {
			System.out.println("Error" + status);
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
