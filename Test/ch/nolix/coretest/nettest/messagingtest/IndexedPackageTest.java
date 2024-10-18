package ch.nolix.coretest.nettest.messagingtest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.net.messaging.IndexedPackage;
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.testing.standardtest.StandardTest;

final class IndexedPackageTest extends StandardTest {

  @Test
  void testCase_hasIndex_whenHasTheGivenIndex() {

    //setup
    final var testUnit = IndexedPackage.withIndexAndContent(105, new VoidObject());

    //execution
    final var result = testUnit.hasIndex(105);

    //verification
    expect(result);
  }

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
