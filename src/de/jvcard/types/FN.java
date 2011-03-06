package de.jvcard.types;

/**
 * Implementation of the FN Type, see Section 3.1.1 in http://www.ietf.org/rfc/rfc2426.txt
 * 
 * @author Thilo Brandt
 *
 */
public class FN extends Type {

	public FN(String value) {
		super(IType.FN, value);
	}

	@Override
	public int getOrderPriority() {
		return 2;
	}

}
