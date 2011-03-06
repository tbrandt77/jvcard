package de.jvcard.types;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.jvcard.types.parameter.IParameter;
import de.jvcard.types.parameter.SingleValueParameter;

/**
 * Implementation of the BDAY Type, see Section 3.1.5 in http://www.ietf.org/rfc/rfc2426.txt
 * 
 * @author Thilo Brandt
 *
 */
public class BDAY extends Type {

	public BDAY(String value) {
		super(IType.BDAY, value);
	}
	
	public BDAY(Date bday) {
		this(bday, false);
	}
	
	public BDAY(Date bday, boolean isDateTime) {
		this("");
		SimpleDateFormat sdf = new SimpleDateFormat((isDateTime ? "yyyy-MM-dd'T'HH:mm:ss'Z'": "yyyy-MM-dd"));
		this.m_value = sdf.format(bday);
		if (isDateTime) {
			this.addParameter(new SingleValueParameter(IParameter.PARAMETER_NAME_VALUE, "date-time"));
		} else {
			this.addParameter(new SingleValueParameter(IParameter.PARAMETER_NAME_VALUE, "dat"));
		}
	}
	
	public Date getBirthDay() throws vCardException {
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
