//package declaration
package ch.nolix.coretest.nettest.messagingtest;

//own imports
import ch.nolix.core.net.messaging.IndexedPackage;
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class IndexedPackageTest extends Test {

  //method
  @TestCase
  public void testCase_hasIndex_whenHasTheGivenIndex() {

    //setup
    final var testUnit = IndexedPackage.withIndexAndContent(105, new VoidObject());

    //execution
    final var result = testUnit.hasIndex(105);

    //verification
    expect(result);
  }

  //method
  @TestCase
  public void testCase_hasIndex_whenDoesNotHaveTheGivenIndex() {

    //setup
    final var testUnit = IndexedPackage.withIndexAndContent(105, new VoidObject());

    //execution
    final var result = testUnit.hasIndex(106);

    //verification
    expectNot(result);
  }
}
