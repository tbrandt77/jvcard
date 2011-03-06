package de.jvcard.types;

import de.jvcard.types.parameter.IParameter;
import de.jvcard.types.parameter.MultiValueParameter;

/**
 * Implementation of the TEL Type, see Section 3.3.1 in http://www.ietf.org/rfc/rfc2426.txt
 * 
 * @author Thilo Brandt
 *
 */
public class TEL extends Type {

	public TEL(String value) {
		super(IType.TEL, value);
	}
	
	public static IParameter createHomeType() {
		return new MultiValueParameter(IParameter.PARAMETER_NAME_TYPE, new String[] {"home"});
	}
	
	public static IParameter createHomeVoiceType() {
		return new MultiValueParameter(IParameter.PARAMETER_NAME_TYPE, new String[] {"home", "voice"});
	}
	
	public static IParameter createHomeFaxType() {
		return new MultiValueParameter(IParameter.PARAMETER_NAME_TYPE, new String[] {"home", "fax"});
	}

	public static IParameter createHomeCellType() {
		return new MultiValueParameter(IParameter.PARAMETER_NAME_TYPE, new String[] {"home", "cell"});
	}

	public static IParameter createHomeIsdnType() {
		return new MultiValueParameter(IParameter.PARAMETER_NAME_TYPE, new String[] {"home", "isdn"});
	}
	
	public static IParameter createHomeCarType() {
		return new MultiValueParameter(IParameter.PARAMETER_NAME_TYPE, new String[] {"home", "car"});
	}
	
	public static IParameter createWorkType() {
		return new MultiValueParameter(IParameter.PARAMETER_NAME_TYPE, new String[] {"work"});
	}
	
	public static IParameter createWorkVoiceType() {
		return new MultiValueParameter(IParameter.PARAMETER_NAME_TYPE, new String[] {"work", "voice"});
	}
	
	public static IParameter createWorkFaxType() {
		return new MultiValueParameter(IParameter.PARAMETER_NAME_TYPE, new String[] {"work", "fax"});
	}

	public static IParameter createWorkCellType() {
		return new MultiValueParameter(IParameter.PARAMETER_NAME_TYPE, new String[] {"work", "cell"});
	}

	public static IParameter createWorkIsdnType() {
		return new MultiValueParameter(IParameter.PARAMETER_NAME_TYPE, new String[] {"work", "isdn"});
	}
	
	public static IParameter createWorkCarType() {
		return new MultiValueParameter(IParameter.PARAMETER_NAME_TYPE, new String[] {"work", "car"});
	}
	
	@Override
	public int getOrderPriority() {
		return 5;
	}

}
