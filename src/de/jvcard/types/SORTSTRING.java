package de.jvcard.types;

/**
 * Implementation of the SORT-STRING Type, see Section 3.6.5 in http://www.ietf.org/rfc/rfc2426.txt
 * 
 * @author Thilo Brandt
 *
 */
public class SORTSTRING extends Type {

	public SORTSTRING(String value) {
		super(IType.SORTSTRING, value);
	}

	@Override
	public int getOrderPriority() {
		return 3;
	}

}
