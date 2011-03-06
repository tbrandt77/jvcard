package de.jvcard.types;

import de.jvcard.types.parameter.IParameter;
import de.jvcard.types.parameter.MultiValueParameter;


/**
 * Implementation of the ADR Type, see Section 3.2.1 in http://www.ietf.org/rfc/rfc2426.txt
 * 
 * @author Thilo Brandt
 *
 */
public class ADR extends Type {

	public ADR(String value) {
		super(IType.ADR, value);
	}
	
	public ADR(String pob, String ext_adr, String street, String city, String region, String postalcode, String country) {
		this((pob==null ? "" : pob) + PARAMETER_SEPARATOR +	
			(ext_adr==null ? "" : ext_adr) + PARAMETER_SEPARATOR +	
			(street==null ? "" : street) + PARAMETER_SEPARATOR +	
			(city==null ? "" : city) + PARAMETER_SEPARATOR +	
			(region==null ? "" : region) + PARAMETER_SEPARATOR +	
			(postalcode==null ? "" : postalcode) + PARAMETER_SEPARATOR +	
			(country==null ? "" : country)				
		);
	}
	
	public ADR(String street, String city, String region, String postalcode, String country) {
		this(PARAMETER_SEPARATOR +	
			PARAMETER_SEPARATOR +	
			(street==null ? "" : street) + PARAMETER_SEPARATOR +	
			(city==null ? "" : city) + PARAMETER_SEPARATOR +	
			(region==null ? "" : region) + PARAMETER_SEPARATOR +	
			(postalcode==null ? "" : postalcode) + PARAMETER_SEPARATOR +	
			(country==null ? "" : country)				
		);
	}
	
	public String getPostOfficeBox(){
		String[] term = this.m_value.split(PARAMETER_SEPARATOR);
		if (term!=null && term.length>=1) {
			return term[0];
		}
		return null;
	}
	
	public String getExtendedAddress(){
		String[] term = this.m_value.split(PARAMETER_SEPARATOR);
		if (term!=null && term.length>=2) {
			return term[1];
		}
		return null;
	}
	
	public String getStreet(){
		String[] term = this.m_value.split(PARAMETER_SEPARATOR);
		if (term!=null && term.length>=3) {
			return term[2];
		}
		return null;
	}
	
	public String getCity(){
		String[] term = this.m_value.split(PARAMETER_SEPARATOR);
		if (term!=null && term.length>=4) {
			return term[3];
		}
		return null;
	}
	
	public String getRegion(){
		String[] term = this.m_value.split(PARAMETER_SEPARATOR);
		if (term!=null && term.length>=5) {
			return term[4];
		}
		return null;
	}
	
	public String getPostalCode(){
		String[] term = this.m_value.split(PARAMETER_SEPARATOR);
		if (term!=null && term.length>=6) {
			return term[5];
		}
		return null;
	}
	
	public String getCountry(){
		String[] term = this.m_value.split(PARAMETER_SEPARATOR);
		if (term!=null && term.length==7) {
			return term[6];
		}
		return null;
	}
	@Override
	public int getOrderPriority() {
		return 4;
	}
	
	public static IParameter createHomeParameter() {
		return new MultiValueParameter(IParameter.PARAMETER_NAME_TYPE, new String[] {"home"});
	}
	
	public static IParameter createHomePrefParameter() {
		return new MultiValueParameter(IParameter.PARAMETER_NAME_TYPE, new String[] {"home", "pref"});
	}

	public static IParameter createWorkParameter() {
		return new MultiValueParameter(IParameter.PARAMETER_NAME_TYPE, new String[] {"work"});
	}
	
	public static IParameter createWorkPrefParameter() {
		return new MultiValueParameter(IParameter.PARAMETER_NAME_TYPE, new String[] {"work", "pref"});
	}
	
	public static IParameter createPostalParameter() {
		return new MultiValueParameter(IParameter.PARAMETER_NAME_TYPE, new String[] {"postal"});
	}
	
	public static IParameter createParcelParameter() {
		return new MultiValueParameter(IParameter.PARAMETER_NAME_TYPE, new String[] {"parcel"});
	}
	
	public static IParameter createIntlParameter() {
		return new MultiValueParameter(IParameter.PARAMETER_NAME_TYPE, new String[] {"intl"});
	}
	
	public static IParameter createDomParameter() {
		return new MultiValueParameter(IParameter.PARAMETER_NAME_TYPE, new String[] {"dom"});
	}
}
