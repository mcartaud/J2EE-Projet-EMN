/*
 * Created on 20 oct. 2013 ( Time 17:59:59 )
 * Generated by Telosys Tools Generator ( version 2.0.6 )
 */

package j2ee.association.persistence.services.jpa;

import j2ee.association.bean.Userinfo ;
import j2ee.association.persistence.commons.jpa.GenericJpaService;
import j2ee.association.persistence.services.UserinfoPersistence;

/**
 * JPA implementation for basic persistence operations ( entity "Userinfo" )
 * 
 * @author Telosys Tools Generator
 *
 */
public class UserinfoPersistenceJPA extends GenericJpaService<Userinfo, Integer> implements UserinfoPersistence {

	/**
	 * Constructor
	 */
	public UserinfoPersistenceJPA() {
		super(Userinfo.class);
	}

	public Userinfo load( int usId ) {
		return super.load( usId );
	}

	public boolean delete( int usId ) {
		return super.delete( usId );
	}

	public boolean delete(Userinfo entity) {
		if ( entity != null ) {
			return super.delete( entity.getUsId() );
		}
		return false ;
	}

}
