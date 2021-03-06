
/*
 * Created on 20 oct. 2013 ( Time 17:59:58 )
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

		return createInstance( mockValues.nextString(10) );
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
		entity.setArCode( mockValues.nextString(2) ) ; // java.lang.String 
		entity.setArName( mockValues.nextString(255) ) ; // java.lang.String 
		entity.setArPrice( mockValues.nextDouble() ) ; // double 
		entity.setArStock( mockValues.nextInteger() ) ; // int 
		// Init Link fields (if any)
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
