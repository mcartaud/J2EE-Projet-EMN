/*
 * Created on 19 oct. 2013 ( Time 12:01:30 )
 * Generated by Telosys Tools Generator ( version 2.0.6 )
 */
// This Bean has a basic Primary Key (not composite) 

package j2ee.association.bean;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Persistent class for entity stored in table "USERINFO"
 *
 * @author Telosys Tools Generator
 *
 */
@XmlRootElement  // JAXB annotation for REST Web Services
@XmlAccessorType(XmlAccessType.PROPERTY) // JAXB accessor = getter/setter pair
@Entity
@Table(name="USERINFO", schema="APP" )
// Define named queries here
// @NamedQueries ( {
//  @NamedQuery ( name="Userinfo.query1", query="SELECT x FROM Userinfo x WHERE  " ),
//  @NamedQuery ( name="Userinfo.query2", query="SELECT x FROM Userinfo x WHERE  " )
// } )
public class Userinfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="US_ID", nullable=false)
    private int        usId         ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="US_PSEUDO", nullable=false, length=255)
    private String     usPseudo     ;

    @Column(name="US_PASSWORD", nullable=false, length=255)
    private String     usPassword   ;

    @Column(name="US_NAME", nullable=false, length=255)
    private String     usName       ;

    @Column(name="US_FIRSTNAME", nullable=false, length=255)
    private String     usFirstname  ;

    @Column(name="US_ADRESS", nullable=false, length=255)
    private String     usAdress     ;

    @Column(name="US_POSTCODE", nullable=false)
    private int        usPostcode   ;

    @Column(name="US_TOWN", nullable=false, length=255)
    private String     usTown       ;

	// "usCoCountry" (column "US_CO_COUNTRY") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="US_CO_COUNTRY", referencedColumnName="CO_ID", insertable=false, updatable=false)
    private Country    country     ;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Userinfo()
    {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setUsId( int usId )
    {
        this.usId = usId ;
    }
    public int getUsId()
    {
        return this.usId;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : US_PSEUDO ( VARCHAR ) 
    public void setUsPseudo( String usPseudo )
    {
        this.usPseudo = usPseudo;
    }
    public String getUsPseudo()
    {
        return this.usPseudo;
    }

    //--- DATABASE MAPPING : US_PASSWORD ( VARCHAR ) 
    public void setUsPassword( String usPassword )
    {
        this.usPassword = usPassword;
    }
    public String getUsPassword()
    {
        return this.usPassword;
    }

    //--- DATABASE MAPPING : US_NAME ( VARCHAR ) 
    public void setUsName( String usName )
    {
        this.usName = usName;
    }
    public String getUsName()
    {
        return this.usName;
    }

    //--- DATABASE MAPPING : US_FIRSTNAME ( VARCHAR ) 
    public void setUsFirstname( String usFirstname )
    {
        this.usFirstname = usFirstname;
    }
    public String getUsFirstname()
    {
        return this.usFirstname;
    }

    //--- DATABASE MAPPING : US_ADRESS ( VARCHAR ) 
    public void setUsAdress( String usAdress )
    {
        this.usAdress = usAdress;
    }
    public String getUsAdress()
    {
        return this.usAdress;
    }

    //--- DATABASE MAPPING : US_POSTCODE ( INTEGER ) 
    public void setUsPostcode( int usPostcode )
    {
        this.usPostcode = usPostcode;
    }
    public int getUsPostcode()
    {
        return this.usPostcode;
    }

    //--- DATABASE MAPPING : US_TOWN ( VARCHAR ) 
    public void setUsTown( String usTown )
    {
        this.usTown = usTown;
    }
    public String getUsTown()
    {
        return this.usTown;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setCountry( Country country )
    {
        this.country = country;
    }
    public Country getCountry()
    {
        return this.country;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString()
    {
        StringBuffer sb = new StringBuffer(); 
        sb.append(usId); 
        sb.append("|"); 
        sb.append(usPseudo); 
        sb.append( "|" ); 
        sb.append(usPassword); 
        sb.append( "|" ); 
        sb.append(usName); 
        sb.append( "|" ); 
        sb.append(usFirstname); 
        sb.append( "|" ); 
        sb.append(usAdress); 
        sb.append( "|" ); 
        sb.append(usPostcode); 
        sb.append( "|" ); 
        sb.append(usTown); 
        return sb.toString(); 
    }

}
