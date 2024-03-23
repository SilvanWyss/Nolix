//package declaration
package ch.nolix.techtest.serverdashboardtest;

import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.system.application.main.LocalServer;
import ch.nolix.tech.serverdashboard.WebApplicationExtractor;

//class
final class WebApplicationExtractorTest extends StandardTest {

  @Test
  void testCase_whenTheGivenServerDoesNotContainWebApplications() {

    //setup
    final var server = new LocalServer();
    final var testUnit = new WebApplicationExtractor();

    //setup verification
    expect(server.isEmpty());

    //execution
    final var result = testUnit.getStoredWebApplicationsOfServer(server);

    //verification
    expect(result).isEmpty();
  }
}
