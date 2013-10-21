package j2ee.association.modele.servlets.tests;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import j2ee.association.modele.servlets.IdentificationServlet;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import j2ee.association.bean.Userinfo;

import org.junit.Before;
import org.junit.Test;

public class testsServlet {
	
	private IdentificationServlet service;

	@Before
	public void setUp() {
	}
	
	@Test
	public void nologin() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Userinfo  adh = new Userinfo();
        String userPseudo = "root";
        String passwd = "21232f297a57a5a743894a0e4a801fc3";
        //test avec user ok
        IdentificationServlet serviceLogin = spy(new IdentificationServlet());
        boolean retour = serviceLogin.checkUnicity("root", "admin");
        assertEquals(retour, true);
       // Userinfo ad = serviceLogin.login("log","pass");
 //       assertNotNull(ad);
  //      assertSame(adh,ad);
	}

}
