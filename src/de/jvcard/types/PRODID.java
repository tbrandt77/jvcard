package de.jvcard.types;

/**
 * Implementation of the PRODID Type, see Section 3.6.3 in http://www.ietf.org/rfc/rfc2426.txt
 * 
 * @author Thilo Brandt
 *
 */
public class PRODID extends Type {

	public PRODID(String value) {
		super(IType.PRODID, value);
	}
}
