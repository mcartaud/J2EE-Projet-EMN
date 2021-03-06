/*
 * Created on 20 oct. 2013 ( Time 17:59:58 )
 * Generated by Telosys Tools Generator ( version 2.0.6 )
 */
package j2ee.association.persistence.services.fake;

import java.util.List;
import java.util.Map;

import j2ee.association.bean.Article ;
import j2ee.association.persistence.commons.fake.GenericFakeService;
import j2ee.association.persistence.services.ArticlePersistence;

/**
 * Fake persistence service implementation ( entity "Article" )
 *
 * @author Telosys Tools Generator
 */
public class ArticlePersistenceFAKE extends GenericFakeService<Article> implements ArticlePersistence {

	public ArticlePersistenceFAKE () {
		super(Article.class);
	}
	
	protected Article buildEntity(int index) {
		Article entity = new Article();
		// Init fields with mock values
		entity.setArId( nextString() ) ;
		entity.setArCode( nextString() ) ;
		entity.setArName( nextString() ) ;
		entity.setArPrice( nextDouble() ) ;
		entity.setArStock( nextInteger() ) ;
		return entity ;
	}
	
	
	public boolean delete(Article entity) {
		log("delete ( Article : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( String arId ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(Article entity) {
		log("insert ( Article : " + entity + ")" ) ;
	}

	public Article load( String arId ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<Article> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<Article> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<Article> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public Article save(Article entity) {
		log("insert ( Article : " + entity + ")" ) ;
		return entity;
	}

	public List<Article> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

}
