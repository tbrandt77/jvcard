package de.jvcard.types;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import de.jvcard.types.parameter.IParameter;
import de.jvcard.types.parameter.SingleValueParameter;
import de.jvcard.types.utils.Base64Decoder;
import de.jvcard.types.utils.Base64Encoder;

/**
 * Implementation of the PHOTO Type, see Section 3.1.4 in http://www.ietf.org/rfc/rfc2426.txt
 * 
 * @author Thilo Brandt
 *
 */
public class PHOTO extends Type {

	public PHOTO(String b64value, String type) {
		super(IType.PHOTO, b64value);
		this.addParameter(new SingleValueParameter(IParameter.PARAMETER_NAME_ENCODING, "b"));
		this.addParameter(new SingleValueParameter(IParameter.PARAMETER_NAME_TYPE, type));
	}
	
	public PHOTO(String uri) {
		super(IType.PHOTO, uri);
		this.addParameter(new SingleValueParameter(IParameter.PARAMETER_NAME_VALUE, "uri"));
	}
	
	public String getURI() {
		if (this.getParameterNames().contains(IParameter.PARAMETER_NAME_VALUE)) {
			return this.m_value;
		}
		return null;
	}
	
	public String getBase64EncodedValue() {
		if (this.getParameterNames().contains(IParameter.PARAMETER_NAME_ENCODING) && this.getParameterNames().contains(IParameter.PARAMETER_NAME_TYPE)) {
			return this.m_value;
		}
		return null;
	}
	
	public static InputStream decodeBase64(String b64value) throws IOException {
		ByteArrayInputStream encodedIn = new ByteArrayInputStream(b64value.getBytes());
		Base64Decoder b64 = new Base64Decoder(encodedIn);
		ByteArrayOutputStream decodedOut = new ByteArrayOutputStream();
		de.jvcard.types.PHOTO.copy(b64, decodedOut);
		b64.close();
		decodedOut.close();
		
		return new ByteArrayInputStream(decodedOut.toByteArray());
	}
	
	public static String encodeBase64(InputStream in) throws IOException {
		StringBuffer sb = new StringBuffer();
		ByteArrayOutputStream encodedOut = new ByteArrayOutputStream();
		// finalie lines with '\n ' instead of '\n'
		Base64Encoder b64 = new Base64Encoder(encodedOut, " ".getBytes());
		de.jvcard.types.PHOTO.copy(in, b64);						
		b64.flush();
		b64.close();
		String imagedata = new String(encodedOut.toByteArray());
		int size = imagedata.length();
		sb.append(imagedata.substring(0,43));
		int z = 43;
		while (z<imagedata.length()) {
			sb.append(imagedata.substring(z,Math.min((z+69), size)));
			z +=69;
		}
		return sb.toString();
	}
	
	private synchronized static void copy(InputStream in, OutputStream out, boolean finalize, int bufSize) throws IOException {
		byte[] buffer = new byte[bufSize];
		int bytesRead;
		while ((bytesRead = in.read(buffer)) != -1) {
			out.write(buffer, 0, bytesRead);
		}  
		out.flush();
		if (finalize) {
			in.close();
			out.close();
		}
	}
	
	private static void copy(InputStream in, OutputStream out) throws IOException {
		de.jvcard.types.PHOTO.copy(in, out, false, Short.MAX_VALUE);
	}

}
