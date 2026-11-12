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
  public static ISslCertificate getDefaultSslCertificateFromLocalNolixConfiguration() {
    final var localNolixConfiguration = getNolixConfiguration();

    return getDefaultSslCertificateFromNolixConfiguration(localNolixConfiguration);
  }

  /**
   * @return the default domain from the local Nolix configuration
   */
  public static String getDefaultDomainFromLocalNolixConfiguration() {
    final var localNolixConfiguration = getNolixConfiguration();

    return getDefaultDomainFromNolixConfiguration(localNolixConfiguration);
  }

  private static String getDefaultDomainFromDefaultCertificateConfiguration(
    final Node<?> defaultSslCertificateConfiguration) {
    return defaultSslCertificateConfiguration
      .getStoredFirstChildNodeWithHeader(DOMAIN_HEADER)
      .getSingleChildNodeHeader();
  }

  private static String getDefaultDomainFromNolixConfiguration(final Node<?> nolixConfiguration) {
    final var defaultSslCertificateConfiguration = nolixConfiguration
      .getStoredFirstChildNodeWithHeader(DEFAULT_SSL_CERTIFICATE_HEADER);

    return getDefaultDomainFromDefaultCertificateConfiguration(defaultSslCertificateConfiguration);
  }

  private static ISslCertificate getDefaultSslCertificateFromDefaultCertificateConfiguration(
    final Node<?> defaultSslCertificateConfiguration) {
    final var publicKeyPemFilePath = defaultSslCertificateConfiguration
      .getStoredFirstChildNodeWithHeader(PUBLIC_KEY_PEM_FILE_HEADER)
      .getSingleChildNodeHeader();

    final var privateKeyPemFilePath = defaultSslCertificateConfiguration
      .getStoredFirstChildNodeWithHeader(PRIVATE_KEY_PEM_FILE_HEADER)
      .getSingleChildNodeHeader();

    return new SslCertificate(publicKeyPemFilePath, privateKeyPemFilePath);
  }

  private static ISslCertificate getDefaultSslCertificateFromNolixConfiguration(final Node<?> nolixConfiguration) {
    final var defaultSslCertificateConfiguration = nolixConfiguration
      .getStoredFirstChildNodeWithHeader(DEFAULT_SSL_CERTIFICATE_HEADER);

    return getDefaultSslCertificateFromDefaultCertificateConfiguration(defaultSslCertificateConfiguration);
  }

  private static Node<?> getNolixConfiguration() {
    return ImmutableNode.fromFile(NolixEnvironmentService.getNolixConfigurationFilePath());
  }
}
