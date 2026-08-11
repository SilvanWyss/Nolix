/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.datastructure.extendediterable;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.base.datastructure.arraylist.ArrayList;
import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.base.testing.testutil.VoidObject;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.programcontrol.function.FunctionService;

/**
 * @author Silvan Wyss
 */
public abstract class ExtendedIterableTest extends StandardTest {
  @Test
  final void testCase_contains_whenContainsTheGivenElement() {
    // setup
    final var antelope = "antelope";
    final var baboon = "baboon";
    final var elephant = "elephant";
    final var lion = "lion";
    final var testUnit = createContainerWithElements(antelope, baboon, elephant, lion);

    // execute
    final var result = testUnit.contains(lion);

    // verify
    expect(result).isTrue();
  }

  @Test
  final void testCase_contains_whenDoesNotContainTheGivenElement() {
    // setup
    final var antelope = "antelope";
    final var baboon = "baboon";
    final var elephant = "elephant";
    final var lion = "lion";
    final var testUnit = createContainerWithElements(antelope, baboon, elephant);

    // execute
    final var result = testUnit.contains(lion);

    // verify
    expect(result).isFalse();
  }

  @Test
  final void testCase_containsAll_forIterable_whenContainsNoneOfTheGivenElements() {
    // setup
    final var antelope = "antelope";
    final var baboon = "baboon";
    final var elephant = "elephant";
    final var lion = "lion";
    final var testUnit = createContainerWithElements(antelope);
    final var list = ImmutableList.withElements(baboon, elephant, lion);

    // execute
    final var result = testUnit.containsAll(list);

    // verify
    expect(result).isFalse();
  }

  @Test
  final void testCase_containsAll_forVarargs_whenContainsNoneOfTheGivenElements() {
    // setup
    final var antelope = "antelope";
    final var baboon = "baboon";
    final var elephant = "elephant";
    final var lion = "lion";
    final var testUnit = createContainerWithElements(antelope);

    // execute
    final var result = testUnit.containsAll(baboon, elephant, lion);

    // verify
    expect(result).isFalse();
  }

  @Test
  final void testCase_containsAll_forIterable_whenContainsOnlySomeOfTheGivenElements() {
    // setup
    final var antelope = "antelope";
    final var baboon = "baboon";
    final var elephant = "elephant";
    final var lion = "lion";
    final var testUnit = createContainerWithElements(antelope, baboon, elephant);
    final var list = ImmutableList.withElements(antelope, baboon, elephant, lion);

    // execute
    final var result = testUnit.containsAll(list);

    // verify
    expect(result).isFalse();
  }

  @Test
  final void testCase_containsAll_forVarargs_whenContainsOnlySomeOfTheGivenElements() {
    // setup
    final var antelope = "antelope";
    final var baboon = "baboon";
    final var elephant = "elephant";
    final var lion = "lion";
    final var testUnit = createContainerWithElements(antelope, baboon, elephant);

    // execute
    final var result = testUnit.containsAll(antelope, baboon, elephant, lion);

    // verify
    expect(result).isFalse();
  }

  @Test
  final void testCase_containsAll_forIterable_whenContainsAllTheGivenElements() {
    // setup
    final var antelope = "antelope";
    final var baboon = "baboon";
    final var elephant = "elephant";
    final var lion = "lion";
    final var testUnit = createContainerWithElements(antelope, baboon, elephant, lion);
    final var list = ImmutableList.withElements(antelope, baboon, elephant);

    // execute
    final var result = testUnit.containsAll(list);

    // verify
    expect(result).isTrue();
  }

  @Test
  final void testCase_containsAll_forVarargs_whenContainsAllTheGivenElements() {
    // setup
    final var antelope = "antelope";
    final var baboon = "baboon";
    final var elephant = "elephant";
    final var lion = "lion";
    final var testUnit = createContainerWithElements(antelope, baboon, elephant, lion);

    // execute
    final var result = testUnit.containsAll(antelope, baboon, elephant);

    // verify
    expect(result).isTrue();
  }

  @Test
  final void testCase_containsAnyOf_whenIsEmpty() {
    // setup
    final var element1 = "x";
    final var element2 = "xx";
    final var testUnit = createEmptyContainerForType(String.class);

    // execute
    final var result = testUnit.containsAny(element1, element2);

    // verify
    expect(result).isFalse();
  }

