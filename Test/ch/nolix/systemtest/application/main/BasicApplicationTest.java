/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.application.main;

import org.junit.jupiter.api.Test;

import ch.nolix.base.datamodel.dataobject.VoidObject;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.application.main.BasicApplication;
import ch.nolix.system.application.maintestutil.TestSession;
import ch.nolix.system.webapplication.main.WebClient;

/**
 * @author Silvan Wyss
 */
final class BasicApplicationTest extends StandardTest {
  @Test
  void testCase_withNameAndInitialSessionClassAndContext() {
    // setup
    final var applicationService = new VoidObject();

   // execute
    @SuppressWarnings("unchecked")
    final var result = BasicApplication.withNameAndInitialSessionClassAndContext(
      "My application",
      TestSession.withClientClass(WebClient.class).getClass(),
      applicationService);

   // verify
    expect(result.getApplicationName()).isEqualTo("My application");
    expect(result.hasInstanceAppendix()).isFalse();
    expect(result.getStoredApplicationService()).is(applicationService);
    expect(result.hasClientConnected()).isFalse();
  }
}
