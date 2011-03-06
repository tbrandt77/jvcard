package de.jvcard.types.parameter;

/**
 * Base interface to be implemented by all parameter implementations.
 * 
 * @author Thilo Brandt
 *
 */
public interface IParameter {
	
	public static final String PARAMETER_VALUE_SEPARATOR = ",";
	public static final String PARAMETER_EQUALATOR = "=";
	
	public static final String PARAMETER_NAME_TYPE = "TYPE";
	public static final String PARAMETER_NAME_VALUE = "VALUE";
	public static final String PARAMETER_NAME_LANGUAGE = "LANGUAGE";
	public static final String PARAMETER_NAME_ENCODING = "ENCODING";
	public static final String PARAMETER_NAME_CHARSET = "CHARSET";
	public static final String PARAMETER_NAME_CONTEXT = "CONTEXT";
	
	public String getName();
	
	public String getValue();
	
	public void setName(String name);
	
	public String toVCard();
}
