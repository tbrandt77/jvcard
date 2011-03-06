package de.jvcard.types;

/**
 * Implementation of the MAILER Type, see Section 3.3.3 in http://www.ietf.org/rfc/rfc2426.txt
 * 
 * @author Thilo Brandt
 *
 */
public class MAILER extends Type {

	public MAILER(String value) {
		super(IType.MAILER, value);
	}
}
