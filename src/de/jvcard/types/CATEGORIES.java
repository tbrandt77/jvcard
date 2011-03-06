package de.jvcard.types;

import de.jvcard.types.parameter.IParameter;

/**
 * Implementation of the CATEGORIES Type, see Section 3.6.1 in http://www.ietf.org/rfc/rfc2426.txt
 * 
 * @author Thilo Brandt
 *
 */
public class CATEGORIES extends Type {

	public CATEGORIES(String value) {
		super(IType.CATEGORIES, value);
	}
	
	public CATEGORIES(String[] categories) {
		this("");
		StringBuffer sb = new StringBuffer();
		for (String s : categories) {
			sb.append(s);
			sb.append(IParameter.PARAMETER_VALUE_SEPARATOR);
		}
		this.m_value = sb.toString().substring(0, sb.length()-1);
	}
	
	public String[] getCategories(){
		return this.m_value.split(IParameter.PARAMETER_VALUE_SEPARATOR);
	}
}
