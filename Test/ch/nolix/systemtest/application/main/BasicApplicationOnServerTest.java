/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.application.main;

import org.junit.jupiter.api.Test;

import ch.nolix.core.misc.dataobject.VoidObject;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.application.main.BasicApplication;
import ch.nolix.system.application.main.LocalServer;
import ch.nolix.system.application.maintestutil.TestSession;
import ch.nolix.system.webapplication.main.WebClient;

/**
 * @author Silvan Wyss
 */
final class BasicApplicationOnServerTest extends StandardTest {
  @Test
  void testCase_getNameAddendum_whenBelongsToServer() {
    try (final var localServer = new LocalServer()) {
      //setup
      final var applicationService = new VoidObject();
      @SuppressWarnings("unchecked")
      final var testUnit = BasicApplication.withNameAndInitialSessionClassAndContext(
        "My application",
        TestSession.withClientClass(WebClient.class).getClass(),
        applicationService);
      localServer.addApplicationWithNameAddendum(testUnit, "Instance1");

      //execution
      final var result = testUnit.getInstanceAppendix();

      //verification
      expect(result).isEqualTo("Instance1");
    }
  }

  @Test
  void testCase_getInstanceName_whenDoesNotBelongToAServer() {
    //setup
    final var applicationService = new VoidObject();
    @SuppressWarnings("unchecked")
    final var testUnit = BasicApplication.withNameAndInitialSessionClassAndContext(
      "My application",
      TestSession.withClientClass(WebClient.class).getClass(),
      applicationService);

    //execution
    final var result = testUnit.getInstanceName();

    //verification
    expect(result).isEqualTo("My application");
  }

  @Test
  void testCase_getInstanceName_whenBelongsToServer() {
    try (final var localServer = new LocalServer()) {
      //setup
      final var applicationService = new VoidObject();
      @SuppressWarnings("unchecked")
      final var testUnit = BasicApplication.withNameAndInitialSessionClassAndContext(
        "My application",
        TestSession.withClientClass(WebClient.class).getClass(),
        applicationService);
      localServer.addApplicationWithNameAddendum(testUnit, "Instance1");

      //execution
      final var result = testUnit.getInstanceName();

      //verification
      expect(result).isEqualTo("My application Instance1");
    }
  }

  @Test
  void testCase_getUrlInstanceName_whenBelongsToServer() {
    try (final var localServer = new LocalServer()) {
      //setup
      final var applicationService = new VoidObject();
      @SuppressWarnings("unchecked")
      final var testUnit = BasicApplication.withNameAndInitialSessionClassAndContext(
        "My application",
        TestSession.withClientClass(WebClient.class).getClass(),
        applicationService);
      localServer.addApplicationWithNameAddendum(testUnit, "Instance1");

      //execution
      final var result = testUnit.getUrlInstanceName();

      //verification
      expect(result).isEqualTo("my_application_instance1");
    }
  }
}
