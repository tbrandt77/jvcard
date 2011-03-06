package de.jvcard.types;

/**
 * Implementation of the NOTE Type, see Section 3.6.2 in http://www.ietf.org/rfc/rfc2426.txt
 * 
 * @author Thilo Brandt
 *
 */
public class NOTE extends Type {

	public NOTE(String value) {
		super(IType.NOTE, value);
	}
}
