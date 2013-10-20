package j2ee.association.modele.servlets;

import j2ee.association.bean.Article;
import j2ee.association.persistence.PersistenceServiceProvider;
import j2ee.association.persistence.services.ArticlePersistence;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CommandeServlet
 */
public class CommandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommandeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Fetching all articles in the data base, storing them in a list of
		// beans
		ArticlePersistence service = PersistenceServiceProvider
				.getService(ArticlePersistence.class);
		List<Article> articles = service.loadAll();
		// Passing the list of beans to the jsp via the request
		request.setAttribute("articles", articles);
		// Forwarding the request to the associated view
		this.getServletContext().getRequestDispatcher("/jsp/commande.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		boolean commandOK = true;
		if (request.getParameter("action").equals("Commander")) {
			commandOK = sendCommand(session);
			if (commandOK) {
				response.sendRedirect(request.getContextPath()+"/index");
			} else {
				response.sendRedirect(request.getContextPath()+"/commande");
			}
		} else {
			eraseCommand(session);
			response.sendRedirect(request.getContextPath()+"/commande");
		}
	}

	private void eraseCommand(HttpSession session) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		session.setAttribute("command", map);
	}

	private boolean sendCommand(HttpSession session) {
		ArticlePersistence persistence = PersistenceServiceProvider.getService(ArticlePersistence.class);
		
		@SuppressWarnings("unchecked")
		Map<String, Integer> map = (Map<String, Integer>) session.getAttribute("command");
		Set<String> elementName = map.keySet();
		for (String element : elementName) {
			int quantity = map.get(element);
			Article article = persistence.load(element);
			if (article.getArStock() < quantity) {
				return false;
			}
		}
		for (String element : elementName) {
			int quantity = map.get(element);
			Article article = persistence.load(element);
			int stock = article.getArStock();
			article.setArStock(stock - quantity);
			persistence.save(article);
		}
		eraseCommand(session);
		return true;
	}
	
	

}
