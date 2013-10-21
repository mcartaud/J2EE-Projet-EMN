package j2ee.association.modele.servlets.tests;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import j2ee.association.modele.servlets.IdentificationServlet;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testsServlet {
	
	private IdentificationServlet service;

	@Before
	public void setUp() {
		service = new IdentificationServlet();
	}
	
	@Test
	public void nologin() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		when(request.getParameter("id")).thenReturn("root");
		when(request.getParameter("password")).thenReturn("admin");
		
		try {
			this.service.connectUser(request);
			verify(request);
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}
