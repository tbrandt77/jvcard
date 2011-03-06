package de.jvcard.types;

/**
 * Implementation of the CLASS Type. This type is not specified in http://www.ietf.org/rfc/rfc2426.txt, but used very often in applications.
 * 
 * @author Thilo Brandt
 *
 */
public class CLASS extends Type {

	public CLASS() {
		super(IType.CLASS, "PUBLIC");
	}
	
	public CLASS(String value) {
		super(IType.CLASS, value);
	}


	@Override
	public int getOrderPriority() {
		return 1;
	}
	
	public static CLASS getPublicClass() {
		return new CLASS();
	}
	
	public static CLASS getPrivateClass() {
		return new CLASS("PRIVATE");
	}
	
	public static CLASS getConfidentialClass() {
		return new CLASS("CONFIDENTIAL");
	}

}
