package de.jvcard.types;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.jvcard.types.parameter.IParameter;
import de.jvcard.types.parameter.SingleValueParameter;


/**
 * Implementation of the REV Type, see Section 3.6.4 in http://www.ietf.org/rfc/rfc2426.txt
 * 
 * @author Thilo Brandt
 *
 */
public class REV extends Type {

	public REV(String value) {
		super(IType.REV, value);
	}
	
	public REV(Date rev) {
		this(rev, false);
	}
	
	public REV(Date rev, boolean isDateTime) {
		this("");
		SimpleDateFormat sdf = new SimpleDateFormat((isDateTime ? "yyyy-MM-dd'T'HH:mm:ss'Z'": "yyyy-MM-dd"));
		this.m_value = sdf.format(rev);
		if (isDateTime) {
			this.addParameter(new SingleValueParameter(IParameter.PARAMETER_NAME_VALUE, "date-time"));
		} else {
			this.addParameter(new SingleValueParameter(IParameter.PARAMETER_NAME_VALUE, "dat"));
		}
	}
	
	public Date getRevisionDay() throws vCardException {
		DateFormat formatter = null;
		if (this.getParameterNames().contains(IParameter.PARAMETER_NAME_VALUE)) {
			if (this.getParameter(IParameter.PARAMETER_NAME_VALUE).getValue().equalsIgnoreCase("date-time")) {
				formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); 
			} else {
				formatter = new SimpleDateFormat("yyyy-MM-dd"); 
			}
		} else {
			formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		}
		try {
			return formatter.parse(this.m_value);
		} catch (ParseException e) {
			throw new vCardException(e);
		}		
	}

}
