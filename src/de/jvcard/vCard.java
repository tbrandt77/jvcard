package de.jvcard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.jvcard.types.*;
import de.jvcard.types.parameter.IMultiValueParameter;
import de.jvcard.types.parameter.IParameter;
import de.jvcard.types.parameter.MultiValueParameter;

/**
 * This class is a vCard implementation in Java, which offers an easy to use way 
 * for handling vCards 2.1 or 3.0 in Java. The class implementation follows the 
 * specification of IETF (see http://www.ietf.org/rfc/rfc2426.txt)<br/>
 * <br/>
 * Created on 2010-06-09 <br/>
 * 
 * @author Thilo Brandt
 * 
 */
public class vCard {

	private List<IType> m_elements;
	
	/**
	 * This is a simple constructor of the class. Creates an empty {@link vCard} object.
	 * You have to add the type by calling the addType({@link IType}) method.
	 * <br/><br/>
	 * E.g. <code>vCard vc = new vCard();<br/>
	 * 		vc.addType(new VERSION30());<br/>
	 *		vc.addType(new N("Brandt", "Thilo"));<br/>
	 *		System.out.println(vc.toString());</code><br/>
	 * 
	 */
	public vCard() {
		this.m_elements = new ArrayList<IType>();
	}
	
	/**
	 * Adds a single vCard type to this vCard instance.
	 * <br/><br/>
	 * E.g. <code>new vCard().addType(new VERSION30());</code><br/>
	 * <br/>
	 * @param type	the type to be added
	 */
	public void addType(IType type) {
		this.m_elements.add(type);
	}
	
	/**
	 * Get the list with all type names assigned to this vCard instance.
	 * 
	 * @return a list with String elements representing the type names
	 */
	public List<String> getTypeNames() {
		List<String> ids = new ArrayList<String>();
		for (IType e : this.m_elements) {
			ids.add(e.getName());
		}
		return ids;
	}
	
	/**
	 * Retrieves a single type by its name. 
	 * 
	 * @param name the name of the type to be retrieved
	 * @return a valid {@link IType} object or null, if not found.
	 */
	public IType getType(String name) {
		for (IType e : this.m_elements) {
			if (e.getName().equalsIgnoreCase(name)) return e;
		}
		return null;
	}
	
	/**
	 * Checks whether a certain type specified by the name is present 
	 * in this {@link vCard} object
	 * 
	 * @param name the name of the type to be retrieved
	 * @return return true if type is present, otherwise false
	 */
	public boolean hasType(String name) {
		for (IType e : this.m_elements) {
			if (e.getName().equalsIgnoreCase(name)) return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((m_elements == null) ? 0 : m_elements.hashCode());
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
		vCard other = (vCard) obj;
		if (m_elements == null) {
			if (other.m_elements != null)
				return false;
		} else if (!m_elements.equals(other.m_elements))
			return false;
		return true;
	}

	
	/**
	 * Created a string representation of this vCard instance.
	 * 
	 * E.g.<br/>
	 * <br/><code>
	 * BEGIN:VCARD<br/>
	 * VERSION:3.0<br/>
	 * N:Brandt;Thilo;;;<br/>
	 * FN;LANGUAGE=DE:Thilo Brandt<br/>
	 * END:VCARD</code><br/><br/>
	 */
	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append(new Type("BEGIN", "VCARD").toVCard());
		
		if (this.m_elements.size()>0) {
			Collections.sort(this.m_elements, new Comparator<IType>() {
				@Override
				public int compare(IType e1, IType e2) {
					if (((IType)e1).getOrderPriority()<((IType)e2).getOrderPriority()) return -1;
					if (((IType)e2).getOrderPriority()<((IType)e1).getOrderPriority()) return 1;
					return 0;
				}
				
			});
			
			for (IType e : this.m_elements)
				s.append(e.toVCard());
			
		}
		s.append(new Type("END", "VCARD").toVCard());
		return s.toString();
	}
	
