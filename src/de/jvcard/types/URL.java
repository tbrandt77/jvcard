package de.jvcard.types;

/**
 * Implementation of the URL Type, see http://www.ietf.org/rfc/rfc2426.txt
 * 
 * @author Thilo Brandt
 *
 */
public class URL extends Type {

	public URL(String value) {
		super(IType.URL, value);
	}
}
