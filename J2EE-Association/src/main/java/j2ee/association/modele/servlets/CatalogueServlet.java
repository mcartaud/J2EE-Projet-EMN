package j2ee.association.modele.servlets;

import j2ee.association.bean.Article;
import j2ee.association.persistence.PersistenceServiceProvider;
import j2ee.association.persistence.services.ArticlePersistence;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CatalogueServlet
 */
public class CatalogueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CatalogueServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Fetching all articles in the data base, storing them in a list of beans
		ArticlePersistence service = PersistenceServiceProvider.getService(ArticlePersistence.class);
		List<Article> articles = service.loadAll();
		// Passing the list of beans to the jsp via the request
		request.setAttribute("articles", articles);
		// Forwarding to the jsp with the beans added
		this.getServletContext().getRequestDispatcher("/jsp/catalogue.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map<String, Integer> command = (Map<String, Integer>)request.getSession().getAttribute("command");
		if(command==null) {
			command = new HashMap<String, Integer>();
			request.getSession().setAttribute("command", command);
		}
	
		// Updating the order for each article
		Enumeration<String> parameterNames = request.getParameterNames();
		while(parameterNames.hasMoreElements()) {
			String articleOrdered = parameterNames.nextElement();
			try {
				Integer orderQuantity = Integer.parseInt( request.getParameter(articleOrdered) );
				
				if(orderQuantity > 0) {
					// TODO deal with the return value
					handleCommand(command, articleOrdered, orderQuantity);
				}
			}catch(NumberFormatException nfe) {
				// must not be a command parameter
				System.out.println(nfe);
			}
		}
		
		// At this point, the command is updated in the session for each ordered article
		// Forwarding to the command page
		response.sendRedirect(request.getContextPath()+"/commande");
	}
	
	public boolean handleCommand(Map<String, Integer> command, String articleOrdered, int orderQuantity) {
		// Loading the article articleOrdered
		ArticlePersistence service = PersistenceServiceProvider.getService(ArticlePersistence.class);
		Article article = service.load( articleOrdered );
		
		// Checking if there are enough in stock to satisfy the command
		int oldQuantity = 0;
		if( command.get( article.getArId() ) != null) oldQuantity = command.get( article.getArId() );
		int quantityToFetch = orderQuantity + oldQuantity;
		if( article.getArStock() >= quantityToFetch ) {
			command.put(articleOrdered, quantityToFetch);
			return true;
		} else {
			return false;
		}
	}

//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Map<String, Integer> command = retrieveCommand(request);
//
//		handleParameters(request, command);
//		request.setAttribute("command", command);
//		response.sendRedirect(request.getContextPath()+"/commande");
//	}
//
//	/**
//	 * Checks all POST parameters to determine whether it has been ordered or not
//	 * @param request the HTTP request pushed by the user
//	 */
//	private void handleParameters(HttpServletRequest request, Map<String, Integer> command) {
//		Enumeration<String> attributeName = request.getParameterNames();
//		while (attributeName.hasMoreElements()) {
//			String param_key = (String) attributeName.nextElement();
//			String param_value = request.getParameter(param_key);
//			
//			int quantityOrdered;
//			try{
//				quantityOrdered = Integer.parseInt(param_value);
//			}catch(NumberFormatException nfe) {
//				quantityOrdered = 0;
//			}
//			
//			if (quantityOrdered >= 0) {
//				enoughInStock(param_key, quantityOrdered, command, request);
//			}
//		}
//	}
//
//	private boolean enoughInStock(String code, int quantity, Map<String, Integer> command, HttpServletRequest request) {
//		Map<String, Object> name = new HashMap<String, Object>();
//		name.put("arCode", code);
//		ArticlePersistence persistence = PersistenceServiceProvider.getService(ArticlePersistence.class);
//		List<Article> informations = persistence.search(name);
//		if (informations.size() != 1) {
//			return false;
//		} else {
//			appendToCommand(quantity, informations.get(0), command, request);
//			return true;
//		}
//	}
//
//	private void appendToCommand(int quantity, Article article, Map<String, Integer> command, HttpServletRequest request) {
//		int quantityOrdered = 0;
//		if (command.containsKey(article.getArCode())) {
//			quantityOrdered = command.get(article.getArCode());
//		}
//		quantityOrdered += quantity;
//		if (article.getArStock() < quantityOrdered) {
//			request.setAttribute(article.getArCode(), false);
//		} else {
//			command.put(article.getArCode(), quantityOrdered);
//		}
//	}
//	
//	private Map<String, Integer> retrieveCommand(HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		@SuppressWarnings("unchecked")
//		Map<String, Integer> command = (Map<String, Integer>)session.getAttribute("command");
//		
//		if (command == null) {
//			command = new HashMap<String, Integer>();
//		}
//		
//		return command;
//	}

}
