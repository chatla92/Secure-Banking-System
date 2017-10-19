package security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingMasking {

	public static String hashString(String message) {
		String algorithm = "SHA-256";
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(algorithm);
			byte[] arrayBytes;
			arrayBytes = digest.digest(message.getBytes("UTF-8"));
			StringBuffer stringBuffer = new StringBuffer();
			for (int i = 0; i < arrayBytes.length; i++) {
				stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			return stringBuffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	protected static boolean verifyPassword(String pwd1, String pwd2) {
		return hashString(pwd2).equals(pwd1);
	}

	public static String mask(String str) {
		char data[] = str.toCharArray();
		for (int n = 0; n < data.length; ++n) {
			data[n] = (char) ((int) data[n] + 2);
		}
		return new String(data);
	}

	protected static String unMask(String str) {
		char data[] = str.toCharArray();
		for (int n = 0; n < data.length; ++n) {
			data[n] = (char) ((int) data[n] - 2);
		}
		return new String(data);
	}
}
