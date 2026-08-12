/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.commontype.arraytool.arrayiteratortest;

import org.junit.jupiter.api.Test;

import ch.nolix.base.commontype.arraytool.ArrayIterator;
import ch.nolix.base.foundation.util.VoidObject;
import ch.nolix.base.programcontrol.flowcontrol.FlowController;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class GetCopyMethodTest extends StandardTest {
  @Test
  void testCase_getCopy_whenIsAtStartIndex() {
    // setup step 1: create array
    final var element1 = new VoidObject();
    final var element2 = new VoidObject();
    final var element3 = new VoidObject();
    final var element4 = new VoidObject();
    final var element5 = new VoidObject();
    final var element6 = new VoidObject();
    final var array = new Object[] { element1, element2, element3, element4, element5, element6 };

    // setup step 2: create testUnit
    final var testUnit = ArrayIterator.forArray(array);

    // execute
    final var result = testUnit.getCopy();

    // verify part 1: verify testUnit
    expect(testUnit.next()).is(element1);
    expect(testUnit.next()).is(element2);
    expect(testUnit.next()).is(element3);
    expect(testUnit.next()).is(element4);
    expect(testUnit.next()).is(element5);
    expect(testUnit.next()).is(element6);
    expect(testUnit.hasNext()).isFalse();

    // verify part 2: verify result
    expect(result.next()).is(element1);
    expect(result.next()).is(element2);
    expect(result.next()).is(element3);
    expect(result.next()).is(element4);
    expect(result.next()).is(element5);
    expect(result.next()).is(element6);
    expect(result.hasNext()).isFalse();
  }

  @Test
  void testCase_getCopy_whenIsAtIndexBetweenStartAndEnd() {
    // setup step 1: create array
    final var element1 = new VoidObject();
    final var element2 = new VoidObject();
    final var element3 = new VoidObject();
    final var element4 = new VoidObject();
    final var element5 = new VoidObject();
    final var element6 = new VoidObject();
    final var array = new Object[] { element1, element2, element3, element4, element5, element6 };

    // setup step 2: create testUnit
    final var testUnit = ArrayIterator.forArray(array);
    FlowController.forCount(3).run(testUnit::next);

    // execute
    final var result = testUnit.getCopy();

    // verify part 1: verify testUnit
    expect(testUnit.next()).is(element4);
    expect(testUnit.next()).is(element5);
    expect(testUnit.next()).is(element6);
    expect(testUnit.hasNext()).isFalse();

    // verify part 2:verify result
    expect(result.next()).is(element4);
    expect(result.next()).is(element5);
    expect(result.next()).is(element6);
    expect(result.hasNext()).isFalse();
  }

  @Test
  void testCase_getCopy_whenIsAtEndIndex() {
    // setup step 1: create array
    final var element1 = new VoidObject();
    final var element2 = new VoidObject();
    final var element3 = new VoidObject();
    final var element4 = new VoidObject();
    final var element5 = new VoidObject();
    final var element6 = new VoidObject();
    final var array = new Object[] { element1, element2, element3, element4, element5, element6 };

    // setup step 2: Creates testUnit
    final var testUnit = ArrayIterator.forArray(array);
    FlowController.forCount(6).run(testUnit::next);

    // execute
    final var result = testUnit.getCopy();

    // verify part 1: verify testUnit
    expect(testUnit.hasNext()).isFalse();

    // verify part 2:verify result
    expect(result.hasNext()).isFalse();
  }
}
