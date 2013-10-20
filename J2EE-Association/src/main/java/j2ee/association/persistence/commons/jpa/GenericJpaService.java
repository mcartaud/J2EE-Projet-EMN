/*
 * Created on 20 oct. 2013 ( Date ISO 2013-10-20 - Time 18:00:00 )
 * Generated by Telosys Tools Generator ( version 2.0.6 )
 */
package j2ee.association.persistence.commons.jpa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import j2ee.association.persistence.PersistenceConfig;

/**
 * Generic JPA service operations <br>
 * Provided by Telosys Tools for JPA testing
 * 
 * @param <T>    Entity type
 * @param <PK>   Primary key type
 */
@SuppressWarnings("unchecked")
public abstract class GenericJpaService<T, PK extends java.io.Serializable> {

	private static final boolean TRANSACTIONAL = true ;
	
	private static final Predicate[] VOID_PREDICATE_ARRAY = {};
	
	/**
	 * The class of the entity managed by the concrete service
	 */
	private final Class<T> persistentClass;

	/**
	 * Constructor
	 * @param persistentClass
	 */
	public GenericJpaService(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	/**
	 * Executes a NON TRANSACTIONAL JPA operation with a specific EntityManager <br>
	 * ( created at the beginning of the execution and closed at the end )
	 * 
	 * @param operation the operation to be executed
	 * @return result
	 * @throws PersistenceException
	 */
	private final Object execute(JpaOperation operation) throws PersistenceException {
		return execute(operation, false) ;
	}

	/**
	 * Executes a JPA operation ( TRANSACTIONAL or NOT ) with a specific EntityManager <br>
	 * ( created at the beginning of the execution and closed at the end )
	 * 
	 * @param operation the operation to be executed
	 * @param transactional true for a TRANSACTIONAL operation, false for NOT TRANSACTIONAL  
	 * @return result
	 * @throws PersistenceException
	 */
	private final Object execute(JpaOperation operation, boolean transactional) throws PersistenceException {
		JpaEnvironment jpaEnvironment = new JpaEnvironment( PersistenceConfig.JPA_PERSISTENCE_UNIT_NAME );
		if ( transactional ) {
			return jpaEnvironment.executeWithTransaction(operation) ;
		}
		else {
			return jpaEnvironment.executeWithoutTransaction(operation) ;
			
		}
	}
	
	/**
	 * Find entity by Primary Key
	 * @param primaryKey
	 * @return
	 */
	public T load(final PK primaryKey) {
		// JPA operation definition 
		JpaOperation operation = new JpaOperation() {
			@Override
			public Object exectue(EntityManager em) throws PersistenceException {
				return em.find(persistentClass, primaryKey);
			}
		} ;
		// JPA operation execution 
		return (T) execute(operation) ;
	}

	/**
	 * Load all entities
	 * @param <T>
	 * @param query
	 * @return
	 */
	public List<T> loadAll() {
		// JPA operation definition 
		JpaOperation operation = new JpaOperation() {
			@Override
			public Object exectue(EntityManager em) throws PersistenceException {
				final Query query = em.createQuery("from " + persistentClass.getName());
				return query.getResultList();
			}
		} ;
		// JPA operation execution 
		return (List<T>) execute(operation) ;
	}

	/**
	 * Load a list of entities using a named query without parameter
	 * @param queryName
	 * @return
	 */
	public List<T> loadByNamedQuery(final String queryName) {
		// JPA operation definition 
		JpaOperation operation = new JpaOperation() {
			@Override
			public Object exectue(EntityManager em) throws PersistenceException {
				final Query query = em.createNamedQuery(queryName);
				return query.getResultList();
			}
		} ;
		// JPA operation execution 
		return (List<T>) execute(operation) ;
	}

	/**
	 * Load a list of entities using a named query with parameters
	 * @param queryName
	 * @param queryParameters
	 * @return
	 */
	public List<T> loadByNamedQuery(final String queryName, final Map<String, Object> queryParameters) {
		// JPA operation definition 
		JpaOperation operation = new JpaOperation() {
			@Override
			public Object exectue(EntityManager em) throws PersistenceException {
				final Query query = em.createNamedQuery(queryName);
				final Iterator<String> i = queryParameters.keySet().iterator();
				while (i.hasNext()) {
					String key = i.next();
					query.setParameter(key, queryParameters.get(key));
				}				
				return query.getResultList();
			}
		} ;
		// JPA operation execution 
		return (List<T>) execute(operation) ;
	}

	/**
	 * Insert entity ( TRANSACTIONAL )
	 * @param <T>
	 * @return
	 */
	public void insert(final T entity) {
		// JPA operation definition 
		JpaOperation operation = new JpaOperation() {
			@Override
			public Object exectue(EntityManager em) throws PersistenceException {
				em.persist(entity);
				return null;
			}
		} ;
		// JPA operation execution 
		execute(operation, TRANSACTIONAL) ;
	}

	/**
	 * Save the given entity ( TRANSACTIONAL )
	 * @param <T>
	 * @param entity
	 * @return
	 */
	public T save(final T entityToSave) {
		// JPA operation definition 
		JpaOperation operation = new JpaOperation() {
			@Override
			public Object exectue(EntityManager em) throws PersistenceException {
				T managedEntity = em.merge(entityToSave);
				return managedEntity;
			}
		} ;
		// JPA operation execution 
		return (T) execute(operation, TRANSACTIONAL) ;
	}
	
	/**
	 * Delete entity by primary key ( TRANSACTIONAL )
	 * @param primaryKey
	 */
	public boolean delete(final PK primaryKey) {
		// JPA operation definition 
		JpaOperation operation = new JpaOperation() {
			@Override
			public Object exectue(EntityManager em) throws PersistenceException {
				final T entity = em.find(persistentClass, primaryKey);
				if (entity != null) {
					em.remove(entity);
					return Boolean.TRUE ;
				}
				else {
					return Boolean.FALSE ;
				}
			}
		} ;
		// JPA operation execution 
		Boolean b = (Boolean) execute(operation, TRANSACTIONAL) ;
		return b.booleanValue();
	}

	/**
	 * Search entities using the given query parameters <br>
	 * Returns all the entities if no query parameter 
	 * @param queryParameters the query parameters to be used (can be null )
	 * @return
	 */
	public List<T> search( final Map<String, Object> queryParameters ) {
		if ( queryParameters != null  ) {
			return this.searchWithParameters(queryParameters);
		}
		else {
			return this.loadAll();
		}
	}
	
	/**
	 * Search entities using given query parameters 
	 * @param queryParameters the query parameters to be used (cannot be null )
	 * @return
	 */
	private List<T> searchWithParameters( final Map<String, Object> queryParameters ) {
		// JPA operation definition 
		JpaOperation operation = new JpaOperation() {
			@Override
			public Object exectue(EntityManager em) throws PersistenceException {
				CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
				CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(persistentClass);
				Root<T> from = criteriaQuery.from(persistentClass);

				List<Predicate> predicates = new ArrayList<Predicate>();

				for ( Map.Entry<String, Object> e : queryParameters.entrySet() ) {
					String expression = e.getKey() ;
					Object value = e.getValue() ;
					
					System.out.println("=== Criterion : " + expression + " : " + value );
					
					if ( value != null ) {
						boolean operatorFound = false ;
						int i = expression.indexOf(' ') ;
						if ( i >= 0 ) {
							String name = expression.substring(0, i);
							String oper = expression.substring(i, expression.length()).trim();
							System.out.println("name = '" + name + "'   oper = '" + oper + "'  value = '" + value + "'" );
							if ( oper.length() > 0 ) {
								operatorFound = true ;
								if ( value instanceof String ) {
									String strValue = (String) value ;
									if ( "=".equalsIgnoreCase(oper) ) {
										Predicate p = criteriaBuilder.equal( from.get(name), strValue ) ;
										predicates.add(p) ;
									}
									else if ( "like".equalsIgnoreCase(oper) ) {
										Predicate p = criteriaBuilder.like( from.<String>get(name), strValue ) ;
										predicates.add(p) ;
									}
									else {
										throw new RuntimeException("Search : invalid operator '" + oper +"' for String attribute") ;
									}
								}
								else if ( value instanceof Number ) {
									Number numValue = (Number) value ;
									if ( "=".equalsIgnoreCase(oper) ) {
										Predicate p = criteriaBuilder.equal( from.<Number>get(name), numValue ) ;
										predicates.add(p) ;
									}
									else if ( "!=".equalsIgnoreCase(oper) || "<>".equalsIgnoreCase(oper)) {
										Predicate p = criteriaBuilder.notEqual( from.<Number>get(name), numValue ) ;
										predicates.add(p) ;
									}
									else if ( ">".equalsIgnoreCase(oper) ) {
										Predicate p = criteriaBuilder.gt( from.<Number>get(name), numValue ) ;
										predicates.add(p) ;
									}
									else if ( "<".equalsIgnoreCase(oper) ) {
										Predicate p = criteriaBuilder.lt( from.<Number>get(name), numValue ) ;
										predicates.add(p) ;
									}
									else if ( ">=".equalsIgnoreCase(oper) ) {
										Predicate p = criteriaBuilder.ge( from.<Number>get(name), numValue ) ;
										predicates.add(p) ;
									}
									else if ( "<=".equalsIgnoreCase(oper) ) {
										Predicate p = criteriaBuilder.le( from.<Number>get(name), numValue ) ;
										predicates.add(p) ;
									}
									else {
										throw new RuntimeException("Search : invalid operator '" + oper +"' for Number attribute") ;
									}
								}
							}
						}
						if ( ! operatorFound ) {
							predicates.add( criteriaBuilder.equal( from.get(expression), value ) ) ;
						}
					}
				}
				
				criteriaQuery.where( predicates.toArray(VOID_PREDICATE_ARRAY) );
				
				TypedQuery<T> typedQuery = em.createQuery(criteriaQuery);
				return typedQuery.getResultList();
			}
		} ;
		
		// JPA operation execution 
		return (List<T>) execute(operation) ;
	}
	
}
