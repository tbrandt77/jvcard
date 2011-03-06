package de.jvcard.sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import de.jvcard.vCard;
import de.jvcard.types.ADR;
import de.jvcard.types.IType;
import de.jvcard.types.N;
import de.jvcard.types.vCardException;

/**
 * Sample application for parsing a vCard file (*.vcf) and retrieving 
 * single types from the content to dump them to System.out.
 * 
 * @author Thilo Brandt
 *
 */
public class ParsevCard2 {
	
	public static void main(String[] args) {
		List<vCard> vCards;
		try {
			vCards = vCard.parsevCard(new FileInputStream(args[0]));
			System.out.println();
			System.out.println("Found "+vCards.size()+" vCards in the file "+args[0]);
			System.out.println();
			for (vCard vc : vCards) {
				if (vc.hasType(IType.N)) {
					System.out.println("Type N value is set to: "+vc.getType(IType.N).getValue());
					System.out.println("Type N FamilyName is set to: "+((N)vc.getType(IType.N)).getFamilyName());
					System.out.println("Type N GivenName is set to: "+((N)vc.getType(IType.N)).getGivenName());
				}
				if (vc.hasType(IType.ADR)) {
					System.out.println("Type ADR value is set to: "+vc.getType(IType.ADR).getValue());
					System.out.println("Type ADR Street is set to: "+((ADR)vc.getType(IType.ADR)).getStreet());
					System.out.println("Type ADR City is set to: "+((ADR)vc.getType(IType.ADR)).getCity());
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (vCardException e1) {
			e1.printStackTrace();
		}
	}

}