	/**
	 * Static method to parse an {@link InputStream} object e.g. from a File handle as 
	 * {@link vCard} object. Unknown types are ignored during parsing. 
	 * 
	 * @param in a buffered or non-buffered {@link InputStream} object which contains a byte representation of a vCard
	 * 
	 * @return a list with all parsed {@link vCard} objects from the {@link InputStream}
	 * @throws vCardException is throws in case of invalid buffer length or if vCard stream is too short
	 */
	public static List<vCard> parsevCard(InputStream in) throws vCardException {
		List<vCard> vCards = new ArrayList<vCard>();
		
		StringBuffer buffer = new StringBuffer();
		try {
			InputStreamReader vcfReader = new InputStreamReader(in);
			BufferedReader bufReader = new BufferedReader(vcfReader);
			while (bufReader.ready()) {
				buffer.append(bufReader.readLine());
				buffer.append(IType.CRLF);
			}
			bufReader.close();
			vcfReader.close();
		} catch (IOException ex) {
			throw new vCardException("I/O exception on inputstream: ", ex);
		}
		if (buffer.length()<64) throw new vCardException("Read vCard object is invalid. Less than 64 bytes.");
		
		String[] entries = buffer.toString().split("BEGIN:");
		vCard vcf = null;
		for (String s : entries) {
			vcf = vCard.parseSinglevCard(s);
			if (vcf!=null)
				vCards.add(vcf);
		}
				
		return vCards;
	}
	
	/**
	 * Static method to parse an {@link File} object as a {@link vCard} object. Unknown types are ignored during parsing. 
	 * 
	 * @param vcfFile a {@link File} object pointing to a vCard file
	 * 
	 * @return a list with all parsed vCard objects from the {@link InputStream}
	 * @throws vCardException is throws in case of invalid buffer length, if vCard stream is too short or if file is not found
	 */
	public static List<vCard> parsevCard(File vcfFile) throws vCardException {
		try {
			return vCard.parsevCard(new FileInputStream(vcfFile));
		} catch (FileNotFoundException e) {
			throw new vCardException(e);
		}
	}
	
	private static vCard parseSinglevCard(String vCard) {
		String[] lines = vCard.toString().split(IType.CRLF);
		vCard vcf = null;
		for (int i=0;i<lines.length;i++) {
			if (hasValidStartElement(lines[i])) {				
				StringBuffer elementBuffer = new StringBuffer();
				elementBuffer.append(lines[i]);
				int count = 1;
				while (lines.length>i+1 && !hasValidStartElement(lines[i+1])) {
					elementBuffer.append(IType.CRLF);
					elementBuffer.append(lines[i+1]);
					i++;
					count++;
				}
				if (vcf==null) vcf = new vCard();
				IType e = parseElement(elementBuffer.toString(), count>1);
				if (e!=null && !e.getName().equalsIgnoreCase("END"))
					vcf.addType(e);
			}			
		}
		return vcf;		
	}
	
