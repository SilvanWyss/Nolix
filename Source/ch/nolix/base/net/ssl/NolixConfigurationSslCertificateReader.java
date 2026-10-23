/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.ssl;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.foundation.nolixenvironment.NolixEnvironmentService;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.net.ssl.ISslCertificate;

/**
 * @author Silvan Wyss
 */
public final class NolixConfigurationSslCertificateReader {
  private static final String DEFAULT_SSL_CERTIFICATE_HEADER = "DefaultSSLCertificate";

  private static final String DOMAIN_HEADER = "Domain";

  private static final String PUBLIC_KEY_PEM_FILE_HEADER = "PublicKeyPEMFile";

  private static final String PRIVATE_KEY_PEM_FILE_HEADER = "PrivateKeyPEMFile";

  private NolixConfigurationSslCertificateReader() {
  }

  /**
   * @return the default {@link ISslCertificate} from the local Nolix
   *         configuration
   */
  public static ISslCertificate getDefaultSSLCertificatefromLocalNolixConfiguration() {
    final var localNolixConfiguration = getNolixConfiguration();

    return getDefaultSSLCertificateFromNolixConfiguration(localNolixConfiguration);
  }

  /**
   * @return the default domain from the local Nolix configuration
   */
  public static String getDefaultDomainFromLocalNolixConfiguration() {
    final var localNolixConfiguration = getNolixConfiguration();

    return getDefaultDomainFromNolixConfiguration(localNolixConfiguration);
  }

  private static String getDefaultDomainFromDefaultCertificateConfiguration(
    final Node<?> defaultSSLCertificateConfiguration) {
    return defaultSSLCertificateConfiguration
      .getStoredFirstChildNodeWithHeader(DOMAIN_HEADER)
      .getSingleChildNodeHeader();
  }

  private static String getDefaultDomainFromNolixConfiguration(final Node<?> nolixConfiguration) {
    final var defaultSSLCertificateConfiguration = nolixConfiguration
      .getStoredFirstChildNodeWithHeader(DEFAULT_SSL_CERTIFICATE_HEADER);

    return getDefaultDomainFromDefaultCertificateConfiguration(defaultSSLCertificateConfiguration);
  }

  private static ISslCertificate getDefaultSSLCertificateFromDefaultCertificateConfiguration(
    final Node<?> defaultSSLCertificateConfiguration) {
    final var publicKeyPemFilePath = defaultSSLCertificateConfiguration
      .getStoredFirstChildNodeWithHeader(PUBLIC_KEY_PEM_FILE_HEADER)
      .getSingleChildNodeHeader();

    final var privateKeyPemFilePath = defaultSSLCertificateConfiguration
      .getStoredFirstChildNodeWithHeader(PRIVATE_KEY_PEM_FILE_HEADER)
      .getSingleChildNodeHeader();

    return new SslCertificate(publicKeyPemFilePath, privateKeyPemFilePath);
  }

  private static ISslCertificate getDefaultSSLCertificateFromNolixConfiguration(final Node<?> nolixConfiguration) {
    final var defaultSSLCertificateConfiguration = nolixConfiguration
      .getStoredFirstChildNodeWithHeader(DEFAULT_SSL_CERTIFICATE_HEADER);

    return getDefaultSSLCertificateFromDefaultCertificateConfiguration(defaultSSLCertificateConfiguration);
  }

  private static Node<?> getNolixConfiguration() {
    return ImmutableNode.fromFile(NolixEnvironmentService.getNolixConfigurationFilePath());
  }
}
