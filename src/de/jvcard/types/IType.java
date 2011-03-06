package de.jvcard.types;

import java.util.List;

import de.jvcard.types.parameter.IParameter;

/**
 * Basic interface to be implemented by all classes derived from IType.
 * 
 * @author Thilo Brandt
 *
 */
public interface IType {
	
	public static final String TYPE_VALUE_SEPARATOR = ":";
	public static final String PARAMETER_SEPARATOR = ";";
	public static final String CRLF = System.getProperty("line.separator");
	
	// elements
	/**
	 * A structured representation of the name of the person, place or thing associated with the vCard object.
	 */
	public static final String N = "N";
	/**
	 * The formatted name string associated with the vCard object
	 */
	public static final String FN = "FN"; 			
	public static final String NICKNAME = "NICKNAME"; 			
	/**
	 * Photograph 	An image or photograph of the individual associated with the vCard
	 */
	public static final String PHOTO = "PHOTO"; 	
	/**
	 * Birthday 	Date of birth of the individual associated with the vCard
	 */
	public static final String BDAY = "BDAY"; 		
	/**
	 * Delivery Address 	A structured representation of the physical delivery address for the vCard object
	 */
	public static final String ADR = "ADR"; 
	/**
	 * Label Address 	Addressing label for physical delivery to the person/object associated with the vCard
	 */
	public static final String LABEL = "LABEL";
	/**
	 * Telephone 	The canonical number string for a telephone number for telephony communication with the vCard object
	 */
	public static final String TEL = "TEL"; 	
	/**
	 * Email 	The address for electronic mail communication with the vCard object
	 */
	public static final String EMAIL = "EMAIL"; 	
	/**
	 * Email Program (Optional) 	Type of email program used
	 */
	public static final String MAILER = "MAILER"; 	
	/**
	 * Time Zone 	Information related to the standard time zone of the vCard object
	 */
	public static final String TZ = "TZ"; 
	/**
	 * Global Positioning 	The type specifies a latitude and longitude
	 */
	public static final String GEO = "GEO"; 
	/**
	 * Title 	Specifies the job title, functional position or function of the individual associated with the vCard object within an organization (V. P. Research and Development)
	 */
	public static final String TITLE = "TITLE"; 
	/**
	 * Role or occupation 	The role, occupation, or business category of the vCard object within an organization (eg. Executive)
	 */
	public static final String ROLE = "ROLE";
	/**
	 * Logo 	An image or graphic of the logo of the organization that is associated with the individual to which the vCard belongs
	 */
	public static final String LOGO = "LOGO"; 
	/**
	 * Agent 	Information about another person who will act on behalf of the vCard object. Typically this would be an area administrator, assistant, or secretary for the individual
	 */
	public static final String AGENT = "AGENT"; 
	/**
	 * Organization Name or Organizational unit 	The name and optionally the unit(s) of the organization associated with the vCard object. This type is based on the X.520 Organization Name attribute and the X.520 Organization Unit attribute
	 */
	public static final String ORG ="ORG"; 
	/**
	 * Note 	Specifies supplemental information or a comment that is associated with the vCard
	 */
	public static final String NOTE ="NOTE"; 
	/**
	 * Last Revision 	Combination of the calendar date and time of day of the last update to the vCard object
	 */
	public static final String REV = "REV"; 
	/**
	 * Sound 	By default, if this parameter is not grouped with other properties it specifies the pronunciation of the Formatted Name parameter of the vCard object.
	 */
	public static final String SOUND = "SOUND";
	/**
	 * URL 	An URL is a representation of an Internet location that can be used to obtain real-time information about the vCard object
	 */
	public static final String URL = "URL"; 
	/**
	 * Unique Identifier 	Specifies a value that represents a persistent, globally unique identifier associated with the object
	 */
	public static final String UID = "UID"; 
	/**
	 * Version 	Version of the vCard Specification
	 */
	public static final String VERSION ="VERSION"; 	
	/**
	 * 	Public Key 	The public encryption key associated with the vCard object
	 */
	public static final String KEY = "KEY";
	public static final String CATEGORIES = "CATEGORIES";
	public static final String SORTSTRING = "SORT-STRING";
	public static final String PRODID = "PRODID";
	public static final String CLASS = "CLASS";
	
	
	// predefined values
	public static final String VALUE_VERSION_21 ="2.1"; 
	public static final String VALUE_VERSION_30 ="3.0"; 
	
	/**
	 * Returns a list of all parameters assigned to an type. 
	 * The formal grammer for the parameters is defined in http://www.ietf.org/rfc/rfc2426.txt, Section 4
	 * 
	 * @return a list of all parameters ({@link IParameter}) of the element
	 */
	public List<IParameter> getParameters();
	
	/**
	 * Returns the name of the type
	 * 
	 * @return type's name
	 */
	public String getName();
	
	/**
	 * Return the assigned value to this type
	 * 
	 * @return the value
	 */
	public String getValue();
	
	/**
	 * Sets the value as string
	 * 
	 * @param value the assigned value
	 */
	public void setValue(String value);
	
	/**
	 * Add further parameters to the existing parameters list
	 * 
	 * @param parameter a valid {@link IParameter} object
	 */
	public void addParameter(IParameter parameter);
	
	/**
	 * Adds a list if parameters to the existing one.
	 * 
	 * @param list a list of {@link IParameter} objects
	 */
	public void addParameter(List<IParameter> list);
	
	/**
	 * Overwrites the existing list of parameters with the specified one.
	 * 
	 * @param list a list for overwriting the existing one
	 */
	public void setParameter(List<IParameter> list);
	
	/**
	 * Returns the vCard syntax of this type including all parameters id specified
	 * 
	 * @return the vCard syntax of this type
	 */
	public String toVCard();
	
	/**
	 * Order priority is used for sorting the types within the vCard object. This is not a mandatory usage.
	 * 
	 * @return order position within vCard object
	 */
	public int getOrderPriority();
	
	/**
	 * Returns a list with all parameter names assigned to this type
	 * 
	 * @return a list with all parameter names of this type
	 */
	public List<String> getParameterNames();
	
	/**
	 * Returns a specific parameter. If it does not exist, null is returned. 
	 * 
	 * @param name the name of the parameter to be returned
	 * @return an {@link IParameter} object or null
	 */
	public IParameter getParameter(String name);
	
}
