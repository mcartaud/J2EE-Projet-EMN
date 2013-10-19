/*
 * Created on 19 oct. 2013 ( Time 12:01:30 )
 * Generated by Telosys Tools Generator ( version 2.0.6 )
 */
package j2ee.association.test.persistence;


import j2ee.association.bean.Userinfo ;
import j2ee.association.mock.UserinfoMock;
import j2ee.association.persistence.PersistenceServiceProvider;
import j2ee.association.persistence.services.UserinfoPersistence;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test case for Userinfo persistence service
 * 
 * @author Telosys Tools Generator
 *
 */
public class UserinfoPersistenceTest 
{
	@Test
	public void test1() {
		
		System.out.println("Test Userinfo persistence : delete + load ..." );
		
		UserinfoPersistence service = PersistenceServiceProvider.getService(UserinfoPersistence.class);
		
		UserinfoMock mock = new UserinfoMock();
		
		// TODO : set primary key values here 
		process( service, mock, 0  );
		// process( service, mock, ... );
	}

	private void process(UserinfoPersistence service, UserinfoMock mock, int usId ) {
		System.out.println("----- "  );
		System.out.println(" . load : " );
		Userinfo entity = service.load( usId );
		if ( entity != null ) {
			// Found 
			System.out.println("   FOUND : " + entity );
			
			// Save (update) with the same values to avoid database integrity errors  
			System.out.println(" . save : " + entity );
			service.save(entity);
			System.out.println("   saved : " + entity );
		}
		else {
			// Not found 
			System.out.println("   NOT FOUND" );
			// Create a new instance 
			entity = mock.createInstance( usId ) ;
			Assert.assertNotNull(entity);

			// This entity references the following entities : 
			// . Country
			/* Insert only if references are OK
			// Try to insert the new instance
			System.out.println(" . insert : " + entity );
			service.insert(entity);
			System.out.println("   inserted : " + entity );
			*/

			System.out.println(" . delete : " );
			boolean deleted = service.delete( usId );
			System.out.println("   deleted = " + deleted);
		}		
	}
}
