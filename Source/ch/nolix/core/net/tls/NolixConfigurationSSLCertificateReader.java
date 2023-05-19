//package declaration
package ch.nolix.core.net.tls;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.independent.nolixenvironment.GlobalNolixEnvironmentHelper;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.netapi.tlsapi.ISSLCertificate;

//class
public final class NolixConfigurationSSLCertificateReader {
	
	//constant
	public static final NolixConfigurationSSLCertificateReader INSTANCE = new NolixConfigurationSSLCertificateReader();
	
	//constant
	private static final String DEFAULT_SSL_CERTIFICATE_HEADER = "DefaultSSLCertificate";
	
	//constant
	private static final String PUBLIC_KEY_PEM_FILE_HEADER = "PublicKeyPEMFile";
	
	//constant
	private static final String PRIVATE_KEY_PEM_FILE_HEADER = "PrivateKeyPEMFile";
	
	//constructor
	private NolixConfigurationSSLCertificateReader() {}
	
	//method
	public ISSLCertificate getDefaultSSLCertificatefromLocalNolixConfiguration() {
		
		final var localNolixConfiguration = getLocalNolixConfiguration();
		
		return getDefaultSSLCertificateFromNolixConfiguration(localNolixConfiguration);
	}
	
	//method
	private ISSLCertificate getDefaultSSLCertificateFromDefaultCertificateConfiguration(
		final INode<?> defaultSSLCertificateConfiguration
	) {
		
		final var publicKeyPEMFilePath =
		defaultSSLCertificateConfiguration
		.getOriFirstChildNodeWithHeader(PUBLIC_KEY_PEM_FILE_HEADER)
		.getSingleChildNodeHeader();
		
		final var privateKeyPEMFilePath =
		defaultSSLCertificateConfiguration
		.getOriFirstChildNodeWithHeader(PRIVATE_KEY_PEM_FILE_HEADER)
		.getSingleChildNodeHeader();
						
		return new SSLCertificate(publicKeyPEMFilePath, privateKeyPEMFilePath);
	}
	
	//method
	private ISSLCertificate getDefaultSSLCertificateFromNolixConfiguration(final INode<?> nolixConfiguration) {
		
		final var defaultSSLCertificateConfiguration =
		nolixConfiguration.getOriFirstChildNodeWithHeader(DEFAULT_SSL_CERTIFICATE_HEADER);
		
		return getDefaultSSLCertificateFromDefaultCertificateConfiguration(defaultSSLCertificateConfiguration);
	}
	
	//method
	private INode<?> getLocalNolixConfiguration() {
		
		final var localNolixConfigurationFilePath = GlobalNolixEnvironmentHelper.getLocalNolixConfigurationFilePath();
		
		return Node.fromFile(localNolixConfigurationFilePath);
	}
}
