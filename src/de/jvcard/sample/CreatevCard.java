package de.jvcard.sample;

import java.util.Date;

import de.jvcard.vCard;
import de.jvcard.types.ADR;
import de.jvcard.types.BDAY;
import de.jvcard.types.CATEGORIES;
import de.jvcard.types.EMAIL;
import de.jvcard.types.FN;
import de.jvcard.types.GEO;
import de.jvcard.types.IType;
import de.jvcard.types.N;
import de.jvcard.types.NICKNAME;
import de.jvcard.types.ORG;
import de.jvcard.types.VERSION30;
import de.jvcard.types.parameter.MultiValueParameter;
import de.jvcard.types.parameter.SingleValueParameter;


/**
 * Sample application for creating a vCard object with Java. Shows 
 * how to use the vCard object, elements and properties.
 * 
 * @author Thilo Brandt
 *
 */
public class CreatevCard {

	public static void main(String[] args) {
		vCard vc = new vCard();
		
		vc.addType(new VERSION30());
		
		vc.addType(new N("Brandt", "Thilo"));
		
		vc.addType(new FN("Thilo Brandt"));
		
		vc.addType(new NICKNAME(new String[] {"Tiger", "Schnucki" }));
		
		vc.addType(new CATEGORIES(new String[] {"Musiker", "Software Entwickler", "Fitness-Club" }));
		
		IType e = new EMAIL("mail@thilo-brandt.de");
		e.addParameter(new MultiValueParameter("type", new String[] {"internet", "home", "pref"}));
		vc.addType(e);
		
		e = new ORG("Brandt IT Solutions");
		e.addParameter(new SingleValueParameter("CHARSET", "ISO-8859-1"));
		vc.addType(e);
		
		e = new ADR("Lindenweg 12", "Angelbachtal", "Baden-Württemberg", "74918", "Deutschland");
		vc.addType(e);

		e = new BDAY(new Date(), true);
		vc.addType(e);

		e = new GEO(44.454545f, 8.88833312f);
		vc.addType(e);
			
		System.out.println(vc.toString());
	}

}
