/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coretest.net.target;

import org.junit.jupiter.api.Test;

import ch.nolix.core.net.target.ApplicationInstanceTarget;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.net.netconstant.PortCatalog;
import ch.nolix.coreapi.net.securityproperty.SecurityMode;

/**
 * @author Silvan Wyss
 */
final class ApplicationInstanceTargetTest extends StandardTest {
  @Test
  void testCase_forIpOrDomainAndPortAndApplicationInstanceNameAndApplicationUrlInstanceNameAndSecurityModeForConnections() {
    //execution
    final var result = ApplicationInstanceTarget
      .forIpOrDomainAndPortAndApplicationInstanceNameAndApplicationUrlInstanceNameAndSecurityModeForConnections(
        "nolix.tech",
        PortCatalog.HTTPS,
        "Demo Application",
        "demo_application",
        SecurityMode.SSL);

    //verification
    expect(result.getIpOrDomain()).isEqualTo("nolix.tech");
    expect(result.getPort()).isEqualTo(PortCatalog.HTTPS);
    expect(result.getApplicationInstanceName()).isEqualTo("Demo Application");
    expect(result.getApplicationUrlInstanceName()).isEqualTo("demo_application");
    expect(result.getSecurityModeForConnection()).isEqualTo(SecurityMode.SSL);
  }

  @Test
  void testCase_toUrl_forHttpPortAndNoneSecurityMode() {
    //setup
    final var testUnit = ApplicationInstanceTarget
      .forIpOrDomainAndPortAndApplicationInstanceNameAndApplicationUrlInstanceNameAndSecurityModeForConnections(
        "nolix.tech",
        PortCatalog.HTTP,
        "Demo Application",
        "demo_application",
        SecurityMode.NONE);

    //execution
    final var result = testUnit.toUrl();

    //verification
    expect(result).isEqualTo("http://nolix.tech?app=demo_application");
  }

  @Test
  void testCase_toUrl_forHttpsPortAndSSLSecurityMode() {
    //setup
    final var testUnit = ApplicationInstanceTarget
      .forIpOrDomainAndPortAndApplicationInstanceNameAndApplicationUrlInstanceNameAndSecurityModeForConnections(
        "nolix.tech",
        PortCatalog.HTTPS,
        "Demo Application",
        "demo_application",
        SecurityMode.SSL);

    //execution
    final var result = testUnit.toUrl();

    //verification
    expect(result).isEqualTo("https://nolix.tech?app=demo_application");
  }

  @Test
  void testCase_toUrl_forCustomPortAndNoneSecurityMode() {
    //setup
    final var testUnit = ApplicationInstanceTarget
      .forIpOrDomainAndPortAndApplicationInstanceNameAndApplicationUrlInstanceNameAndSecurityModeForConnections(
        "nolix.tech",
        50000,
        "Demo Application",
        "demo_application",
        SecurityMode.NONE);

    //execution
    final var result = testUnit.toUrl();

    //verification
    expect(result).isEqualTo("http://nolix.tech:50000?app=demo_application");
  }

  @Test
  void testCase_toUrl_forCustomPortAndSslSecurityMode() {
    //setup
    final var testUnit = ApplicationInstanceTarget
      .forIpOrDomainAndPortAndApplicationInstanceNameAndApplicationUrlInstanceNameAndSecurityModeForConnections(
        "nolix.tech",
        50000,
        "Demo Application",
        "demo_application",
        SecurityMode.SSL);

    //execution
    final var result = testUnit.toUrl();

    //verification
    expect(result).isEqualTo("https://nolix.tech:50000?app=demo_application");
  }
}
