/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.commontype.arraytool;

import org.junit.jupiter.api.Test;

import ch.nolix.base.commontype.arraytool.ArraySorter;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.base.util.FunctionService;

/**
 * @author Silvan Wyss
 */
final class ArraySorterTest extends StandardTest {
  @Test
  final void testCase_sortArray_whenGivenArrayIsEmpty() {
    // setup
    final var array = new String[] {};

    // execute & verify
    expectRunning(() -> ArraySorter.sortArray(array, String::length)).doesNotThrowException();
  }

  @Test
  final void testCase_sortArray_1A() {
    // setup
    final var array = new String[] { "x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx" };

    // execute
    ArraySorter.sortArray(array, String::length);

    // verify
    expect(array[0]).isEqualTo("x");
    expect(array[1]).isEqualTo("xx");
    expect(array[2]).isEqualTo("xxx");
    expect(array[3]).isEqualTo("xxxx");
    expect(array[4]).isEqualTo("xxxxx");
    expect(array[5]).isEqualTo("xxxxxx");
  }

  @Test
  final void testCase_sortArray_1B() {
    // setup
    final var array = new String[] { "xxxxxx", "xxxxx", "xxxx", "xxx", "xx", "x" };

    // execute
    ArraySorter.sortArray(array, String::length);

    // verify
    expect(array[0]).isEqualTo("x");
    expect(array[1]).isEqualTo("xx");
    expect(array[2]).isEqualTo("xxx");
    expect(array[3]).isEqualTo("xxxx");
    expect(array[4]).isEqualTo("xxxxx");
    expect(array[5]).isEqualTo("xxxxxx");
  }

  @Test
  final void testCase_sortArray_2() {
    // setup
    final var array = //
    new String[] {
    "python",
    "elephant",
    "zebra",
    "lion",
    "shark",
    "jaguar",
    "rhino",
    "crocodile",
    "flamingo",
    "tiger" };

    // execute
    ArraySorter.sortArray(array, FunctionService::getSelf);

    // verify
    expect(array[0]).isEqualTo("crocodile");
    expect(array[1]).isEqualTo("elephant");
    expect(array[2]).isEqualTo("flamingo");
    expect(array[3]).isEqualTo("jaguar");
    expect(array[4]).isEqualTo("lion");
    expect(array[5]).isEqualTo("python");
    expect(array[6]).isEqualTo("rhino");
    expect(array[7]).isEqualTo("shark");
    expect(array[8]).isEqualTo("tiger");
    expect(array[9]).isEqualTo("zebra");
  }
}
