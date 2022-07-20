package tr.com.innova.internship.userservice.helper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256HashManager {

	private MessageDigest digest;

	public Sha256HashManager() {
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(this.getClass().getSimpleName() + " NoSuchAlgorithmException");
		}
	}

	public String hash(String original) {
		byte[] encodedhash = digest.digest(original.getBytes(StandardCharsets.UTF_8));
		return bytesToHex(encodedhash);
	}

	private String bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1) hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
