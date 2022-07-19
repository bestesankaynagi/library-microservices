package tr.com.innova.internship.encryiption;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.stream.Collectors;

public class SpringResourceAwareRSAKeyProvider implements AsymmetricEncryptionKeyProvider<RSAPrivateKey, RSAPublicKey>, InitializingBean {

	private final Resource publicKeyResourceUri;
	private final Resource privateKeyResourceUri;

	private RSAPublicKey rsaPublicKey;
	private RSAPrivateKey rsaPrivateKey;

	public SpringResourceAwareRSAKeyProvider(Resource publicKeyResourceUri, Resource privateKeyResourceUri) {
		this.publicKeyResourceUri = publicKeyResourceUri;
		this.privateKeyResourceUri = privateKeyResourceUri;
	}


	@Override
	public RSAPrivateKey getPrivateKey() {
		return this.rsaPrivateKey;
	}

	@Override
	public RSAPublicKey getPublicKey() {
		return this.rsaPublicKey;
	}

	private String getKeyAsString(Resource resource) throws IOException {
		String key;
		try (final BufferedReader buf = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
			key = buf.lines().collect(Collectors.joining("\n"));
		}

		return removeKeyMarkerStrings(key);
	}

	private String removeKeyMarkerStrings(String key) {
		return key.replace("-----BEGIN PUBLIC KEY-----\n", "")
				.replace("-----END PUBLIC KEY-----", "")
				.replace("-----BEGIN PRIVATE KEY-----\n", "")
				.replace("-----END PRIVATE KEY-----", "");
	}

	private RSAPrivateKey toRSAPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] encodedKey = Base64.decodeBase64(privateKey);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedKey);
		return (RSAPrivateKey) kf.generatePrivate(keySpec);
	}

	private RSAPublicKey toRSAPublicKey(String publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
		byte[] encodedKey = Base64.decodeBase64(publicKey);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return (RSAPublicKey) kf.generatePublic(new X509EncodedKeySpec(encodedKey));
	}

	private void initKeys() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
		String privateKeyString = getKeyAsString(privateKeyResourceUri);
		String publicKeyString = getKeyAsString(publicKeyResourceUri);
		this.rsaPrivateKey = toRSAPrivateKey(privateKeyString);
		this.rsaPublicKey = toRSAPublicKey(publicKeyString);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		initKeys();
	}


}
