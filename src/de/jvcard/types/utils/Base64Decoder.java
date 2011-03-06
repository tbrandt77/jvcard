package de.jvcard.types.utils;

import java.io.*;

/**
 *  This class provides utilities for base64 deconding.
 * 
 *@author     Thilo Brandt
 *@created    2004/08/04
 */
public class Base64Decoder extends FilterInputStream {

  private final static char[] chars = {
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
    'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
    'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
    'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
    'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
    'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
    '8', '9', '+', '/'
    };

  // A mapping between char values and six-bit integers
  private final static int[] ints = new int[128];
  static {
    for (int i = 0; i < 64; i++) {
      ints[chars[i]] = i;
    }
  }

  private int charCount;
  private int carryOver;

  /**
   * Constructs a new Base64 decoder that reads input from the given
   * InputStream.
   *
   * @param in the input stream
   */
  public Base64Decoder(InputStream in) {
    super(in);
  }

  /**
   * Returns the next decoded character from the stream, or -1 if end of stream
   * was reached.
   *
   * @return the decoded character, or -1 if the end of the input stream is
   *      reached
   * @exception IOException if an I/O error occurs
   */
  public int read()
    throws IOException {
    // Read the next non-whitespace character
    int x;
    do {
      x = in.read();
      if (x == -1) {
        return -1;
      }
    } while (Character.isWhitespace((char)x));
    charCount++;

    // The '=' sign is just padding
    if (x == '=') {
      return -1;// effective end of stream
    }

    // Convert from raw form to 6-bit form
    x = ints[x];

    // Calculate which character we're decoding now
    int mode = (charCount - 1) % 4;

    // First char save all six bits, go for another
    if (mode == 0) {
      carryOver = x & 63;
      return read();
    }
    // Second char use previous six bits and first two new bits,
    // save last four bits
    else if (mode == 1) {
      int decoded = ((carryOver << 2) + (x >> 4)) & 255;
      carryOver = x & 15;
      return decoded;
    }
    // Third char use previous four bits and first four new bits,
    // save last two bits
    else if (mode == 2) {
      int decoded = ((carryOver << 4) + (x >> 2)) & 255;
      carryOver = x & 3;
      return decoded;
    }
    // Fourth char use previous two bits and all six new bits
    else if (mode == 3) {
      int decoded = ((carryOver << 6) + x) & 255;
      return decoded;
    }
    return -1;// can't actually reach this line
  }

  /**
   * Reads decoded data into an array of bytes and returns the actual number of
   * bytes read, or -1 if end of stream was reached.
   *
   * @param off the start offset of the data
   * @param len the maximum number of bytes to read
   * @param b TBD: Description of the incoming method parameter
   * @return the actual number of bytes read, or -1 if the end of the input
   *      stream is reached
   * @exception IOException if an I/O error occurs
   */
  public int read(byte[] b, int off, int len)
    throws IOException {
    // This could of course be optimized
    int i;
    for (i = 0; i < len; i++) {
      int x = read();
      if (x == -1 && i == 0) {// an immediate -1 returns -1
        return -1;
      }
      else if (x == -1) {// a later -1 returns the chars read so far
        break;
      }
      b[off + i] = (byte)x;
    }
    return i;
  }

  /**
   * Returns the decoded form of the given encoded string.
   *
   * @param encoded the string to decode
   * @return the decoded form of the encoded string
   */
  public static String decode(String encoded) {
    byte[] bytes = null;
    try {
      bytes = encoded.getBytes("8859_1");
    }
    catch (UnsupportedEncodingException ex) {
    }

    Base64Decoder in = new Base64Decoder(
      new ByteArrayInputStream(bytes));

    ByteArrayOutputStream out =
      new ByteArrayOutputStream((int)(bytes.length * 0.67));

    try {
      byte[] buf = new byte[4 * 1024];// 4K buffer
      int bytesRead;
      while ((bytesRead = in.read(buf)) != -1) {
        out.write(buf, 0, bytesRead);
      }
      out.close();

      return out.toString("8859_1");
    }
    catch (IOException ignored) {
      return null;
    }
  }

  /*
   * public static void main(String[] args) throws Exception {
   * if (args.length != 1) {
   * System.err.println("Usage: java Base64Decoder fileToDecode");
   * }
   * Base64Decoder decoder = null;
   * try {
   * decoder = new Base64Decoder(
   * new BufferedInputStream(
   * new FileInputStream(args[0])));
   * byte[] buf = new byte[4 * 1024];  // 4K buffer
   * int bytesRead;
   * while ((bytesRead = decoder.read(buf)) != -1) {
   * System.out.write(buf, 0, bytesRead);
   * }
   * }
   * finally {
   * if (decoder != null) decoder.close();
   * }
   * }
   */
}

