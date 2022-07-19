package tr.com.innova.internship.encryiption;

public interface AsymmetricEncryptionKeyProvider<A, B> {
    A getPrivateKey();

    B getPublicKey();
}

