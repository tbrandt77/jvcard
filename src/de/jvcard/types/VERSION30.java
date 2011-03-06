package de.jvcard.types;

/**
 * Implementation of the VERSION Type, with a predefined value for vCard 3.0
 * 
 * @author Thilo Brandt
 *
 */
public class VERSION30 extends Type {

	public VERSION30() {
		super(IType.VERSION, VALUE_VERSION_30);
	}

	public VERSION30(String value) {
		super(IType.VERSION, VALUE_VERSION_30);
	}

	@Override
	public int getOrderPriority() {
		return 1;
	}

}
