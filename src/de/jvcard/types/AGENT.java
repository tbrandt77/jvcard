package de.jvcard.types;

/**
 * Implementation of the AGENT Type, see Section 3.5.4 in http://www.ietf.org/rfc/rfc2426.txt
 * 
 * @author Thilo Brandt
 *
 */
public class AGENT extends Type {

	public AGENT(String value) {
		super(IType.AGENT, value);
	}
}
