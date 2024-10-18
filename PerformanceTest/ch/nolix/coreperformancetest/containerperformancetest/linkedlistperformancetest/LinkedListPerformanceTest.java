package ch.nolix.coreperformancetest.containerperformancetest.linkedlistperformancetest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.programatom.function.GlobalFunctionService;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.testing.performancetest.PerformanceTest;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;

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
    expectOnAnObjectFrom(this::createLinkedList).running(IContainer::getCount).hasConstantOrLowerTimeComplexity();
  }

  @Test
  void testCase_getMax() {
    expectOnAnObjectFrom(this::createLinkedList)
      .running(l -> l.getMax(GlobalFunctionService::getSelf))
      .hasLinearOrLowerTimeComplexity();
  }

  @Test
  void testCase_getMedian() {
    expectOnAnObjectFrom(this::createLinkedList)
      .running(l -> l.getMedian(GlobalFunctionService::getSelf))
      .hasQuadraticOrLowerTimeComplexity();
  }

  @Test
  void testCase_getMin() {
    expectOnAnObjectFrom(this::createLinkedList)
      .running(l -> l.getMin(GlobalFunctionService::getSelf))
      .hasLinearOrLowerTimeComplexity();
  }

  @Test
  void testCase_removeFirst() {
    expectOnAnObjectFrom(this::createLinkedList).running(ILinkedList::removeFirst).hasConstantOrLowerTimeComplexity();

  }

  @Test
  void testCase_removeLast() {
    expectOnAnObjectFrom(this::createLinkedList).running(ILinkedList::removeLast).hasLinearOrLowerTimeComplexity();
  }

  private ILinkedList<Integer> createLinkedList(final int elementCount) {

    final ILinkedList<Integer> list = LinkedList.createEmpty();

    GlobalSequencer.forCount(elementCount).run(i -> list.addAtEnd(i - (i % 1000)));

    return list;
  }
}
