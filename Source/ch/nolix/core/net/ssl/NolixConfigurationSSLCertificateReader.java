//package declaration
package ch.nolix.core.net.ssl;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.independent.nolixenvironment.GlobalNolixEnvironmentTool;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.netapi.sslapi.ISslCertificate;

//class
public final class NolixConfigurationSSLCertificateReader {

  //constant
  private static final String DEFAULT_SSL_CERTIFICATE_HEADER = "DefaultSSLCertificate";

  //constant
  private static final String DOMAIN_HEADER = "Domain";

  //constant
  private static final String PUBLIC_KEY_PEM_FILE_HEADER = "PublicKeyPEMFile";

  //constant
  private static final String PRIVATE_KEY_PEM_FILE_HEADER = "PrivateKeyPEMFile";

  //method
  public ISslCertificate getDefaultSSLCertificatefromLocalNolixConfiguration() {

    final var localNolixConfiguration = getNolixConfiguration();

    return getDefaultSSLCertificateFromNolixConfiguration(localNolixConfiguration);
  }

  //method
  public String getDefaultDomainFromLocalNolixConfiguration() {

    final var localNolixConfiguration = getNolixConfiguration();

    return getDefaultDomainFromNolixConfiguration(localNolixConfiguration);
  }

  //method
  private String getDefaultDomainFromDefaultCertificateConfiguration(
    final INode<?> defaultSSLCertificateConfiguration) {
    return defaultSSLCertificateConfiguration
      .getStoredFirstChildNodeWithHeader(DOMAIN_HEADER)
      .getSingleChildNodeHeader();
  }

  //method
  private String getDefaultDomainFromNolixConfiguration(final INode<?> nolixConfiguration) {

    final var defaultSSLCertificateConfiguration = nolixConfiguration
      .getStoredFirstChildNodeWithHeader(DEFAULT_SSL_CERTIFICATE_HEADER);

    return getDefaultDomainFromDefaultCertificateConfiguration(defaultSSLCertificateConfiguration);
  }

  //method
  private ISslCertificate getDefaultSSLCertificateFromDefaultCertificateConfiguration(
    final INode<?> defaultSSLCertificateConfiguration) {

    final var publicKeyPemFilePath = defaultSSLCertificateConfiguration
      .getStoredFirstChildNodeWithHeader(PUBLIC_KEY_PEM_FILE_HEADER)
      .getSingleChildNodeHeader();

    final var privateKeyPemFilePath = defaultSSLCertificateConfiguration
      .getStoredFirstChildNodeWithHeader(PRIVATE_KEY_PEM_FILE_HEADER)
      .getSingleChildNodeHeader();

    return new SslCertificate(publicKeyPemFilePath, privateKeyPemFilePath);
  }

  //method
  private ISslCertificate getDefaultSSLCertificateFromNolixConfiguration(final INode<?> nolixConfiguration) {

    final var defaultSSLCertificateConfiguration = nolixConfiguration
      .getStoredFirstChildNodeWithHeader(DEFAULT_SSL_CERTIFICATE_HEADER);

    return getDefaultSSLCertificateFromDefaultCertificateConfiguration(defaultSSLCertificateConfiguration);
  }

  //method
  private INode<?> getNolixConfiguration() {
    return Node.fromFile(GlobalNolixEnvironmentTool.NOLIX_CONFIGURATION_FILE_PATH);
  }
}
