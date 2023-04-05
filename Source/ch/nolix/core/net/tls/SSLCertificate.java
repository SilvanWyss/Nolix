//package declaration
package ch.nolix.core.net.tls;

//Java imports
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.netapi.tlsapi.ISSLCertificate;

//record
public final class SSLCertificate implements ISSLCertificate {
	
	//static method
	public static SSLCertificate fromPEMFiles(final String publicKeyPEMFilePath, final String privateKeyPEMFilePath) {
		return new SSLCertificate(getBytesOfFile(publicKeyPEMFilePath), getBytesOfFile(privateKeyPEMFilePath));
	}
	
	//static method
	private static byte[] getBytesOfFile(String filePath) {
		
		final var file = new File(filePath);
		
		return getBytesOfFile(file);
	}
	
	//static method
	private static byte[] getBytesOfFile(final File file) {
		
		final var bytes = new byte[(int) file.length()];
		
		try (final var fileInputStream = new FileInputStream(file)) {
			fileInputStream.read(bytes);
		} catch (final IOException paramIOException) {
			throw WrapperException.forError(paramIOException);
		}
		return bytes;
	}
	
	//attribute
	private final byte[] publicKeyBytes;
	
	//attribute
	private final byte[] privateKeyBytes;
	
	//constructor
	private SSLCertificate(final byte[] publicKeyBytes, final byte[] privateKeyBytes) {
		
		GlobalValidator.assertThat(publicKeyBytes).thatIsNamed("public key bytes").isNotNull();
		GlobalValidator.assertThat(privateKeyBytes).thatIsNamed("private key bytes").isNotNull();
		
		this.publicKeyBytes = publicKeyBytes;
		this.privateKeyBytes = privateKeyBytes;
	}
	
	//method
	@Override
	public byte[] getPrivateKeyBytes() {
		return privateKeyBytes.clone();
	}
	
	//method
	@Override
	public byte[] getPublicKeyBytes() {
		return publicKeyBytes.clone();
	}
}
