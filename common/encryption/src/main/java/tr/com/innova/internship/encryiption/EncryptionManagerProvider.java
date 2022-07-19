package tr.com.innova.internship.encryiption;

import java.security.GeneralSecurityException;

public class EncryptionManagerProvider {
	private EncryptionManager encryptionManager;

	public EncryptionManagerProvider(String secret, String ivySecret) throws GeneralSecurityException {
		encryptionManager = EncryptionManager
				.newBuider()
				.withIvyScreet(ivySecret)
				.withScretKey(secret)
				.build();
	}

	public EncryptionManager getEncryptionManager() {
		return encryptionManager;
	}
}