	@SuppressWarnings("unchecked")
	private static IType parseElement(String element_line, boolean multiLine) {
		IType e = null;
		if (element_line.indexOf(IType.TYPE_VALUE_SEPARATOR)>0) {
			// found valid element
			String element_id = element_line.substring(0, element_line.indexOf(IType.TYPE_VALUE_SEPARATOR));
			String element_value = element_line.substring(element_line.indexOf(IType.TYPE_VALUE_SEPARATOR)+1);
			
			String[] attributes = element_id.split(IType.PARAMETER_SEPARATOR);
			if (attributes.length>1) {
				// found attributes
				List<IParameter> properties = new ArrayList<IParameter>();
				for (int i=1;i<attributes.length;i++) {
					parseAndAddProperties(properties, attributes[i]);
				}
				// check for a concrete class file
				try {
					Class<IType> elementClass = (Class<IType>) Class.forName("de.jvcard.types."+attributes[0], true, Thread.currentThread().getContextClassLoader());
					Constructor<IType> con = elementClass.getConstructor(String.class);
					e = con.newInstance(new String());
					e.setValue(element_value);
					e.setParameter(properties);
				} catch (ClassNotFoundException e1) {
					e = new Type(attributes[0], element_value);
					e.setParameter(properties);
				} catch (InstantiationException e1) {
					e = new Type(attributes[0], element_value);
					e.setParameter(properties);
				} catch (IllegalAccessException e1) {
					e = new Type(attributes[0], element_value);
					e.setParameter(properties);
				} catch (SecurityException e1) {
					e = new Type(attributes[0], element_value);
					e.setParameter(properties);
				} catch (NoSuchMethodException e1) {
					e = new Type(attributes[0], element_value);
					e.setParameter(properties);
				} catch (IllegalArgumentException e1) {
					e = new Type(attributes[0], element_value);
					e.setParameter(properties);
				} catch (InvocationTargetException e1) {
					e = new Type(attributes[0], element_value);
					e.setParameter(properties);
				}				
			} else {
				// check for a concrete class file
				try {
					Class<IType> elementClass = (Class<IType>) Class.forName("de.jvcard.types."+attributes[0], true, Thread.currentThread().getContextClassLoader());
					Constructor<IType> con = elementClass.getConstructor(String.class);
					e = con.newInstance(new String());
					e.setValue(element_value);
				} catch (ClassNotFoundException e1) {
					e = new Type(attributes[0], element_value);
				} catch (InstantiationException e1) {
					e = new Type(attributes[0], element_value);
				} catch (IllegalAccessException e1) {
					e = new Type(attributes[0], element_value);
				} catch (SecurityException e1) {
					e = new Type(attributes[0], element_value);
				} catch (NoSuchMethodException e1) {
					e = new Type(attributes[0], element_value);
				} catch (IllegalArgumentException e1) {
					e = new Type(attributes[0], element_value);
				} catch (InvocationTargetException e1) {
					e = new Type(attributes[0], element_value);
				}	
			}
		}
		return e;
	}
	
	private static void parseAndAddProperties(List<IParameter> properties, String new_property) {
		if (new_property.indexOf(IParameter.PARAMETER_EQUALATOR)>0) {
			String[] p = new_property.split(IParameter.PARAMETER_EQUALATOR);
			if (p.length==2) {
				IParameter pr = getProperty(properties, p[0]); 
				if (pr==null) {
					MultiValueParameter mvp = new MultiValueParameter(p[0]);
					mvp.setValue(p[1]);
					properties.add(mvp);
				} else {
					if (pr instanceof IMultiValueParameter) {
						((IMultiValueParameter)pr).addValue(p[1]);
					}
				}
			}
		} else {
			IParameter p= getProperty(properties, IParameter.PARAMETER_NAME_TYPE); 
			if (p==null)
				properties.add(new MultiValueParameter(IParameter.PARAMETER_NAME_TYPE, new String[] { new_property }));
			else {
				if (p instanceof IMultiValueParameter) {
					((IMultiValueParameter)p).addValue(new_property);
				}
			}
		}
	}
	
	private static IParameter getProperty(List<IParameter> properties, String id) {
		for (IParameter p : properties) {
			if (p.getName().equalsIgnoreCase(id)) return p;
		}
		return null;
	}
	
	private static boolean hasValidStartElement(String line) {
		if (line.toLowerCase().startsWith(IType.ADR.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.ADR.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.AGENT.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.AGENT.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.BDAY.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.BDAY.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.CATEGORIES.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.CATEGORIES.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.CLASS.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.CLASS.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.EMAIL.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.EMAIL.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.FN.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.FN.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.GEO.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.GEO.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.KEY.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.KEY.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.LABEL.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.LABEL.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.LOGO.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.LOGO.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.MAILER.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.MAILER.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.N.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.N.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.NICKNAME.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.NICKNAME.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.NOTE.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.NOTE.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.ORG.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.ORG.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.PHOTO.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.PHOTO.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.PRODID.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.PRODID.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.REV.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.REV.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.ROLE.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.ROLE.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.SORTSTRING.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.SORTSTRING.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.SOUND.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.SOUND.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.TEL.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.TEL.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.TITLE.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.TITLE.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.TZ.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.TZ.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.UID.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.UID.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.URL.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.URL.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith(IType.VERSION.toLowerCase() + IType.PARAMETER_SEPARATOR) || line.toLowerCase().startsWith(IType.VERSION.toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		if (line.toLowerCase().startsWith("X-".toLowerCase())) return true;
		if (line.toLowerCase().startsWith("END".toLowerCase() + IType.TYPE_VALUE_SEPARATOR)) return true;
		
		return false;
	}
	
}
