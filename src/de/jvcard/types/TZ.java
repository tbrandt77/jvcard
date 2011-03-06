package de.jvcard.types;

/**
 * Implementation of the TZ Type, see Section 3.4.1 in http://www.ietf.org/rfc/rfc2426.txt
 * 
 * @author Thilo Brandt
 *
 */
public class TZ extends Type {

	public TZ(String value) {
		super(IType.TZ, value);
	}
}
