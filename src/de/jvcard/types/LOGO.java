package de.jvcard.types;

import de.jvcard.types.parameter.IParameter;
import de.jvcard.types.parameter.SingleValueParameter;

/**
 * Implementation of the LOGO Type, see Section 3.5.3 in http://www.ietf.org/rfc/rfc2426.txt
 * 
 * @author Thilo Brandt
 *
 */
public class LOGO extends PHOTO {

	public LOGO(String b64value, String type) {
		super(IType.LOGO, b64value);
		this.addParameter(new SingleValueParameter(IParameter.PARAMETER_NAME_ENCODING, "b"));
		this.addParameter(new SingleValueParameter(IParameter.PARAMETER_NAME_TYPE, type));
	}
	
	public LOGO(String uri) {
		super(IType.LOGO, uri);
		this.addParameter(new SingleValueParameter(IParameter.PARAMETER_NAME_VALUE, "uri"));
	}
}
