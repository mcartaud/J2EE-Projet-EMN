package j2ee.association.modele.servlets;

import j2ee.association.bean.Country;
import j2ee.association.bean.Userinfo;
import j2ee.association.persistence.PersistenceServiceProvider;
import j2ee.association.persistence.services.CountryPersistence;
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
		CountryPersistence service = PersistenceServiceProvider.getService(CountryPersistence.class);
		List<Country> countries = service.loadAll();
		request.setAttribute("countries", countries);
		this.getServletContext().getRequestDispatcher("/jsp/enregistrement.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean userCreated = createUser(request);
		if (userCreated) {
			response.sendRedirect(request.getContextPath()+"/identification");
		} else {
			response.sendRedirect(request.getContextPath()+"/enregistrement");
		}
		
	}

	private boolean createUser(HttpServletRequest request) {
		Map<String, String[]> userInformations = request.getParameterMap();
		Map<String, Object> name = new HashMap<String, Object>();
		
		String password = userInformations.get("userPassword")[0];
		String passwordConfirm = userInformations.get("userPasswordConfirm")[0];
		if (!password.equals(passwordConfirm)) {
			return false;
		}
		
		String userId = userInformations.get("userID")[0];
		name.put("usPseudo", userId);
		
		UserinfoPersistence persistence = PersistenceServiceProvider.getService(UserinfoPersistence.class);
		List<Userinfo> informations = persistence.search(name);
		if (informations.size() == 0) {
			addUser(userInformations, persistence);
			return true;
		} else {
			return false;
		}
	}

	private void addUser(Map<String, String[]> userInformations,
			UserinfoPersistence persistence) {
		CountryPersistence countryPersistence = PersistenceServiceProvider.getService(CountryPersistence.class);
		Userinfo newUser = new Userinfo();
		newUser.setUsPseudo(userInformations.get("userID")[0]);
		newUser.setUsPassword(userInformations.get("userPassword")[0]);
		newUser.setUsName(userInformations.get("userName")[0]);
		newUser.setUsFirstname(userInformations.get("userFirstName")[0]);
		newUser.setUsAdress(userInformations.get("userAddress")[0]);
		newUser.setUsPostcode(Integer.parseInt(userInformations.get("userPostcode")[0]));
		newUser.setUsTown(userInformations.get("userTown")[0]);
		int country = Integer.parseInt(userInformations.get("userCountry")[0]);
		newUser.setCountry(countryPersistence.load(country));
		
		persistence.insert(newUser);
	}
}
