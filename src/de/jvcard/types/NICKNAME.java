package de.jvcard.types;

import de.jvcard.types.parameter.IParameter;

/**
 * Implementation of the NICKNAME Type, see Section 3.1.3 in http://www.ietf.org/rfc/rfc2426.txt
 * 
 * @author Thilo Brandt
 *
 */
public class NICKNAME extends Type {

	public NICKNAME(String value) {
		super(IType.NICKNAME, value);
	}
	
	public NICKNAME(String[] nicknames) {
		this("");
		StringBuffer sb = new StringBuffer();
		for (String s : nicknames) {
			sb.append(s);
			sb.append(IParameter.PARAMETER_VALUE_SEPARATOR);
		}
		this.m_value = sb.toString().substring(0, sb.length()-1);
	}
	
	public String[] getNicknames(){
		return this.m_value.split(IParameter.PARAMETER_VALUE_SEPARATOR);
	}
	
	@Override
	public int getOrderPriority() {
		return 2;
	}
}
