package j2ee.association.modele.servlets;

import j2ee.association.bean.Userinfo;
import j2ee.association.persistence.PersistenceServiceProvider;
import j2ee.association.persistence.services.UserinfoPersistence;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class IdentificationServlet
 */
public class IdentificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdentificationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Forwarding the request to the associated view					
		this.getServletContext().getRequestDispatcher("/jsp/identification.jsp").include(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[IDENTIFICATION] destroying previous sessions");
		request.getSession().invalidate();
		boolean userConnected;
		try {
			userConnected = connectUser(request);
		} catch (NoSuchAlgorithmException e) {
			userConnected = false;
		}
		if (userConnected) {			
			response.sendRedirect(request.getContextPath()+"/index");
		} else {
			response.sendRedirect(request.getContextPath()+"/identification");
		}
	}

	private boolean connectUser(HttpServletRequest in) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		HttpServletRequest request = in;
		String userName = request.getParameter("id");
		String userPasswd = request.getParameter("password");
		if (checkUnicity(userName, userPasswd)) {
			HttpSession session = request.getSession();
			session.setAttribute("userName", userName);
			return true;
		}
		return false;
	}
	
	private boolean checkUnicity(String userName, String userPasswd) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		Map<String, Object> name = new HashMap<String, Object>();
		name.put("usPseudo", userName);
		UserinfoPersistence service = PersistenceServiceProvider.getService(UserinfoPersistence.class);
		List<Userinfo> informations = service.search(name);
		if (informations.size() != 1) {
			return false;
		} else {
			return checkUser(userName, userPasswd, informations.get(0));
		}
	}

	private boolean checkUser(String userName, String userPasswd, Userinfo userInfo) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		String pass = computeInMd5(userPasswd);
		if (userName.equals(userInfo.getUsPseudo())) {
			if (pass.equals(userInfo.getUsPassword())) {
				return true;
			}
		}
		return false;
	}

	private String computeInMd5(String userPasswd) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		byte[] passBytes = userPasswd.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] md5 = md.digest(passBytes);
		StringBuffer sb = new StringBuffer();
        
		for (int i = 0; i < md5.length; i++) {
                sb.append(Integer.toString((md5[i] & 0xff) + 0x100, 16)
                                .substring(1));
        }
		return sb.toString();
	}
	
}
