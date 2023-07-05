//package declaration
package ch.nolix.core.net.endpoint;

//Java imports
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

//Javax imports
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

//Netty imports
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;

//own imports
import ch.nolix.core.environment.filesystem.FileSystemAccessor;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.coreapi.netapi.tlsapi.ISSLCertificate;

//class
final class SecureServerSSLContextCreator {
	
	//method
	public SslContext createSSLContext(final ISSLCertificate paramSSLCertificate) {
		try {
			KeyStore keystore = KeyStore.getInstance("JKS");
			keystore.load(null, "changeit".toCharArray());
			
			String password = "changeit";
			X509Certificate cert = getCert(paramSSLCertificate);
			PrivateKey key = getPrivateKey(paramSSLCertificate);
			keystore.setCertificateEntry("cert-alias", cert);
			keystore.setKeyEntry("key-alias", key, password.toCharArray(), new Certificate[]{cert});
			
			KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			keyManagerFactory.init(keystore, "changeit".toCharArray());
			
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(keyManagerFactory.getKeyManagers(), null, null);
			
			var sslContextBuilder = SslContextBuilder.forServer(keyManagerFactory);
			return sslContextBuilder.build();
		} catch (Exception exception) {
			throw WrapperException.forError(exception);
		}
	}
	
	private X509Certificate getCert(final ISSLCertificate paramSSLCertificate) throws Exception {
		
		String filePath = paramSSLCertificate.getPublicKeyPEMFilePath();
		
		return
		(X509Certificate)CertificateFactory
		.getInstance("X509")
		.generateCertificate(new ByteArrayInputStream(FileSystemAccessor.readFileToBytes(filePath)));
	}
	
	public static PrivateKey getPrivateKey(final ISSLCertificate paramSSLCertificate) throws Exception {
		
		String filePath = paramSSLCertificate.getPrivateKeyPEMFilePath();
		
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		StringBuilder privateKeyBuilder = new StringBuilder();
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
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyBytes);
		KeyFactory kf = KeyFactory.getInstance("EC");
		PrivateKey privateKey = kf.generatePrivate(spec);
		return privateKey;
	}
}
