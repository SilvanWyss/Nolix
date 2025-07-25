package ch.nolix.core.net.ssl;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.environment.nolixenvironment.NolixEnvironmentService;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.netapi.sslapi.ISslCertificate;

public final class NolixConfigurationSSLCertificateReader {

  private static final String DEFAULT_SSL_CERTIFICATE_HEADER = "DefaultSSLCertificate";

  private static final String DOMAIN_HEADER = "Domain";

  private static final String PUBLIC_KEY_PEM_FILE_HEADER = "PublicKeyPEMFile";

  private static final String PRIVATE_KEY_PEM_FILE_HEADER = "PrivateKeyPEMFile";

  public ISslCertificate getDefaultSSLCertificatefromLocalNolixConfiguration() {

    final var localNolixConfiguration = getNolixConfiguration();

    return getDefaultSSLCertificateFromNolixConfiguration(localNolixConfiguration);
  }

  public String getDefaultDomainFromLocalNolixConfiguration() {

    final var localNolixConfiguration = getNolixConfiguration();

    return getDefaultDomainFromNolixConfiguration(localNolixConfiguration);
  }

  private String getDefaultDomainFromDefaultCertificateConfiguration(
    final INode<?> defaultSSLCertificateConfiguration) {
    return defaultSSLCertificateConfiguration
      .getStoredFirstChildNodeWithHeader(DOMAIN_HEADER)
      .getSingleChildNodeHeader();
  }

  private String getDefaultDomainFromNolixConfiguration(final INode<?> nolixConfiguration) {

    final var defaultSSLCertificateConfiguration = nolixConfiguration
      .getStoredFirstChildNodeWithHeader(DEFAULT_SSL_CERTIFICATE_HEADER);

    return getDefaultDomainFromDefaultCertificateConfiguration(defaultSSLCertificateConfiguration);
  }

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

  private ISslCertificate getDefaultSSLCertificateFromNolixConfiguration(final INode<?> nolixConfiguration) {

    final var defaultSSLCertificateConfiguration = nolixConfiguration
      .getStoredFirstChildNodeWithHeader(DEFAULT_SSL_CERTIFICATE_HEADER);

    return getDefaultSSLCertificateFromDefaultCertificateConfiguration(defaultSSLCertificateConfiguration);
  }

  private INode<?> getNolixConfiguration() {
    return Node.fromFile(NolixEnvironmentService.getNolixConfigurationFilePath());
  }
}
