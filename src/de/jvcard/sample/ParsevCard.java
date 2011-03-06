package de.jvcard.sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import de.jvcard.vCard;
import de.jvcard.types.vCardException;

/**
 * Sample application for parsing a vCard file (*.vcf) and dumping the content to System.out.
 * 
 * @author Thilo Brandt
 *
 */
public class ParsevCard {
	
	public static void main(String[] args) {
		List<vCard> vCards;
		try {
			vCards = vCard.parsevCard(new FileInputStream(args[0]));
			System.out.println(vCards);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (vCardException e1) {
			e1.printStackTrace();
		}
	}

}
