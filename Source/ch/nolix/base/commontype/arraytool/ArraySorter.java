/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontype.arraytool;

import java.util.function.Function;

/**
 * Of the {@link ArraySorter} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class ArraySorter {
  private ArraySorter() {
  }

  public static <E, C extends Comparable<C>> void sortArray(final E[] array, final Function<E, C> comparableMapper) {
    final int legth = array.length;
    final var zeroBasedEndIndex = legth - 1;
    final var comparableArray = createComparableArray(array, legth, comparableMapper);

    @SuppressWarnings("unchecked")
    final var workElementArray = (E[]) new Object[legth];

    @SuppressWarnings("unchecked")
    final var workComparableArray = (C[]) new Comparable[legth];

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
    final E[] elementsArray,
    final C[] comparablesArray,
    final int leftSectionStartIndex,
    final int leftSectionEndIndex,
    final int rightSectionEndIndex,
    final E[] elementsWorkArray,
    final C[] comparablesWorkArray) {
    final var elementsToProcessCount = rightSectionEndIndex - leftSectionStartIndex + 1;
    var movedElements = false;
    var continueComparing = true;
    var leftSectionIndex = leftSectionStartIndex;
    var rightSectionIndex = leftSectionEndIndex + 1;

    for (var i = 0; i < elementsToProcessCount; i++) {
      if (continueComparing && comparablesArray[leftSectionIndex].compareTo(comparablesArray[rightSectionIndex]) > 0) {
        elementsWorkArray[i] = elementsArray[rightSectionIndex];
        comparablesWorkArray[i] = comparablesArray[rightSectionIndex];
        rightSectionIndex++;
        movedElements = true;
      } else {
        elementsWorkArray[i] = elementsArray[leftSectionIndex];
        comparablesWorkArray[i] = comparablesArray[leftSectionIndex];
        leftSectionIndex++;
      }

      if (continueComparing && (leftSectionIndex > leftSectionEndIndex || rightSectionIndex > rightSectionEndIndex)) {
        continueComparing = false;
      }
    }

    if (movedElements) {
      System.arraycopy(elementsWorkArray, 0, elementsArray, leftSectionStartIndex, elementsToProcessCount);
      System.arraycopy(comparablesWorkArray, 0, comparablesArray, leftSectionStartIndex, elementsToProcessCount);
    }
  }
}
