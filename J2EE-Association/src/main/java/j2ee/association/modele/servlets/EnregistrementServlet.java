package j2ee.association.modele.servlets;

import j2ee.association.bean.Userinfo;
import j2ee.association.persistence.PersistenceServiceProvider;
import j2ee.association.persistence.services.UserinfoPersistence;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EnregistrementServlet
 */
public class EnregistrementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnregistrementServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Forwarding the request to the associated view
		this.getServletContext().getRequestDispatcher("/jsp/enregistrement.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkUser(request);
		
	}

	private boolean checkUser(HttpServletRequest request) {
		Map<String, String[]> userInformations = request.getParameterMap();
		Map<String, Object> name = new HashMap<String, Object>();
		
		String userId = userInformations.get("userID")[0];
		name.put("usPseudo", userId);
		
		UserinfoPersistence persistence = PersistenceServiceProvider.getService(UserinfoPersistence.class);
		List<Userinfo> informations = persistence.search(name);
		if (informations.size() == 0) {
			return addUser(userInformations, persistence);
		} else {
			return false;
		}
	}

	private boolean addUser(Map<String, String[]> userInformations,
			UserinfoPersistence persistence) {
		Userinfo newUser = new Userinfo();
		newUser.setUsPseudo(userInformations.get("userID")[0]);
		newUser.setUsPassword(userInformations.get("userName")[0]);
		newUser.setUsName(userInformations.get("userName")[0]);
		newUser.setUsFirstname(userInformations.get("userName")[0]);
		newUser.setUsAdress(userInformations.get("userName")[0]);
		newUser.setUsPostcode(Integer.parseInt(userInformations.get("userName")[0]));
		newUser.setUsTown(userInformations.get("userName")[0]);
		return false;
	}

}
