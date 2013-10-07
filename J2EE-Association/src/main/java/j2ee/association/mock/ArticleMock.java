
/*
 * Created on 7 oct. 2013 ( Time 12:34:11 )
 * Generated by Telosys Tools Generator ( version 2.0.6 )
 */
package j2ee.association.mock;

import java.util.LinkedList;
import java.util.List;

import j2ee.association.bean.Article ;
import j2ee.association.mock.tool.MockValues;

public class ArticleMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public Article createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextString(2) );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public Article createInstance( String arId ) {
		Article entity = new Article();
		// Init Primary Key fields
		entity.setArId( arId) ;
		// Init Data fields
		entity.setArQuantity( mockValues.nextInteger() ) ; // int 
		// Init Link fields (if any)
		// setCommand( TODO ) ; // Command 
		// setCatalogue( TODO ) ; // Catalogue 
		// setListOfCommand( TODO ) ; // List<Command> 
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<Article> createList(int count) {
		List<Article> list = new LinkedList<Article>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}