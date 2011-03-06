package de.jvcard.types;

import de.jvcard.types.parameter.IParameter;
import de.jvcard.types.parameter.MultiValueParameter;

/**
 * Implementation of the EMAIL Type, see Section 3.3.2 in http://www.ietf.org/rfc/rfc2426.txt
 * 
 * @author Thilo Brandt
 *
 */
public class EMAIL extends Type {

	public EMAIL(String value) {
		super(IType.EMAIL, value);
	}
	
	public static IParameter createInternetParameter() {
		return new MultiValueParameter(IParameter.PARAMETER_NAME_TYPE, new String[] {"internet"});
	}
	
	public static IParameter createInternetPrefParameter() {
		return new MultiValueParameter(IParameter.PARAMETER_NAME_TYPE, new String[] {"internet", "pref"});
	}
}
