/*
 * Created on 7 oct. 2013 ( Time 12:34:12 )
 * Generated by Telosys Tools Generator ( version 2.0.6 )
 */
// This Bean has a basic Primary Key (not composite) 

package j2ee.association.bean;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;


import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Persistent class for entity stored in table "CATALOGUE"
 *
 * @author Telosys Tools Generator
 *
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="CATALOGUE", schema="APP" )
// Define named queries here
// @NamedQueries ( {
//  @NamedQuery ( name="Catalogue.query1", query="SELECT x FROM Catalogue x WHERE  " ),
//  @NamedQuery ( name="Catalogue.query2", query="SELECT x FROM Catalogue x WHERE  " )
// } )
public class Catalogue implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="CA_ID", nullable=false)
    private int        caId         ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="CA_CODE", nullable=false, length=2)
    private String     caCode       ;

    @Column(name="CA_NAME", nullable=false, length=255)
    private String     caName       ;

    @Column(name="CA_PRICE", nullable=false)
    private double     caPrice      ;

    @Column(name="CA_STOCK", nullable=false)
    private int        caStock      ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy="catalogue", targetEntity=Article.class)
    private List<Article> listOfArticle;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Catalogue()
    {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setCaId( int caId )
    {
        this.caId = caId ;
    }
    public int getCaId()
    {
        return this.caId;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : CA_CODE ( VARCHAR ) 
    public void setCaCode( String caCode )
    {
        this.caCode = caCode;
    }
    public String getCaCode()
    {
        return this.caCode;
    }

    //--- DATABASE MAPPING : CA_NAME ( VARCHAR ) 
    public void setCaName( String caName )
    {
        this.caName = caName;
    }
    public String getCaName()
    {
        return this.caName;
    }

    //--- DATABASE MAPPING : CA_PRICE ( DOUBLE ) 
    public void setCaPrice( double caPrice )
    {
        this.caPrice = caPrice;
    }
    public double getCaPrice()
    {
        return this.caPrice;
    }

    //--- DATABASE MAPPING : CA_STOCK ( INTEGER ) 
    public void setCaStock( int caStock )
    {
        this.caStock = caStock;
    }
    public int getCaStock()
    {
        return this.caStock;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    @XmlTransient
    public void setListOfArticle( List<Article> listOfArticle )
    {
        this.listOfArticle = listOfArticle;
    }
    public List<Article> getListOfArticle()
    {
        return this.listOfArticle;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString()
    {
        StringBuffer sb = new StringBuffer(); 
        sb.append(caId); 
        sb.append("|"); 
        sb.append(caCode); 
        sb.append( "|" ); 
        sb.append(caName); 
        sb.append( "|" ); 
        sb.append(caPrice); 
        sb.append( "|" ); 
        sb.append(caStock); 
        return sb.toString(); 
    }

}