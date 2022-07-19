package tr.com.innova.internship.encryiption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;

public class EncryptionManager {

	private static final Logger log = LoggerFactory.getLogger(EncryptionManager.class);
	private IvParameterSpec ivParameterSpec;
	private SecretKeySpec secretKeySpec;

	private EncryptionManager(String secret1, String secret2) {
		init(secret1, secret2);
	}

	public static Builder newBuider() {
		return new Builder();
	}

	public String encrypt(String toBeEncrypt) {
		try {
			return encryptInternal(toBeEncrypt);

		} catch (InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException | NoSuchAlgorithmException e) {
			log.error(e.getMessage(), e);
			throw new EncryptionException("encrypt failed");
		}
	}

	public byte[] decrypt(String encrypted) {
		try {
			return decryptInternal(encrypted);
		} catch (InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException | NoSuchAlgorithmException e) {
			log.error(e.getMessage(), e);
			throw new EncryptionException("decrypt failed");
		}
	}

	private void init(String secret1, String secret2) {
		ivParameterSpec = new IvParameterSpec(hexStringToByteArray(secret1));
		secretKeySpec = new SecretKeySpec(hexStringToByteArray(secret2), "AES");
	}

	private String encryptInternal(String toBeEncrypt) throws InvalidAlgorithmParameterException,
			InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
		byte[] encrypted = cipher.doFinal(toBeEncrypt.getBytes(StandardCharsets.UTF_8));
		return Base64.getUrlEncoder().encodeToString(encrypted);
	}

	private String urlEncode(String url) throws UnsupportedEncodingException {
		return URLEncoder.encode(url, "UTF-8");
	}

	private byte[] decryptInternal(String encrypted) throws InvalidAlgorithmParameterException, InvalidKeyException,
			BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
		return cipher.doFinal(Base64.getUrlDecoder().decode(encrypted));
	}

	private byte[] hexStringToByteArray(String hex) {
		int l = hex.length();
		byte[] data = new byte[l / 2];
		for (int i = 0; i < l; i += 2) {
			data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
					+ Character.digit(hex.charAt(i + 1), 16));
		}
		return data;
	}

	public static Optional<Character> findChar(ByteBuffer array, int index) {
		Objects.requireNonNull(array);
		if (index < 0) throw new IllegalArgumentException("index must positive");

		final CharBuffer charBuffer = StandardCharsets.UTF_8.decode(array.asReadOnlyBuffer());
		if (charBuffer.length() > index) {
			return Optional.of(charBuffer.charAt(index));
		}

		return Optional.empty();
	}

	public static int stringLength(byte[] array) {
		Objects.requireNonNull(array);
		int minus = 0;
		for (int i = 0; i < array.length; i++) {
			if (!Character.isAlphabetic(array[i])) {
				minus++;
				i++;
			}
		}
		return array.length - minus;
	}

	public static class Builder {
		private String secret1;
		private String secret2;

		public Builder withIvyScreet(String value) {
			this.secret1 = value;
			return this;
		}

		public Builder withScretKey(String value) {
			this.secret2 = value;
			return this;
		}

		public EncryptionManager build() throws NoSuchPaddingException, NoSuchAlgorithmException {
			return new EncryptionManager(secret1, secret2);
		}

	}

}
