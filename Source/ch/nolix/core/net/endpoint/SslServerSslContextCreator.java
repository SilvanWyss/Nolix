//package declaration
package ch.nolix.core.net.endpoint;

//Java imports
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

//Javax imports
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

//own imports
import ch.nolix.core.environment.filesystem.FileSystemAccessor;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.coreapi.netapi.sslapi.ISslCertificate;
//Netty imports
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;

//class
final class SslServerSslContextCreator {

  //method
  public SslContext createSSLContext(final ISslCertificate paramSSLCertificate) {
    try {

      final var password = "my_password";
      X509Certificate cert = getCert(paramSSLCertificate);

      final var key = getPrivateKey(paramSSLCertificate);

      final var keystore = KeyStore.getInstance("JKS");
      keystore.load(null, password.toCharArray());
      keystore.setCertificateEntry("cert-alias", cert);
      keystore.setKeyEntry("key-alias", key, password.toCharArray(), new Certificate[] { cert });

      final var keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
      keyManagerFactory.init(keystore, password.toCharArray());

      final var sslContext = SSLContext.getInstance("TLS");
      sslContext.init(keyManagerFactory.getKeyManagers(), null, null);

      final var sslContextBuilder = SslContextBuilder.forServer(keyManagerFactory);

      return sslContextBuilder.build();
    } catch (final Exception exception) {
      throw WrapperException.forError(exception);
    }
  }

  //method
  private X509Certificate getCert(final ISslCertificate paramSSLCertificate) throws CertificateException {

    final var filePath = paramSSLCertificate.getPublicKeyPEMFilePath();

    return (X509Certificate) CertificateFactory
      .getInstance("X509")
      .generateCertificate(new ByteArrayInputStream(FileSystemAccessor.readFileToBytes(filePath)));
  }

  private PrivateKey getPrivateKey(final ISslCertificate paramSSLCertificate)
  throws Exception { //NOSONAR: This method can throw Exceptions of several types.

    final var filePath = paramSSLCertificate.getPrivateKeyPEMFilePath();

    @SuppressWarnings("resource")
    final var reader = new BufferedReader(new FileReader(filePath, Charset.defaultCharset()));
    final var privateKeyBuilder = new StringBuilder();
    String line;

    while ((line = reader.readLine()) != null) {
      if (line.startsWith("-----BEGIN PRIVATE KEY-----")) {
        continue;
      }
      if (line.startsWith("-----END PRIVATE KEY-----")) {
        break;
      }
      privateKeyBuilder.append(line.trim());
    }

    byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBuilder.toString());
    final var spec = new PKCS8EncodedKeySpec(privateKeyBytes);
    final var keyFactory = KeyFactory.getInstance("EC");
    return keyFactory.generatePrivate(spec);
  }
}
