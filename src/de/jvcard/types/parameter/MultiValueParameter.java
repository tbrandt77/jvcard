package de.jvcard.types.parameter;

import java.util.Arrays;

/**
 * Implementation of IMultiValueParameter. Keeps a comma separated list of parameter values.
 * 
 * @author Thilo Brandt
 *
 */
public class MultiValueParameter implements IMultiValueParameter {

	private String m_name;
	private String[] m_values;

	public MultiValueParameter(String name, String[] values) {
		super();
		this.m_name = name;
		this.m_values = values;
	}
	
	public MultiValueParameter(String name, String value) {
		super();
		this.m_name = name;
		this.m_values = new String[1];
		this.m_values[0] = value;
	}
	
	public MultiValueParameter(String name) {
		super();
		this.m_name = name;
	}
	
	@Override
	public String[] getValues() {
		return this.m_values;
	}

	@Override
	public String getName() {
		return this.m_name;
	}

	@Override
	public void addValue(String value) {
		String[] values = new String[this.m_values==null ? 1 : this.m_values.length+1];
		values[0] = value;
		if (this.m_values!=null) {
			for (int i=0;i<this.m_values.length;i++) {
				values[i+1] = this.m_values[i];
			}
		}
		this.m_values = values;
	}

	@Override
	public void setValues(String[] values) {
		this.m_values = values;
	}


	@Override
	public String getValue() {
		StringBuffer s = new StringBuffer();
		for (String value: this.m_values) {
			s.append(value);
			s.append(PARAMETER_VALUE_SEPARATOR);
		}
		return s.toString().substring(0, s.length()-PARAMETER_VALUE_SEPARATOR.length());
	}
	
	@Override
	public void setName(String name) {
		this.m_name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m_name == null) ? 0 : m_name.hashCode());
		result = prime * result + Arrays.hashCode(m_values);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MultiValueParameter other = (MultiValueParameter) obj;
		if (m_name == null) {
			if (other.m_name != null)
				return false;
		} else if (!m_name.equals(other.m_name))
			return false;
		if (!Arrays.equals(m_values, other.m_values))
			return false;
		return true;
	}

	@Override
	public String toVCard() {
		StringBuffer s = new StringBuffer();
		s.append(this.m_name);
		s.append(PARAMETER_EQUALATOR);
		for (String value: this.m_values) {
			s.append(value);
			s.append(PARAMETER_VALUE_SEPARATOR);
		}
		return s.toString().substring(0, s.length()-PARAMETER_VALUE_SEPARATOR.length());
	}

	public void setValue(String value) {
		this.m_values = value.split(PARAMETER_VALUE_SEPARATOR);
	}

}
