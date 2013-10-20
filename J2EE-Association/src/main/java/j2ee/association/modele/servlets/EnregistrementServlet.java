package j2ee.association.modele.servlets;

import j2ee.association.bean.Country;
import j2ee.association.bean.Userinfo;
import j2ee.association.persistence.PersistenceServiceProvider;
import j2ee.association.persistence.services.CountryPersistence;
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
		boolean userCreated;
		try {
			userCreated = createUser(request);
		} catch (NoSuchAlgorithmException e) {
			userCreated = false;
		}
		if (userCreated) {
			response.sendRedirect(request.getContextPath()+"/identification");
		} else {
			response.sendRedirect(request.getContextPath()+"/enregistrement");
		}
		
	}

	private boolean createUser(HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
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
			UserinfoPersistence persistence) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		CountryPersistence countryPersistence = PersistenceServiceProvider.getService(CountryPersistence.class);
		Userinfo newUser = new Userinfo();
		newUser.setUsPseudo(userInformations.get("userID")[0]);
		newUser.setUsPassword(computeInMd5(userInformations));
		newUser.setUsName(userInformations.get("userName")[0]);
		newUser.setUsFirstname(userInformations.get("userFirstName")[0]);
		newUser.setUsAdress(userInformations.get("userAddress")[0]);
		String postcode = userInformations.get("userPostcode")[0];
		int post;
		if (postcode.isEmpty()) {
			post = 0;
		} else {
			post = Integer.parseInt(postcode);
		}
		newUser.setUsPostcode(post);
		newUser.setUsTown(userInformations.get("userTown")[0]);
		int country = Integer.parseInt(userInformations.get("userCountry")[0]);
		Country userCountry = countryPersistence.load(country);
		newUser.setCountry(userCountry);
		
		persistence.save(newUser);
		//persistence.insert(newUser);
	}

	private String computeInMd5(Map<String, String[]> userInformations)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		String pass = userInformations.get("userPassword")[0];
		byte[] passBytes = pass.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] md5 = md.digest(passBytes);
		return md5.toString();
	}
}
