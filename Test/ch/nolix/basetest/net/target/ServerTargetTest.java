/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.target;

import org.junit.jupiter.api.Test;

import ch.nolix.base.net.target.ServerTarget;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.net.netproperty.SecurityMode;

/**
 * @author Silvan Wyss
 */
final class ServerTargetTest extends StandardTest {
  @Test
  void testCase_forIpOrDomainAndPortAndSecurityModeForConnections() {
    // define test parameters
    final var domain = "nolix.ch";
    final var port = 443;
    final var securiyMode = SecurityMode.SSL;

    // execute
    final var result = ServerTarget.forIpOrDomainAndPortAndSecurityModeForConnections(domain, port, securiyMode);

    // verify
    expect(result.getIpOrDomain()).isEqualTo(domain);
    expect(result.getPort()).isEqualTo(port);
    expect(result.getSecurityModeForConnection()).isEqualTo(securiyMode);
  }
}
