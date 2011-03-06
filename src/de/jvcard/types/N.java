package de.jvcard.types;

/**
 * Implementation of the N Type, see Section 3.1.2 in http://www.ietf.org/rfc/rfc2426.txt
 * 
 * @author Thilo Brandt
 *
 */
public class N extends Type {

	public N(String value) {
		super(IType.N, value);
	}
	
	public N(String family, String given, String middle, String prefix) {
		this((family==null ? "" : family) + PARAMETER_SEPARATOR + 
			(given==null ? "" : given) + PARAMETER_SEPARATOR + 
			(middle==null ? "" : middle) + PARAMETER_SEPARATOR + 
			(prefix==null ? "" : prefix));
	}
	
	public N(String family, String given) {
		this((family==null ? "" : family) + PARAMETER_SEPARATOR + 
			(given==null ? "" : given) + PARAMETER_SEPARATOR + 
			PARAMETER_SEPARATOR  
			);
	}
	
	@Override
	public int getOrderPriority() {
		return 2;
	}

	public String getFamilyName(){
		String[] term = this.m_value.split(PARAMETER_SEPARATOR);
		if (term!=null && term.length>=1) {
			return term[0];
		}
		return null;
	}

	public String getGivenName(){
		String[] term = this.m_value.split(PARAMETER_SEPARATOR);
		if (term!=null && term.length>=2) {
			return term[1];
		}
		return null;
	}
	
	public String getMiddleName(){
		String[] term = this.m_value.split(PARAMETER_SEPARATOR);
		if (term!=null && term.length>=3) {
			return term[2];
		}
		return null;
	}
	
	public String getPrefix(){
		String[] term = this.m_value.split(PARAMETER_SEPARATOR);
		if (term!=null && term.length==4) {
			return term[3];
		}
		return null;
	}
}
