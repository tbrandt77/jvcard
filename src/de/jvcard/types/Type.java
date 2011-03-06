package de.jvcard.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.jvcard.types.parameter.IParameter;

/**
 * A generic implementation of a vCard type. It is the basis class for all inherited classes.
 * It can also be used for specifying custom types.
 * 
 * @author Thilo Brandt
 *
 */
public class Type implements IType {
	
	private String m_name;
	private List<IParameter> m_properties;
	protected String m_value;
	
	public Type(String name, String value, List<IParameter> properties) {
		super();
		this.m_name = name;
		this.m_properties = properties;
		this.m_value = value;
	}
	
	public Type(String name, String value) {
		this(name, value, new ArrayList<IParameter>());
	}
	
	public Type(String name) {
		this(name, null, new ArrayList<IParameter>());
	}
	
	@Override
	public List<IParameter> getParameters() {
		return this.m_properties;
	}

	@Override
	public String getName() {
		return this.m_name;
	}

	@Override
	public String getValue() {
		return this.m_value;
	}

	@Override
	public void setValue(String value) {
		this.m_value = value;
	}

	@Override
	public void addParameter(IParameter parameter) {
		if (this.m_properties!=null) {
			this.m_properties.add(parameter);
		}
	}

	@Override
	public String toVCard() {
		StringBuffer s = new StringBuffer();
		s.append(this.m_name);
		if (this.m_properties!=null && this.m_properties.size()>0) {
			Iterator<IParameter> i = this.m_properties.iterator();
			while (i.hasNext()) {		
				s.append(PARAMETER_SEPARATOR);
				s.append(i.next().toVCard());
			}
		}		
		s.append(TYPE_VALUE_SEPARATOR);
		s.append(this.m_value);
		s.append(CRLF);
		return s.toString();
	}

	@Override
	public int getOrderPriority() {
		return 999;
	}

	@Override
	public List<String> getParameterNames() {
		List<String> ids = new ArrayList<String>();
		for (IParameter p : this.m_properties) {
			ids.add(p.getName());
		}
		return ids;
	}

	@Override
	public IParameter getParameter(String name) {
		for (IParameter p : this.m_properties) {
			if (p.getName().equalsIgnoreCase(name)) return p;
		}
		return null;
	}

	@Override
	public void addParameter(List<IParameter> list) {
		this.m_properties.addAll(list);
	}

	@Override
	public void setParameter(List<IParameter> list) {
		this.m_properties = list;
	}

}
