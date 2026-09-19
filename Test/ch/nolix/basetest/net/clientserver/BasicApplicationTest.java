/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.clientserver;

import org.junit.jupiter.api.Test;

import ch.nolix.base.foundation.util.VoidObject;
import ch.nolix.base.net.clientserver.StandardApplication;
import ch.nolix.base.net.clientservertestutil.TestSession;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.webapplication.main.WebClient;

/**
 * @author Silvan Wyss
 */
final class BasicApplicationTest extends StandardTest {
  @Test
  void testCase_withNameAndApplicationServiceAndInitialSessionClass() {
    // setup
    final var applicationService = new VoidObject();

    // execute
    @SuppressWarnings("unchecked")
    final var result = //
    StandardApplication.withNameAndApplicationServiceAndInitialSessionClass(
      "application",
      applicationService,
      TestSession.withClientClass(WebClient.class).getClass());

    // verify
    expect(result.getName()).isEqualTo("application");
    expect(result.getStoredApplicationService()).is(applicationService);
    expect(result.hasClientConnected()).isFalse();
  }
}
