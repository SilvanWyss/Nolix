//package declaration
package ch.nolix.systemtest.applicationtest.maintest;

//own imports
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.application.main.BasicApplication;
import ch.nolix.system.application.main.LocalServer;
import ch.nolix.system.application.maintestutil.TestSession;
import ch.nolix.system.application.webapplication.WebClient;

//class
public final class BasicApplicationOnServerTest extends Test {

  //method
  @TestCase
  public void testCase_getNameAddendum_whenBelongsToServer() {
    try (final var localServer = new LocalServer()) {

      //setup
      final var applicationContext = new VoidObject();
      @SuppressWarnings("unchecked")
      final var testUnit = BasicApplication.withNameAndInitialSessionClassAndContext(
        "My application",
        TestSession.withClientClass(WebClient.class).getClass(),
        applicationContext);
      localServer.addApplicationWithNameAddendum(testUnit, "Instance1");

      //execution
      final var result = testUnit.getNameAddendum();

      //verification
      expect(result).isEqualTo("Instance1");
    }
  }

  //method
  @TestCase
  public void testCase_getInstanceName_whenDoesNotBelongToAServer() {

    //setup
    final var applicationContext = new VoidObject();
    @SuppressWarnings("unchecked")
    final var testUnit = BasicApplication.withNameAndInitialSessionClassAndContext(
      "My application",
      TestSession.withClientClass(WebClient.class).getClass(),
      applicationContext);

    //execution
    final var result = testUnit.getInstanceName();

    //verification
    expect(result).isEqualTo("My application");
  }

  //method
  @TestCase
  public void testCase_getInstanceName_whenBelongsToServer() {
    try (final var localServer = new LocalServer()) {

      //setup
      final var applicationContext = new VoidObject();
      @SuppressWarnings("unchecked")
      final var testUnit = BasicApplication.withNameAndInitialSessionClassAndContext(
        "My application",
        TestSession.withClientClass(WebClient.class).getClass(),
        applicationContext);
      localServer.addApplicationWithNameAddendum(testUnit, "Instance1");

      //execution
      final var result = testUnit.getInstanceName();

      //verification
      expect(result).isEqualTo("My application Instance1");
    }
  }

  //method
  @TestCase
  public void testCase_getUrlInstanceName_whenBelongsToServer() {
    try (final var localServer = new LocalServer()) {

      //setup
      final var applicationContext = new VoidObject();
      @SuppressWarnings("unchecked")
      final var testUnit = BasicApplication.withNameAndInitialSessionClassAndContext(
        "My application",
        TestSession.withClientClass(WebClient.class).getClass(),
        applicationContext);
      localServer.addApplicationWithNameAddendum(testUnit, "Instance1");

      //execution
      final var result = testUnit.getUrlInstanceName();

      //verification
      expect(result).isEqualTo("my_application_instance1");
    }
  }
}