  @Test
  final void testCase_containsAnyOf_whenContainsGivenElementsAndMore() {
    // setup
    final var element1 = "x";
    final var element2 = "xx";
    final var element3 = "xxx";
    final var element4 = "xxxx";
    final var testUnit = createContainerWithElements(element1, element2, element3, element4);

    // execute
    final var result = testUnit.containsAny(element1, element2, element3, element4);

    // verify
    expect(result).isTrue();
  }

  @Test
  final void testCase_containsAnyOf_whenContainsOtherElementsOnly() {
    // setup
    final var element1 = "x";
    final var element2 = "xx";
    final var element3 = "xxx";
    final var element4 = "xxxx";
    final var testUnit = createContainerWithElements(element1, element2);

    // execute
    final var result = testUnit.containsAny(element3, element4);

    // verify
    expect(result).isFalse();
  }

  @Test
  final void testCase_containsExactlyInSameOrder_whenIsEmptyAndGivenContainerIsEmpty() {
    // setup
    final var testUnit = createEmptyContainerForType(String.class);
    final var container = createEmptyContainerForType(String.class);

    // execute
    final var result = testUnit.containsExactlyInSameOrder(container);

    // verify
    expect(result).isTrue();
  }

  @Test
  final void //
  testCase_containsExactlyInSameOrder_whenContainsElementsAndGivenContainerContainsSameElementsInSameOrder() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
    final var container = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    // execute
    final var result = testUnit.containsExactlyInSameOrder(container);

