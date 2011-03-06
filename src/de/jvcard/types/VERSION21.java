package de.jvcard.types;

/**
 * Implementation of the VERSION Type, with a predefined value for vCard 2.1
 * 
 * @author Thilo Brandt
 *
 */
public class VERSION21 extends Type {

	public VERSION21() {
		super(IType.VERSION, VALUE_VERSION_21);
	}

	public VERSION21(String value) {
		super(IType.VERSION, VALUE_VERSION_21);
	}
	
	@Override
	public int getOrderPriority() {
		return 1;
	}

}
