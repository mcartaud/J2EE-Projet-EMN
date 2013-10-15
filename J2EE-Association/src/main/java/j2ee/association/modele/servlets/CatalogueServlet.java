package j2ee.association.modele.servlets;

import j2ee.association.bean.Article;
import j2ee.association.persistence.PersistenceServiceProvider;
import j2ee.association.persistence.services.ArticlePersistence;

import java.io.IOException;
import java.util.List;

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
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
