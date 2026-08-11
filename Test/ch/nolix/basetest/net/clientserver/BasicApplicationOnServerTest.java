/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.clientserver;

import org.junit.jupiter.api.Test;

import ch.nolix.base.net.clientserver.BasicApplication;
import ch.nolix.base.net.clientserver.LocalServer;
import ch.nolix.base.net.clientservertestutil.TestSession;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.base.testing.testutil.VoidObject;
import ch.nolix.system.webapplication.main.WebClient;

/**
 * @author Silvan Wyss
 */
final class BasicApplicationOnServerTest extends StandardTest {
  @Test
  void testCase_getNameAddendum_whenBelongsToServer() {
    try (final var localServer = new LocalServer()) {
      // setup
      final var applicationService = new VoidObject();
      @SuppressWarnings("unchecked")
      final var testUnit = BasicApplication.withNameAndInitialSessionClassAndContext(
        "My application",
        TestSession.withClientClass(WebClient.class).getClass(),
        applicationService);
      localServer.addApplicationWithNameAddendum(testUnit, "Instance1");

      // execute
      final var result = testUnit.getInstanceAppendix();

      // verify
      expect(result).isEqualTo("Instance1");
    }
  }

  @Test
  void testCase_getInstanceName_whenDoesNotBelongToAServer() {
    // setup
    final var applicationService = new VoidObject();
    @SuppressWarnings("unchecked")
    final var testUnit = BasicApplication.withNameAndInitialSessionClassAndContext(
      "My application",
      TestSession.withClientClass(WebClient.class).getClass(),
      applicationService);

    // execute
    final var result = testUnit.getInstanceName();

    // verify
    expect(result).isEqualTo("My application");
  }

  @Test
  void testCase_getInstanceName_whenBelongsToServer() {
    try (final var localServer = new LocalServer()) {
      // setup
      final var applicationService = new VoidObject();
      @SuppressWarnings("unchecked")
      final var testUnit = BasicApplication.withNameAndInitialSessionClassAndContext(
        "My application",
        TestSession.withClientClass(WebClient.class).getClass(),
        applicationService);
      localServer.addApplicationWithNameAddendum(testUnit, "Instance1");

      // execute
      final var result = testUnit.getInstanceName();

      // verify
      expect(result).isEqualTo("My application Instance1");
    }
  }

  @Test
  void testCase_getUrlInstanceName_whenBelongsToServer() {
    try (final var localServer = new LocalServer()) {
      // setup
      final var applicationService = new VoidObject();
      @SuppressWarnings("unchecked")
      final var testUnit = BasicApplication.withNameAndInitialSessionClassAndContext(
        "My application",
        TestSession.withClientClass(WebClient.class).getClass(),
        applicationService);
      localServer.addApplicationWithNameAddendum(testUnit, "Instance1");

      // execute
      final var result = testUnit.getUrlInstanceName();

      // verify
      expect(result).isEqualTo("my_application_instance1");
    }
  }
}
