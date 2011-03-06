package de.jvcard.types.parameter;

/**
 * Interface for multi value parameter. Keeps the values in a comma separated list.
 * 
 * @author Thilo Brandt
 *
 */
public interface IMultiValueParameter extends ISingleValueParameter {

	public String[] getValues();
	
	public void setValues(String[] values);
	
	public void addValue(String value);
	
}
