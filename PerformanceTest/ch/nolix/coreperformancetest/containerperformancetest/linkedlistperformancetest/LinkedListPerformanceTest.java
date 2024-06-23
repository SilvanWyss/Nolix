//package declaration
package ch.nolix.coreperformancetest.containerperformancetest.linkedlistperformancetest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.programatom.function.FunctionCatalogue;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.testing.performancetest.PerformanceTest;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;

//class
final class LinkedListPerformanceTest extends PerformanceTest {

  //method
  @Test
  void testCase_addAtBegin() {
    expectOnAnObjectFrom(this::createLinkedList).running(ll -> ll.addAtBegin(66)).hasConstantTimeComplexity();
  }

  //method
  @Test
  void testCase_addAtEnd() {
    expectOnAnObjectFrom(this::createLinkedList).running(ll -> ll.addAtEnd(66)).hasConstantTimeComplexity();
  }

  //method
  @Test
  void testCase_getCount() {
    expectOnAnObjectFrom(this::createLinkedList).running(IContainer::getCount).hasConstantTimeComplexity();
  }

  //method
  @Test
  void testCase_getMax() {
    expectOnAnObjectFrom(this::createLinkedList)
      .running(l -> l.getMax(FunctionCatalogue::getSelf))
      .hasLinearOrLowerTimeComplexity();
  }

  //method
  @Test
  void testCase_getMedian() {
    expectOnAnObjectFrom(this::createLinkedList)
      .running(l -> l.getMedian(FunctionCatalogue::getSelf))
      .hasQuadraticOrLowerTimeComplexity();
  }

  //method
  @Test
  void testCase_getMin() {
    expectOnAnObjectFrom(this::createLinkedList)
      .running(l -> l.getMin(FunctionCatalogue::getSelf))
      .hasLinearOrLowerTimeComplexity();
  }

  //method
  @Test
  void testCase_removeFirst() {
    expectOnAnObjectFrom(this::createLinkedList).running(ILinkedList::removeFirst).hasConstantTimeComplexity();

  }

  //method
  @Test
  void testCase_removeLast() {
    expectOnAnObjectFrom(this::createLinkedList).running(ILinkedList::removeLast).hasLinearOrLowerTimeComplexity();
  }

  //method
  private LinkedList<Integer> createLinkedList(final int elementCount) {

    final var list = new LinkedList<Integer>();

    GlobalSequencer.forCount(elementCount).run(i -> list.addAtEnd(i - (i % 1000)));

    return list;
  }
}