    // verify
    expect(result).isTrue();
  }

  @Test
  final void //
  testCase_containsExactlyInSameOrder_whenContainsElementsAndGivenContainerContainsSameElementsInOtherOrder() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
    final var container = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxxx", "xxxxx");

    // execute
    final var result = testUnit.containsExactlyInSameOrder(container);

    // verify
    expect(result).isFalse();
  }

  @Test
  final void testCase_containsOnce_whenDoesNotContainTheGivenElement() {
    // setup
    final var antelope = "antelope";
    final var baboon = "baboon";
    final var elephant = "elephant";
    final var lion = "lion";
    final var testUnit = createContainerWithElements(antelope, baboon, elephant);

    // execute
    final var result = testUnit.containsOnce(lion);

    // verify
    expect(result).isFalse();
  }

  @Test
  final void testCase_containsOnce_whenContainsTheGivenElementOnce() {
    // setup
    final var antelope = "antelope";
    final var baboon = "baboon";
    final var elephant = "elephant";
    final var lion = "lion";
    final var testUnit = createContainerWithElements(antelope, baboon, elephant, lion);

    // execute
    final var result = testUnit.containsOnce(lion);

    // verify
    expect(result).isTrue();
  }

  @Test
  final void testCase_containsOnce_whenContainsTheGivenElementSeveralTimes() {
    // setup
    final var antelope = "antelope";
    final var baboon = "baboon";
    final var elephant = "elephant";
    final var lion = "lion";
    final var testUnit = createContainerWithElements(antelope, baboon, elephant, lion, lion);

    // execute
    final var result = testUnit.containsOnce(lion);

    // verify
    expect(result).isFalse();
  }

  @Test
  final void testCase_containsOne_1A() {
    // setup
    final var testUnit = createEmptyContainerForType(String.class);

    // execute
    final var result = testUnit.containsOne();

    // verify
    expect(result).isFalse();
  }

  @Test
  final void testCase_containsOne_1B() {
    // setup
    final var testUnit = createContainerWithElements("x");

    // execute
    final var result = testUnit.containsOne();

    // verify
    expect(result).isTrue();
  }

  @Test
  final void testCase_containsOne_1C() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx");

    // execute
    final var result = testUnit.containsOne();

    // verify
    expect(result).isFalse();
  }

  @Test
  final void testCase_containsOne_ElementTakerBooleanGetter1A() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx", "xx", "xx", "xx", "xx");

    // execute
    final var result = testUnit.containsOneMatching(e -> e.equals("x"));

    // verify
    expect(result).isTrue();
  }

  @Test
  final void testCase_containsOne_ElementTakerBooleanGetter1B() {
    // setup
    final var testUnit = createContainerWithElements("x", "x", "xx", "xx", "xx", "xx");

    // execute
    final var result = testUnit.containsOneMatching(e -> e.equals("x"));

    // verify
    expect(result).isFalse();
  }

  @Test
  final void testCase_forEach() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");
    final var list = ArrayList.createEmpty();

    // execute
    testUnit.forEach(list::addAtEnd);

    // verify
    expect(list.getCount()).isEqualTo(6);
    for (var i = 1; i <= 6; i++) {
      expect(testUnit.getStoredAtOneBasedIndex(i)).isEqualTo(list.getStoredAtOneBasedIndex(i));
    }
  }

  @Test
  final void testCase_from() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    // execute
    final var result = testUnit.getViewFromOneBasedStartIndex(4);

    // verify
    expect(result.getCount()).isEqualTo(3);
    expect(result.getStoredAtOneBasedIndex(1)).isEqualTo("xxxx");
    expect(result.getStoredAtOneBasedIndex(2)).isEqualTo("xxxxx");
    expect(result.getStoredAtOneBasedIndex(3)).isEqualTo("xxxxxx");
  }

  @Test
  final void testCase_getAverage_whenIsEmpty() {
    // setup
    final var testUnit = createEmptyContainerForType(Double.class);

    // execute & verify
    expectRunning(() -> testUnit.getAverage(FunctionService::getSelf))
      .throwsException()
      .ofType(EmptyArgumentException.class)
      .withMessage("The given " + testUnit.getClass().getSimpleName() + " is empty.");
  }

  @Test
  final void testCase_getAverage_whenContainsAny() {
    // setup
    final var testUnit = createContainerWithElements(5.0, 10.0, 15.0, 20.0, 25.0, 30.0);

    // execute
    final var result = testUnit.getAverage(FunctionService::getSelf);

    // verify
    expect(result).isEqualTo(17.5);
  }

  @Test
  final void testCase_getAverageOrZero_whenIsEmpty() {
    // setup
    final var testUnit = createEmptyContainerForType(Double.class);

    // execute
    final var result = testUnit.getAverageOrZero(FunctionService::getSelf);

    // verify
    expect(result).isEqualTo(0.0);
  }

  @Test
  final void testCase_getAverageOrZero_whenContainsAny() {
    // setup
    final var testUnit = createContainerWithElements(5.0, 10.0, 15.0, 20.0, 25.0, 30.0);

    // execute
    final var result = testUnit.getAverageOrZero(FunctionService::getSelf);

    // verify
    expect(result).isEqualTo(17.5);
  }

  @ParameterizedTest
  @CsvSource({
  "0, 10",
  "1, 8",
  "2, 6",
  "3, 4",
  "4, 2",
  "5, 0",
  "6, 0",
  "7, 0",
  "8, 0",
  })
  final void testCase_getCount(final int minLength, final int expectedCount) {
    // setup
    final var testUnit = createContainerWithElements("", "", "x", "x", "xx", "xx", "xxx", "xxx", "xxxx", "xxxx");

    // execute
    final var result = testUnit.getCount(e -> e.length() >= minLength);

    // verify
    expect(result).isEqualTo(expectedCount);
  }

  @Test
  final void testCase_getElementCount() {
    // setup
    final var testUnit = createContainerWithElements("x", "x", "x", "x", "x", "x");

    // execute & verify
    expect(testUnit.getCount()).isEqualTo(6);
  }

  @Test
  final void testCase_getElementCount_whenLinkedListIsEmpty() {
    // setup
    final var testUnit = createEmptyContainerForType(String.class);

    // execute & verify
    expect(testUnit.getCount()).isEqualTo(0);
  }

  @Test
  final void testCase_getOneBasedIndexOfFirst_whenIsEmpty() {
    // setup
    final var testUnit = createEmptyContainerForType(String.class);

    // execute & verify
    expectRunning(() -> testUnit.getOneBasedIndexOfFirst(e -> e.startsWith("x")))
      .throwsException()
      .ofType(ArgumentDoesNotContainElementException.class);
  }

  @Test
  final void testCase_getOneBasedIndexOfFirst_whenContainsAMatchingElement() {
    // setup
    final var testUnit = createContainerWithElements("wx", "xx", "yx", "zx");

    // execute
    final var result = testUnit.getOneBasedIndexOfFirst(e -> e.startsWith("y"));

    // verify
    expect(result).isEqualTo(3);
  }

  @Test
  final void testCase_getMax_whenIsEmptyAndTheGivenComparableMapperMapsToIntegers() {
    // setup
    final var testUnit = createEmptyContainerForType(String.class);

    // execute
    expectRunning(() -> testUnit.getMax(String::length))
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  @Test
  final void testCase_getMax_whenContainsSomeAndGivenNormIsDouble() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    // execute
    final var result = testUnit.getMax(e -> 1.0 / e.length());

    // verify
    expect(result).isEqualTo(1.0);
  }

  @Test
  final void testCase_getMax_whenContainsSomeAndGivenNormIsInteger() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    // execute
    final var result = testUnit.getMax(String::length);

    // verify
    expect(result).isEqualTo(6);
  }

  @Test
  final void testCase_getMaxOrZero_whenIsEmptyContainerForBigDecimals() {
    // setup
    final var testUnit = createEmptyContainerForType(BigDecimal.class);

    // execute
    final var result = testUnit.getMaxOrZero(FunctionService::getSelf);

    // verify
    expect(result).isEqualTo(0.0);
  }

  @Test
  final void testCase_getMaxOrZero_whenContainsBigDecimals() {
    // setup
    final var testUnit = createContainerWithElements(
      BigDecimal.valueOf(10.0),
      BigDecimal.valueOf(10.0),
      BigDecimal.valueOf(11.5),
      BigDecimal.valueOf(9.5));

    // execute
    final var result = testUnit.getMaxOrZero(FunctionService::getSelf);

    // verify
    expect(result).isEqualTo(11.5);
  }

  @Test
  final void testCase_getMedian_whenIsEmpty() {
    // setup
    final var testUnit = createEmptyContainerForType(Integer.class);

    // execute & verify
    expectRunning(() -> testUnit.getMedian(FunctionService::getSelf))
      .throwsException()
      .ofType(EmptyArgumentException.class);
  }

  @Test
  final void testCase_getMedian_whenContainsAny() {
    // setup
    final var testUnit = createContainerWithElements(10, 1, 9, 2, 8, 3, 4);

    // execute
    final var result = testUnit.getMedian(FunctionService::getSelf);

    // verify
    expect(result).isEqualTo(4.0);
  }

  @Test
  final void testCase_getMedianOrZero_whenIsEmpty() {
    // setup
    final var testUnit = createEmptyContainerForType(Integer.class);

    // execute
    final var result = testUnit.getMedianOrZero(FunctionService::getSelf);

    // verify
    expect(result).isEqualTo(0.0);
  }

  @Test
  final void testCase_getMedianOrZero_whenContainsAny() {
    // setup
    final var testUnit = createContainerWithElements(10, 1, 9, 2, 8, 3, 4);

    // execute
    final var result = testUnit.getMedianOrZero(FunctionService::getSelf);

    // verify
    expect(result).isEqualTo(4.0);
  }

  @Test
  final void testCase_getMin_whenIsEmptyAndTheGivenComparableMapperMapstoIntegers() {
    // setup
    final var testUnit = createEmptyContainerForType(String.class);

    // execute
    expectRunning(() -> testUnit.getMin(String::length)).throwsException().ofType(InvalidArgumentException.class);
  }

  @Test
  final void testCase_getMin_whenContainsSomeAndGivenNormIsDouble() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx");

    // execute
    final var result = testUnit.getMin(e -> 1.0 / e.length());

    // verify
    expect(result).isEqualTo(0.2);
  }

  @Test
  final void testCase_getMin_whenContainsSomeAndGivenNormIsInteger() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    // execute
    final var result = testUnit.getMin(String::length);

    // verify
    expect(result).isEqualTo(1);
  }

  @Test
  final void testCase_getMinOrZero_whenIsEmptyContainerForBigDecimals() {
    // setup
    final var testUnit = createEmptyContainerForType(BigDecimal.class);

    // execute
    final var result = testUnit.getMinOrZero(FunctionService::getSelf);

    // verify
    expect(result).isEqualTo(0.0);
  }

  @Test
  final void testCase_getMinOrZero_whenContainsBigDecimals() {
    // setup
    final var testUnit = createContainerWithElements(
      BigDecimal.valueOf(10.0),
      BigDecimal.valueOf(10.0),
      BigDecimal.valueOf(11.5),
      BigDecimal.valueOf(9.5));

    // execute
    final var result = testUnit.getMinOrZero(FunctionService::getSelf);

    // verify
    expect(result).isEqualTo(9.5);
  }

  @Test
  void testCase_getOptionalStoredFirst_whenIsEmpty() {
    // setup
    final var testUnit = createEmptyContainerForType(String.class);

    // execute
    final var result = testUnit.getOptionalStoredFirst();

    // verify
    expect(result.isEmpty()).isTrue();
  }

  @Test
  void testCase_getOptionalStoredFirst_whenContainsSeveralElements() {
    // setup
    final var elephant = "elephant";
    final var lion = "lion";
    final var rhino = "rhino";
    final var zebra = "zebra";
    final var testUnit = createContainerWithElements(elephant, lion, rhino, zebra);

    // execute
    final var result = testUnit.getOptionalStoredFirst();

    // verify
    expect(result.orElseThrow()).is(elephant);
  }

  @Test
  final void testCase_getStoredByMax_whenIsEmptyAndGivenNormIsInteger() {
    // setup
    final var testUnit = createEmptyContainerForType(String.class);

    // execute
    expectRunning(() -> testUnit.getStoredByMax(String::length))
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  @Test
  final void testCase_getStoredByMax_whenContainsSomeAndGivenNormIsDouble() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    // execute
    final var result = testUnit.getStoredByMax(e -> 1.0 / e.length());

    // verify
    expect(result).isEqualTo("x");
  }

  @Test
  final void testCase_getStoredByMax_whenContainsSomeAndGivenNormIsInteger() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    // execute
    final var result = testUnit.getStoredByMax(String::length);

    // verify
    expect(result).isEqualTo("xxxxxx");
  }

  @Test
  final void testCase_getStoredByMin_whenIsEmptyAndGivenNormIsInteger() {
    // setup
    final var testUnit = createEmptyContainerForType(String.class);

    // execute
    expectRunning(() -> testUnit.getStoredByMin(String::length))
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  @Test
  final void testCase_getStoredByMin_whenContainsSomeAndGivenNormIsDouble() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    // execute
    final var result = testUnit.getStoredByMin(e -> 1.0 / e.length());

    // verify
    expect(result).isEqualTo("xxxxxx");
  }

  @Test
  final void testCase_getStoredByMin_whenContainsSomeAndGivenNormIsInteger() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    // execute
    final var result = testUnit.getStoredByMin(String::length);

    // verify
    expect(result).isEqualTo("x");
  }

  @Test
  final void testCase_getStoredFirst() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    // execute
    final var result = testUnit.getStoredFirstNonNull();

    // verify
    expect(result).isEqualTo("x");
  }

  @Test
  final void testCase_getStoredFirst_whenLinkedListIsEmpty() {
    // setup
    final var testUnit = createEmptyContainerForType(String.class);

    // execute & verify
    expectRunning(testUnit::getStoredFirstNonNull)
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage("The given " + testUnit.getClass().getSimpleName() + " does not contain a non-null element.");
  }

  @Test
  final void testCase_getStoredGroups_whenIsEmpty() {
    // setup
    final var testUnit = createEmptyContainerForType(String.class);

    // execute
    final var result = testUnit.getStoredInGroups(String::length);

    // verify
    expect(result).isEmpty();
  }

  @Test
  final void testCase_getStoredGroups_1A() {
    // setup
    final var testUnit = createContainerWithElements("x", "y", "x", "y", "x", "y");

    // execute
    final var result = testUnit.getStoredInGroups(String::length);

    // verify
    expect(result.getCount()).isEqualTo(1);
    expect(result.getStoredSingle()).containsExactlyEqualing("x", "y", "x", "y", "x", "y");
  }

  @Test
  final void testCase_getStoredGroups_1B() {
    // setup
    final var testUnit = createContainerWithElements("x", "y", "xx", "yy", "xxx", "yyy");

    // execute
    final var result = testUnit.getStoredInGroups(String::length);

    // verify
    expect(result.getCount()).isEqualTo(3);
    expect(result.getStoredAtOneBasedIndex(1)).containsExactlyEqualing("x", "y");
    expect(result.getStoredAtOneBasedIndex(2)).containsExactlyEqualing("xx", "yy");
    expect(result.getStoredAtOneBasedIndex(3)).containsExactlyEqualing("xxx", "yyy");
  }

  @Test
  void testCase_getStoredLast_whenIsEmpty() {
    // setup
    final var testUnit = createEmptyContainerForType(String.class);

    // execute & verify
    expectRunning(testUnit::getStoredLast).throwsException();
  }

  @Test
  void testCase_getStoredLast_whenContainsSeveralElements() {
    // setup
    final var elephant = "elephant";
    final var lion = "lion";
    final var rhino = "rhino";
    final var zebra = "zebra";
    final var testUnit = createContainerWithElements(elephant, lion, rhino, zebra);

    // execute
    final var result = testUnit.getStoredLast();

    // verify
    expect(result).is(zebra);
  }

  @Test
  final void testCase_getStoredOne_whenDoesNotContainAMatchingElement() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    // execute & verify
    expectRunning(() -> testUnit.getStoredSingle(e -> e.length() == 7))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage(
        "The given "
        + testUnit.getClass().getSimpleName()
        + " 'x,xx,xxx,xxxx,xxxxx,xxxxxx' does not contain an element the given selector selects.");
  }

  @Test
  final void testCase_getStoredOne_whenContainsOneMatchingElement() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    // execute
    final var result = testUnit.getStoredSingle(e -> e.length() == 3);

    // verify
    expect(result).isEqualTo("xxx");
  }

  @Test
  final void testCase_getStoredOne_whenContainsSeveralMatchingElements() {
    // setup
    final var testUnit = createContainerWithElements("x", "y", "xx", "yy", "xxx", "yyy");

    // execute & verify
    expectRunning(() -> testUnit.getStoredSingle(e -> e.length() == 3))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage(
        "The given "
        + testUnit.getClass().getSimpleName()
        + " 'x,y,xx,yy,xxx,yyy' contains several elements the given selector selects.");
  }

  @Test
  final void testCase_getStoredSelected_1A() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    // execute
    final var result = testUnit.getStoredSelected(e -> e.length() < 4);

    // verify
    expect(result.getCount()).isEqualTo(3);
    expect(result.getStoredAtOneBasedIndex(1)).isEqualTo("x");
    expect(result.getStoredAtOneBasedIndex(2)).isEqualTo("xx");
    expect(result.getStoredAtOneBasedIndex(3)).isEqualTo("xxx");
  }

  @Test
  final void testCase_getStoredSelected_1B() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    // execute
    final var result = testUnit.getStoredSelected(e -> e.length() > 6);

    // verify
    expect(result.isEmpty()).isTrue();
  }

  @Test
  final void testCase_getSumByInt_whenIsEmpty() {
    // setup
    final var testUnit = createEmptyContainerForType(String.class);

    // execute
    final var result = testUnit.getSum(String::length);

    // verify
    expect(result.intValue()).isEqualTo(0);
  }

  @Test
  final void testCase_getSum_whenContainsAny() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    // execute
    final var result = testUnit.getSum(String::length);

    // verify
    expect(result.intValue()).isEqualTo(21);
  }

  @Test
  final void testCase_getVariance() {
    // setup
    final var testUnit = createContainerWithElements(0.0, 0.0, 0.5, 1.0, 1.0);

    // execute
    final var result = testUnit.getVariance(FunctionService::getSelf);

    // verify
    expect(result).isEqualTo(0.2);
  }

  @Test
  final void testCase_getViewWithoutFirst_whenIsEmpty() {
    // setup
    final var testUnit = createEmptyContainerForType(String.class);

    // execute & verify
    expectRunning(testUnit::getViewWithoutFirst).throwsException();
  }

  @Test
  final void testCase_getViewWithoutFirst_whenContainsSeveral() {
    // setup
    final var antelope = "antelope";
    final var baboon = "baboon";
    final var elephant = "elephant";
    final var lion = "lion";
    final var rhino = "rhino";
    final var zebra = "zebra";
    final var testUnit = createContainerWithElements(antelope, baboon, elephant, lion, rhino, zebra);

    // execute
    final var result = testUnit.getViewWithoutFirst();

    // verify
    expect(result).containsExactlyInSameOrder(baboon, elephant, lion, rhino, zebra);
  }

  @Test
  final void testCase_getViewWithoutLast_whenIsEmpty() {
    // setup
    final var testUnit = createEmptyContainerForType(String.class);

    // execute & verify
    expectRunning(testUnit::getViewWithoutLast).throwsException();
  }

  @Test
  final void testCase_getViewWithoutLast_whenContainsSeveral() {
    // setup
    final var antelope = "antelope";
    final var baboon = "baboon";
    final var elephant = "elephant";
    final var lion = "lion";
    final var rhino = "rhino";
    final var zebra = "zebra";
    final var testUnit = createContainerWithElements(antelope, baboon, elephant, lion, rhino, zebra);

    // execute
    final var result = testUnit.getViewWithoutLast();

    // verify
    expect(result).containsExactlyInSameOrder(antelope, baboon, elephant, lion, rhino);
  }

  @Test
  final void testCase_isView() {
    // setup
    final var testUnit = createEmptyContainerForType(VoidObject.class);

    // execute
    final var result = testUnit.isView();

    // verify
    expect(result).is(!testUnit.isMaterialized());
  }

  @Test
  final void testCase_toArray() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    // execute
    final var result = testUnit.toArray();

    // verify
    expect(result.length).isEqualTo(6);
    expect(result[0]).isEqualTo("x");
    expect(result[1]).isEqualTo("xx");
    expect(result[2]).isEqualTo("xxx");
    expect(result[3]).isEqualTo("xxxx");
    expect(result[4]).isEqualTo("xxxxx");
    expect(result[5]).isEqualTo("xxxxxx");
  }

  @Test
  final void testCase_toConcatenatedString_whenIsEmpty() {
    // setup
    final var testUnit = createEmptyContainerForType(String.class);

    // execute
    final var result = testUnit.toConcatenatedString();

    // verify
    expect(result).isEmpty();
  }

  @Test
  final void testCase_toConcatenatedString_whenContainsAny() {
    // setup
    final var testUnit = createContainerWithElements("x", "yy", "zzz", "pppp");

    // execute
    final var result = testUnit.toConcatenatedString();

    // verify
    expect(result).isEqualTo("xyyzzzpppp");
  }

  @Test
  final void testCase_toDoubleArray_whenIsEmpty() {
    // setup
    final var testUnit = createEmptyContainerForType(String.class);

    // execute
    final var result = testUnit.toDoubleArray(String::length);

    // verify
    expect(result.length).isEqualTo(0);
  }

  @Test
  final void testCase_toDoubleArray_whenContainsAny() {
    // setup
    final var testUnit = createContainerWithElements("x", "x", "xx", "xx", "xxx", "xxx");

    // execute
    final var result = testUnit.toDoubleArray(String::length);

    // verify
    expect(result.length).isEqualTo(6);
    expect(result[0]).isEqualTo(1.0);
    expect(result[1]).isEqualTo(1.0);
    expect(result[2]).isEqualTo(2.0);
    expect(result[3]).isEqualTo(2.0);
    expect(result[4]).isEqualTo(3.0);
    expect(result[5]).isEqualTo(3.0);
  }

  @Test
  final void testCase_toOrdered() {
    // setup
    final var testUnit = createContainerWithElements("xxxxxx", "xxxxx", "xxxx", "xxx", "xx", "x");

    // execute
    final var result = testUnit.toOrdered(String::length);

    // verify
    expect(result.getCount()).isEqualTo(6);
    expect(result.getStoredAtOneBasedIndex(1)).isEqualTo("x");
    expect(result.getStoredAtOneBasedIndex(2)).isEqualTo("xx");
    expect(result.getStoredAtOneBasedIndex(3)).isEqualTo("xxx");
    expect(result.getStoredAtOneBasedIndex(4)).isEqualTo("xxxx");
    expect(result.getStoredAtOneBasedIndex(5)).isEqualTo("xxxxx");
    expect(result.getStoredAtOneBasedIndex(6)).isEqualTo("xxxxxx");
  }

  @Test
  final void testCase_toIntArray() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    // execute
    final var result = testUnit.toIntArray(String::length);

    // verify
    expect(result.length).isEqualTo(6);
    expect(result[0]).isEqualTo(1);
    expect(result[1]).isEqualTo(2);
    expect(result[2]).isEqualTo(3);
    expect(result[3]).isEqualTo(4);
    expect(result[4]).isEqualTo(5);
    expect(result[5]).isEqualTo(6);
  }

  @Test
  final void testCase_toStringArray_whenIsEmpty() {
    // setup
    final var testUnit = createEmptyContainerForType(Integer.class);

    // execute
    final var result = testUnit.toStringArray();

    // verify
    expect(result.length).isEqualTo(0);
  }

  @Test
  final void testCase_toStringArray_whenContainsElements() {
    // setup
    final var testUnit = createContainerWithElements(10, 20, 30, 40);

    // execute
    final var result = testUnit.toStringArray();

    // verify
    expect(result.length).isEqualTo(4);
    expect(result[0]).isEqualTo("10");
    expect(result[1]).isEqualTo("20");
    expect(result[2]).isEqualTo("30");
    expect(result[3]).isEqualTo("40");
  }

  @Test
  final void testCase_toStrings() {
    // setup
    final var testUnit = createContainerWithElements(10, 20, 30, 40, 50, 60);

    // execute
    final var result = testUnit.toStrings();

    // verify
    expect(result.getCount()).isEqualTo(6);
    expect(result.getStoredAtOneBasedIndex(1)).isEqualTo("10");
    expect(result.getStoredAtOneBasedIndex(2)).isEqualTo("20");
    expect(result.getStoredAtOneBasedIndex(3)).isEqualTo("30");
    expect(result.getStoredAtOneBasedIndex(4)).isEqualTo("40");
    expect(result.getStoredAtOneBasedIndex(5)).isEqualTo("50");
    expect(result.getStoredAtOneBasedIndex(6)).isEqualTo("60");
  }

  @Test
  final void testCase_toStrings_whenContainerIsEmpty() {
    // setup
    final var testUnit = createEmptyContainerForType(Integer.class);

    // execute
    final var result = testUnit.toStrings();

    // verifys
    expect(result).isEmpty();
  }

  @Test
  final void testCase_until() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    // execute
    final var result = testUnit.getViewToOneBasedEndIndex(5);

    // verify
    expect(result.getCount()).isEqualTo(5);
    expect(result.getStoredAtOneBasedIndex(1)).isEqualTo("x");
    expect(result.getStoredAtOneBasedIndex(2)).isEqualTo("xx");
    expect(result.getStoredAtOneBasedIndex(3)).isEqualTo("xxx");
    expect(result.getStoredAtOneBasedIndex(4)).isEqualTo("xxxx");
    expect(result.getStoredAtOneBasedIndex(5)).isEqualTo("xxxxx");
  }

  @Test
  final void testCase_withElements() {
    // execute
    final var result = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    // verify
    expect(result).contains(s -> s.equals("x"));
    expect(result).contains(s -> s.equals("xx"));
    expect(result).contains(s -> s.equals("xxx"));
    expect(result).contains(s -> s.equals("xxxx"));
    expect(result).contains(s -> s.equals("xxxxx"));
    expect(result).contains(s -> s.equals("xxxxxx"));
  }

  @Test
  final void testCase_withoutFirst() {
    // setup
    final var testUnit = createContainerWithElements("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    // execute
    final var result = testUnit.getViewWithoutFirst();

    // verify
    expect(result.getCount()).isEqualTo(5);
    expect(result.getStoredAtOneBasedIndex(1)).isEqualTo("xx");
    expect(result.getStoredAtOneBasedIndex(2)).isEqualTo("xxx");
    expect(result.getStoredAtOneBasedIndex(3)).isEqualTo("xxxx");
    expect(result.getStoredAtOneBasedIndex(4)).isEqualTo("xxxxx");
    expect(result.getStoredAtOneBasedIndex(5)).isEqualTo("xxxxxx");
  }

  protected abstract <E> ExtendedIterable<E> createContainerWithElements(@SuppressWarnings("unchecked") E... elements);

  protected abstract <E> ExtendedIterable<E> createEmptyContainerForType(Class<E> type);
}
