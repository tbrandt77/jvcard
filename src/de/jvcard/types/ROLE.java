package de.jvcard.types;

/**
 * Implementation of the ROLE Type, see Section 3.5.2 in http://www.ietf.org/rfc/rfc2426.txt
 * 
 * @author Thilo Brandt
 *
 */
public class ROLE extends Type {

	public ROLE(String value) {
		super(IType.ROLE, value);
	}
}
