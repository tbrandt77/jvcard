package de.jvcard.types;

/**
 * Implementation of the LABEL Type, see Section 3.2.2 in http://www.ietf.org/rfc/rfc2426.txt
 * 
 * @author Thilo Brandt
 *
 */
public class LABEL extends Type {

	public LABEL(String value) {
		super(IType.LABEL, value);
	}
}
