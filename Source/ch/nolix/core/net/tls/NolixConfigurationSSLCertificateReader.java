//package declaration
package ch.nolix.core.net.tls;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.independent.nolixenvironment.GlobalNolixEnvironmentHelper;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.netapi.tlsapi.ISSLCertificate;

//class
public final class NolixConfigurationSSLCertificateReader {

  // constant
  private static final String DEFAULT_SSL_CERTIFICATE_HEADER = "DefaultSSLCertificate";

  // constant
  private static final String DOMAIN_HEADER = "Domain";

  // constant
  private static final String PUBLIC_KEY_PEM_FILE_HEADER = "PublicKeyPEMFile";

  // constant
  private static final String PRIVATE_KEY_PEM_FILE_HEADER = "PrivateKeyPEMFile";

  // method
  public ISSLCertificate getDefaultSSLCertificatefromLocalNolixConfiguration() {

    final var localNolixConfiguration = getLocalNolixConfiguration();

    return getDefaultSSLCertificateFromNolixConfiguration(localNolixConfiguration);
  }

  // method
  public String getDefaultDomainFromLocalNolixConfiguration() {

    final var localNolixConfiguration = getLocalNolixConfiguration();

    return getDefaultDomainFromNolixConfiguration(localNolixConfiguration);
  }

  // method
  private String getDefaultDomainFromDefaultCertificateConfiguration(
      final INode<?> defaultSSLCertificateConfiguration) {
    return defaultSSLCertificateConfiguration
        .getStoredFirstChildNodeWithHeader(DOMAIN_HEADER)
        .getSingleChildNodeHeader();
  }

  // method
  private String getDefaultDomainFromNolixConfiguration(final INode<?> nolixConfiguration) {

    final var defaultSSLCertificateConfiguration = nolixConfiguration
        .getStoredFirstChildNodeWithHeader(DEFAULT_SSL_CERTIFICATE_HEADER);

    return getDefaultDomainFromDefaultCertificateConfiguration(defaultSSLCertificateConfiguration);
  }

  // method
  private ISSLCertificate getDefaultSSLCertificateFromDefaultCertificateConfiguration(
      final INode<?> defaultSSLCertificateConfiguration) {

    final var publicKeyPEMFilePath = defaultSSLCertificateConfiguration
        .getStoredFirstChildNodeWithHeader(PUBLIC_KEY_PEM_FILE_HEADER)
        .getSingleChildNodeHeader();

    final var privateKeyPEMFilePath = defaultSSLCertificateConfiguration
        .getStoredFirstChildNodeWithHeader(PRIVATE_KEY_PEM_FILE_HEADER)
        .getSingleChildNodeHeader();

    return new SSLCertificate(publicKeyPEMFilePath, privateKeyPEMFilePath);
  }

  // method
  private ISSLCertificate getDefaultSSLCertificateFromNolixConfiguration(final INode<?> nolixConfiguration) {

    final var defaultSSLCertificateConfiguration = nolixConfiguration
        .getStoredFirstChildNodeWithHeader(DEFAULT_SSL_CERTIFICATE_HEADER);

    return getDefaultSSLCertificateFromDefaultCertificateConfiguration(defaultSSLCertificateConfiguration);
  }

  // method
  private INode<?> getLocalNolixConfiguration() {

    final var localNolixConfigurationFilePath = GlobalNolixEnvironmentHelper.getLocalNolixConfigurationFilePath();

    return Node.fromFile(localNolixConfigurationFilePath);
  }
}
