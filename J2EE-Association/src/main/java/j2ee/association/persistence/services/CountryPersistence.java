/*
 * Created on 19 oct. 2013 ( Time 12:01:29 )
 * Generated by Telosys Tools Generator ( version 2.0.6 )
 */
package j2ee.association.persistence.services;

import java.util.List;
import java.util.Map;

import j2ee.association.bean.Country ;

/**
 * Basic persistence operations for entity "Country"
 * 
 * This Bean has a basic Primary Key : int
 *
 * @author Telosys Tools Generator
 *
 */
public interface CountryPersistence {

	/**
	 * Deletes the given entity <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param country
	 * @return true if found and deleted, false if not found
	 */
	public boolean delete(Country country) ;

	/**
	 * Deletes the entity by its Primary Key <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param coId
	 * @return true if found and deleted, false if not found
	 */
	public boolean delete(int coId) ;

	/**
	 * Inserts the given entity and commit <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param country
	 */
	public void insert(Country country) ;

	/**
	 * Loads the entity for the given Primary Key <br>
	 * @param coId
	 * @return the entity loaded (or null if not found)
	 */
	public Country load(int coId) ;

	/**
	 * Loads ALL the entities (use with caution)
	 * @return
	 */
	public List<Country> loadAll() ;

	/**
	 * Loads a list of entities using the given "named query" without parameter 
	 * @param queryName
	 * @return
	 */
	public List<Country> loadByNamedQuery(String queryName) ;

	/**
	 * Loads a list of entities using the given "named query" with parameters 
	 * @param queryName
	 * @param queryParameters
	 * @return
	 */
	public List<Country> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) ;

	/**
	 * Saves (create or update) the given entity <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param country
	 * @return
	 */
	public Country save(Country country) ;

	/**
	 * Search the entities matching the given search criteria
	 * @param criteria
	 * @return
	 */
	public List<Country> search( Map<String, Object> criteria ) ;
}
