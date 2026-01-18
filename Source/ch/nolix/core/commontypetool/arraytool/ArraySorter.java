/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.commontypetool.arraytool;

import java.util.function.Function;

import ch.nolix.core.errorcontrol.validator.Validator;

/**
 * @author Silvan Wyss
 */
public final class ArraySorter {
  private ArraySorter() {
  }

  public static <E, C extends Comparable<C>> void sortArray(
    final E[] array,
    final int oneBasedEndIndex,
    final Function<E, C> comparableMapper) {
    Validator.assertThat(oneBasedEndIndex).thatIsNamed("one-based end index").isBetween(1, array.length);

    final var zeroBasedEndIndex = oneBasedEndIndex - 1;
    final var comparableArray = createComparableArray(array, oneBasedEndIndex, comparableMapper);

    @SuppressWarnings("unchecked")
    final var workElementArray = (E[]) new Object[oneBasedEndIndex];

    @SuppressWarnings("unchecked")
    final var workComparableArray = (C[]) new Comparable[oneBasedEndIndex];

    sortSection(array, comparableArray, 0, zeroBasedEndIndex, workElementArray, workComparableArray);
  }

  private static <E, C extends Comparable<C>> C[] createComparableArray(
    final E[] array,
    final int oneBasedEndIndex,
    final Function<E, C> comparableMapper) {
    @SuppressWarnings("unchecked")
    final var comparableArray = (C[]) new Comparable[oneBasedEndIndex];

    for (var i = 0; i < oneBasedEndIndex; i++) {
      comparableArray[i] = comparableMapper.apply(array[i]);
    }

    return comparableArray;
  }

  private static <E, C extends Comparable<C>> void sortSection(
    final E[] elementArray,
    final C[] comparableArray,
    final int zeroBasedBeginIndex,
    final int zeroBasedEndIndex,
    final E[] workElementArray,
    final C[] workComparableArray) {
    final var elementToProcessCount = zeroBasedEndIndex - zeroBasedBeginIndex + 1;

    switch (elementToProcessCount) {
      case 0, 1:
        break;
      case 2:

        if (comparableArray[zeroBasedBeginIndex].compareTo(comparableArray[zeroBasedEndIndex]) > 0) {
          swapElements(elementArray, zeroBasedBeginIndex, zeroBasedEndIndex);
          swapElements(comparableArray, zeroBasedBeginIndex, zeroBasedEndIndex);
        }

        break;
      default: //NOSONAR: The implementation of the case is as simple as possible.
        sortSectionWhenContainsMoreThanTwoElements(
          elementArray,
          comparableArray,
          zeroBasedBeginIndex,
          zeroBasedEndIndex,
          workElementArray,
          workComparableArray);
    }
  }

  private static void swapElements(final Object[] array, final int zeroBasedBeginIndex,
    final int zeroBasedEndIndex) {
    final var leftElement = array[zeroBasedBeginIndex];

    array[zeroBasedBeginIndex] = array[zeroBasedEndIndex];
    array[zeroBasedEndIndex] = leftElement;
  }

  private static <E, C extends Comparable<C>> void sortSectionWhenContainsMoreThanTwoElements(
    final E[] elementArray,
    final C[] comparableArray,
    final int zeroBasedBeginIndex,
    final int zeroBasedEndIndex,
    final E[] workElementArray,
    final C[] workComparableArray) {
    final var elementToProcessCount = zeroBasedEndIndex - zeroBasedBeginIndex + 1;
    final var leftSectionZeroBasedEndIndex = zeroBasedBeginIndex + (elementToProcessCount / 2);
    final var rightSectionZeroBasedStartIndex = leftSectionZeroBasedEndIndex + 1;

    sortSection(
      elementArray,
      comparableArray,
      zeroBasedBeginIndex,
      leftSectionZeroBasedEndIndex,
      workElementArray,
      workComparableArray);

    sortSection(
      elementArray,
      comparableArray,
      rightSectionZeroBasedStartIndex,
      zeroBasedEndIndex,
      workElementArray,
      workComparableArray);

    mergeSortedSections(
      elementArray,
      comparableArray,
      zeroBasedBeginIndex,
      leftSectionZeroBasedEndIndex,
      zeroBasedEndIndex,
      workElementArray,
      workComparableArray);
  }

  private static <E, C extends Comparable<C>> void mergeSortedSections(
    final E[] elementArray,
    final C[] comparableArray,
    final int leftSectionZeroBasedBeginIndex,
    final int leftSectionZeroBasedEndIndex,
    final int rightSectionZeroBasedEndIndex,
    final E[] workElementArray,
    final C[] workComparableArray) {
    var leftSectionZeroBasedIndex = leftSectionZeroBasedBeginIndex;
    var rightSectionZeroBasedIndex = leftSectionZeroBasedEndIndex + 1;
    var movedElement = false;

    var i = 0;
    while (leftSectionZeroBasedIndex <= leftSectionZeroBasedEndIndex
    && rightSectionZeroBasedIndex <= rightSectionZeroBasedEndIndex) {
      if (comparableArray[leftSectionZeroBasedIndex].compareTo(comparableArray[rightSectionZeroBasedIndex]) > 0) {
        workElementArray[i] = elementArray[rightSectionZeroBasedIndex];
        workComparableArray[i] = comparableArray[rightSectionZeroBasedIndex];
        movedElement = true;
        rightSectionZeroBasedIndex++;
      } else {
        workElementArray[i] = elementArray[leftSectionZeroBasedIndex];
        workComparableArray[i] = comparableArray[leftSectionZeroBasedIndex];
        leftSectionZeroBasedIndex++;
      }

      i++;
    }

    if (movedElement) {
      while (leftSectionZeroBasedIndex <= leftSectionZeroBasedEndIndex) {
        workElementArray[i] = elementArray[leftSectionZeroBasedIndex];
        workComparableArray[i] = comparableArray[leftSectionZeroBasedIndex];
        leftSectionZeroBasedIndex++;
        i++;
      }

      while (rightSectionZeroBasedIndex <= rightSectionZeroBasedEndIndex) {
        workElementArray[i] = elementArray[rightSectionZeroBasedIndex];
        workComparableArray[i] = comparableArray[rightSectionZeroBasedIndex];
        rightSectionZeroBasedIndex++;
      }

      System.arraycopy(workElementArray, 0, elementArray, leftSectionZeroBasedBeginIndex, i);
      System.arraycopy(workComparableArray, 0, comparableArray, leftSectionZeroBasedBeginIndex, i);
    }
  }
}
