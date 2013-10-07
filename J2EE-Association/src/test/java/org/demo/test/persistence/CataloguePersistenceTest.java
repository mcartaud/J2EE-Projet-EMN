/*
 * Created on 7 oct. 2013 ( Time 10:21:01 )
 * Generated by Telosys Tools Generator ( version 2.0.6 )
 */
package org.demo.test.persistence;


import org.demo.bean.Catalogue ;
import org.demo.mock.CatalogueMock;
import org.demo.persistence.PersistenceServiceProvider;
import org.demo.persistence.services.CataloguePersistence;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test case for Catalogue persistence service
 * 
 * @author Telosys Tools Generator
 *
 */
public class CataloguePersistenceTest 
{
	@Test
	public void test1() {
		
		System.out.println("Test Catalogue persistence : delete + load ..." );
		
		CataloguePersistence service = PersistenceServiceProvider.getService(CataloguePersistence.class);
		
		CatalogueMock mock = new CatalogueMock();
		
		// TODO : set primary key values here 
		process( service, mock, 0  );
		// process( service, mock, ... );
	}

	private void process(CataloguePersistence service, CatalogueMock mock, int caId ) {
		System.out.println("----- "  );
		System.out.println(" . load : " );
		Catalogue entity = service.load( caId );
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
			entity = mock.createInstance( caId ) ;
			Assert.assertNotNull(entity);

			// No reference : insert is possible 
			// Try to insert the new instance
			System.out.println(" . insert : " + entity );
			service.insert(entity);
			System.out.println("   inserted : " + entity );

			System.out.println(" . delete : " );
			boolean deleted = service.delete( caId );
			System.out.println("   deleted = " + deleted);
			Assert.assertTrue(deleted) ;
		}		
	}
}
