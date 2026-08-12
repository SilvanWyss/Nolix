/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseperformancetest.container.linkedlist;

import org.junit.jupiter.api.Test;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.foundation.util.FunctionService;
import ch.nolix.base.programcontrol.flowcontrol.FlowController;
import ch.nolix.base.testing.performancetest.PerformanceTest;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;

/**
 * @author Silvan Wyss
 */
final class LinkedListPerformanceTest extends PerformanceTest {
  @Test
  void testCase_addAtBegin() {
    expectOnAnObjectFrom(this::createLinkedList).running(ll -> ll.addAtBegin(66)).hasConstantOrLowerTimeComplexity();
  }

  @Test
  void testCase_addAtEnd() {
    expectOnAnObjectFrom(this::createLinkedList).running(ll -> ll.addAtEnd(66)).hasConstantOrLowerTimeComplexity();
  }

  @Test
  void testCase_getCount() {
    expectOnAnObjectFrom(this::createLinkedList).running(ExtendedIterable::getCount).hasConstantOrLowerTimeComplexity();
  }

  @Test
  void testCase_getMax() {
    expectOnAnObjectFrom(this::createLinkedList)
      .running(l -> l.getMax(FunctionService::getSelf))
      .hasLinearOrLowerTimeComplexity();
  }

  @Test
  void testCase_getMedian() {
    expectOnAnObjectFrom(this::createLinkedList)
      .running(l -> l.getMedian(FunctionService::getSelf))
      .hasQuadraticOrLowerTimeComplexity();
  }

  @Test
  void testCase_getMin() {
    expectOnAnObjectFrom(this::createLinkedList)
      .running(l -> l.getMin(FunctionService::getSelf))
      .hasLinearOrLowerTimeComplexity();
  }

  @Test
  void testCase_getViewOfStoredSelected() {
    expectOnAnObjectFrom(this::createLinkedList)
      .running(l -> l.getViewOfStoredSelected(n -> n % 3 == 0))
      .hasConstantOrLowerTimeComplexity();
  }

  @Test
  void testCase_removeFirst() {
    expectOnAnObjectFrom(this::createLinkedList).running(ILinkedList::removeFirst).hasConstantOrLowerTimeComplexity();

  }

  @Test
  void testCase_removeLast() {
    expectOnAnObjectFrom(this::createLinkedList).running(ILinkedList::removeLast).hasLinearOrLowerTimeComplexity();
  }

  @Test
  void testCase_toIntArray() {
    expectOnAnObjectFrom(this::createLinkedList)
      .running(l -> l.toIntArray(i -> (i / 2) + 3))
      .hasLinearOrLowerTimeComplexity();
  }

  @Test
  void testCase_toOrdered() {
    expectOnAnObjectFrom(this::createLinkedList)
      .running(l -> l.toOrdered(FunctionService::getSelf))
      .hasGivenOrLowerTimeComplexity(n -> n * Math.sqrt(n));
  }

  private ILinkedList<Integer> createLinkedList(final int elementCount) {
    final ILinkedList<Integer> list = LinkedList.createEmpty();

    FlowController.forCount(elementCount).run(i -> list.addAtEnd(i - (i % 1000)));

    return list;
  }
}
