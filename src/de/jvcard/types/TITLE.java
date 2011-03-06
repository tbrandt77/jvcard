package de.jvcard.types;

/**
 * Implementation of the TITLE Type, see Section 3.5.1 in http://www.ietf.org/rfc/rfc2426.txt
 * 
 * @author Thilo Brandt
 *
 */
public class TITLE extends Type {

	public TITLE(String value) {
		super(IType.TITLE, value);
	}
}
