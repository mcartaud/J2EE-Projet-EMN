package j2ee.association.modele.servlets.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import j2ee.association.modele.servlets.IdentificationServlet;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;

public class LoginServletTest {
	
	@Before
	public void setUp() {
	}
	
	@Test
	public void testLogin() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String userPseudo = "root";
        String passwd = "admin";
        //test user OK
        IdentificationServlet serviceLogin = spy(new IdentificationServlet());
        boolean retour = serviceLogin.checkUnicity(userPseudo, passwd);
        assertEquals(retour, true);
        
        //test pswd KO
        passwd = "root";
        retour = serviceLogin.checkUnicity(userPseudo, passwd);
        assertEquals(retour, false);
        
        //test user KO
        userPseudo = "Richelieu";
        passwd = "admin";
        retour = serviceLogin.checkUnicity(userPseudo, passwd);
        assertEquals(retour, false);
	}

}
