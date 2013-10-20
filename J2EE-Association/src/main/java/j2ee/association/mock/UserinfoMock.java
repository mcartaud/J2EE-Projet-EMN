
/*
 * Created on 20 oct. 2013 ( Time 17:59:59 )
 * Generated by Telosys Tools Generator ( version 2.0.6 )
 */
package j2ee.association.mock;

import java.util.LinkedList;
import java.util.List;

import j2ee.association.bean.Userinfo ;
import j2ee.association.mock.tool.MockValues;

public class UserinfoMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public Userinfo createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextInteger() );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public Userinfo createInstance( int usId ) {
		Userinfo entity = new Userinfo();
		// Init Primary Key fields
		entity.setUsId( usId) ;
		// Init Data fields
		entity.setUsPseudo( mockValues.nextString(255) ) ; // java.lang.String 
		entity.setUsPassword( mockValues.nextString(255) ) ; // java.lang.String 
		entity.setUsName( mockValues.nextString(255) ) ; // java.lang.String 
		entity.setUsFirstname( mockValues.nextString(255) ) ; // java.lang.String 
		entity.setUsAdress( mockValues.nextString(255) ) ; // java.lang.String 
		entity.setUsPostcode( mockValues.nextInteger() ) ; // int 
		entity.setUsTown( mockValues.nextString(255) ) ; // java.lang.String 
		// Init Link fields (if any)
		// setCountry( TODO ) ; // Country 
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<Userinfo> createList(int count) {
		List<Userinfo> list = new LinkedList<Userinfo>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}
