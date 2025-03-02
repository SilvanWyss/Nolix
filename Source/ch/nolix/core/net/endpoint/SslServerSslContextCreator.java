package ch.nolix.core.net.endpoint;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

//Javax imports
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

import ch.nolix.core.environment.filesystem.FileSystemAccessor;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.net.ssl.SslCertificateKeyReader;
import ch.nolix.coreapi.netapi.sslapi.ISslCertificate;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;

final class SslServerSslContextCreator {

  private static final String PASSWORD = "my_password";

  private static final char[] PASSWORD_AS_CHAR_ARRAY = PASSWORD.toCharArray();

  private static final SslCertificateKeyReader SSL_CERTIFICATE_KEY_READER = new SslCertificateKeyReader();

  public SslContext createSSLContext(final ISslCertificate paramSSLCertificate) {
    try {

      X509Certificate cert = getCert(paramSSLCertificate);

      final var key = getPrivateKey(paramSSLCertificate);

      final var keystore = KeyStore.getInstance("JKS");
      keystore.load(null, PASSWORD_AS_CHAR_ARRAY);
      keystore.setCertificateEntry("cert-alias", cert);
      keystore.setKeyEntry("key-alias", key, PASSWORD_AS_CHAR_ARRAY, new Certificate[] { cert });

      final var keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
      keyManagerFactory.init(keystore, PASSWORD_AS_CHAR_ARRAY);

      final var sslContext = SSLContext.getInstance("TLS");
      sslContext.init(keyManagerFactory.getKeyManagers(), null, null);

      final var sslContextBuilder = SslContextBuilder.forServer(keyManagerFactory);

      return sslContextBuilder.build();
    } catch (final CertificateException
    | InvalidKeySpecException
    | IOException
    | KeyManagementException
    | KeyStoreException
    | NoSuchAlgorithmException
    | UnrecoverableEntryException exception) {
      throw WrapperException.forError(exception);
    }
  }

  private X509Certificate getCert(final ISslCertificate paramSSLCertificate) throws CertificateException {

    final var filePath = paramSSLCertificate.getPublicKeyPemFilePath();

    return (X509Certificate) CertificateFactory
      .getInstance("X509")
      .generateCertificate(new ByteArrayInputStream(FileSystemAccessor.readFileToBytes(filePath)));
  }

  private PrivateKey getPrivateKey(final ISslCertificate paramSSLCertificate)
  throws InvalidKeySpecException, NoSuchAlgorithmException {
    final var privateKeyPemFilePath = paramSSLCertificate.getPrivateKeyPemFilePath();
    final var privateKey = SSL_CERTIFICATE_KEY_READER.readKeyFromPemFile(privateKeyPemFilePath);
    final var privateKeyAsBytes = Base64.getDecoder().decode(privateKey);
    final var keySpec = new PKCS8EncodedKeySpec(privateKeyAsBytes);
    final var keyFactory = KeyFactory.getInstance("EC");
    return keyFactory.generatePrivate(keySpec);
  }
}
