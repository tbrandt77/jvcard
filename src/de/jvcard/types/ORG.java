package de.jvcard.types;

/**
 * Implementation of the ORG Type, see Section 3.5.5 in http://www.ietf.org/rfc/rfc2426.txt
 * 
 * @author Thilo Brandt
 *
 */
public class ORG extends Type {

	public ORG(String value) {
		super(IType.ORG, value);
	}
}
