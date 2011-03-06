package de.jvcard.types.parameter;

/**
 * Implementation of ISingleValueParameter. Keeps string representation of the parameter value.
 * 
 * @author Thilo Brandt
 *
 */
public class SingleValueParameter implements ISingleValueParameter {

	private String m_name;
	private String m_value;
	
	public SingleValueParameter(String name, String value) {
		super();
		this.m_name = name;
		this.m_value = value;
	}
	
	public SingleValueParameter(String name) {
		super();
		this.m_name = name;
	}
	
	@Override
	public String getValue() {
		return this.m_value;
	}

	@Override
	public String getName() {
		return this.m_name;
	}

	@Override
	public void setValue(String value) {
		this.m_value = value;
	}

	@Override
	public void setName(String name) {
		this.m_name = name;
	}

	@Override
	public String toVCard() {
		return this.m_name + PARAMETER_EQUALATOR + this.m_value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m_name == null) ? 0 : m_name.hashCode());
		result = prime * result + ((m_value == null) ? 0 : m_value.hashCode());
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
		SingleValueParameter other = (SingleValueParameter) obj;
		if (m_name == null) {
			if (other.m_name != null)
				return false;
		} else if (!m_name.equals(other.m_name))
			return false;
		if (m_value == null) {
			if (other.m_value != null)
				return false;
		} else if (!m_value.equals(other.m_value))
			return false;
		return true;
	}
	
	

}
