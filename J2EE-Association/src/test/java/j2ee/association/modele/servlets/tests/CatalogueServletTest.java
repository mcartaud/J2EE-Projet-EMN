package j2ee.association.modele.servlets.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import j2ee.association.modele.servlets.CatalogueServlet;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class CatalogueServletTest {

	private CatalogueServlet service;
	
	@Before
	public void setUp() {
		service = new CatalogueServlet();
	}
	
	@Test
	public void correctAmount() {
		boolean result = service.handleCommand(new HashMap<String, Integer>(), "1", 5);
		assertTrue(result);
	}
	
	@Test
	public void wrongAmount() {
		boolean result = service.handleCommand(new HashMap<String, Integer>(), "1", 100);
		assertFalse(result);
	}
}
