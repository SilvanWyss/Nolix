//package declaration
package ch.nolix.coretest.nettest.messagingtest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.net.messaging.IndexedPackage;
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.testing.test.StandardTest;

//class
final class IndexedPackageTest extends StandardTest {

  //method
  @Test
  void testCase_hasIndex_whenHasTheGivenIndex() {

    //setup
    final var testUnit = IndexedPackage.withIndexAndContent(105, new VoidObject());

    //execution
    final var result = testUnit.hasIndex(105);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_hasIndex_whenDoesNotHaveTheGivenIndex() {

    //setup
    final var testUnit = IndexedPackage.withIndexAndContent(105, new VoidObject());

    //execution
    final var result = testUnit.hasIndex(106);

    //verification
    expectNot(result);
  }
}
