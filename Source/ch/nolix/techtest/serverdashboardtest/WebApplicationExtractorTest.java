//package declaration
package ch.nolix.techtest.serverdashboardtest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.application.main.LocalServer;
import ch.nolix.tech.serverdashboard.WebApplicationExtractor;

//class
public final class WebApplicationExtractorTest extends Test {

  //TODO: Make BaseServer a Clearable.
  @TestCase
  public void testCase_whenTheGivenServerDoesNotContainWebApplications() {

    //setup
    final var server = new LocalServer();
    final var testUnit = new WebApplicationExtractor();

    //setup verification
    expect(server.getStoredApplications()).isEmpty();

    //execution
    final var result = testUnit.getStoredWebApplicationsOfServer(server);

    //verification
    expect(result).isEmpty();
  }
}
