package de.jvcard.types;

/**
 * Implementation of the GEO Type, see Section 3.4.2 in http://www.ietf.org/rfc/rfc2426.txt
 * 
 * @author Thilo Brandt
 *
 */
public class GEO extends Type {

	public GEO(String value) {
		super(IType.GEO, value);
	}
	
	public GEO(float longitude, float latitude) {
		this("");
		this.m_value = Float.toString(latitude) + IType.PARAMETER_SEPARATOR + Float.toString(longitude);
	}
	
	public float getLatitude() {
		String[] values = this.m_value.split(IType.PARAMETER_SEPARATOR);
		if (values.length==2) {
			return Float.parseFloat(values[0]);
		}
		return 0.0f;
	}
	public float getLongitude() {
		String[] values = this.m_value.split(IType.PARAMETER_SEPARATOR);
		if (values.length==2) {
			return Float.parseFloat(values[1]);
		}
		return 0.0f;
	}
}
