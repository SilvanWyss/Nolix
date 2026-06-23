/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.messaging;

import org.junit.jupiter.api.Test;

import ch.nolix.base.datamodel.dataobject.VoidObject;
import ch.nolix.base.net.messaging.IndexedPackage;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class IndexedPackageTest extends StandardTest {
  @Test
  void testCase_hasIndex_whenHasTheGivenIndex() {
    //setup
    final var testUnit = IndexedPackage.withIndexAndContent(105, new VoidObject());

    //execution
    final var result = testUnit.hasIndex(105);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_hasIndex_whenDoesNotHaveTheGivenIndex() {
    //setup
    final var testUnit = IndexedPackage.withIndexAndContent(105, new VoidObject());

    //execution
    final var result = testUnit.hasIndex(106);

    //verification
    expect(result).isFalse();
  }
}
