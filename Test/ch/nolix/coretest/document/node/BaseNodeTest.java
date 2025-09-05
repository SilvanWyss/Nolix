package ch.nolix.coretest.document.node;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import ch.nolix.core.document.node.AbstractNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.testing.standardtest.StandardTest;

abstract class BaseNodeTest<N extends AbstractNode<N>> extends StandardTest {
  @Test
  final void testCase_getStoredSingleChildNode_whenNodeDoesNotContainChildNodes() {
    //setup
    final N testUnit = createNodeWithHeaderAndChildNodes("a");

    //execution & verification
    expectRunning(testUnit::getStoredSingleChildNode).throwsException().ofType(EmptyArgumentException.class);
  }

  @Test
  final void testCase_getStoredSingleChildNode_whenNodeContains1ChildNode() {
    //setup
    final N testUnit = createNodeWithHeaderAndChildNodes("a", "b");

    //execution
    final N result = testUnit.getStoredSingleChildNode();

    //verification
    expect(result).hasStringRepresentation("b");
  }

  @Test
  void testCase_getStoredChildNodeAtOneBasedIndex() {
    //setup
    final N testUnit = createNodeWithHeaderAndChildNodes("a", "b", "c", "d");

    //execution
    final N result1 = testUnit.getStoredChildNodeAtOneBasedIndex(1);
    final N result2 = testUnit.getStoredChildNodeAtOneBasedIndex(2);
    final N result3 = testUnit.getStoredChildNodeAtOneBasedIndex(3);

    //verification part 1
    expect(result1.toString()).isEqualTo("b");
    expect(result2.toString()).isEqualTo("c");
    expect(result3.toString()).isEqualTo("d");

    //verification part 2
    expectRunning(
      () -> testUnit.getStoredChildNodeAtOneBasedIndex(-1)).throwsException()
      .ofType(ArgumentIsOutOfRangeException.class);
    expectRunning(
      () -> testUnit.getStoredChildNodeAtOneBasedIndex(0)).throwsException()
      .ofType(ArgumentIsOutOfRangeException.class);
    expectRunning(
      () -> testUnit.getStoredChildNodeAtOneBasedIndex(4)).throwsException()
      .ofType(ArgumentIsOutOfRangeException.class);
  }

  @ParameterizedTest
  @ValueSource(strings = {
  "LoremXIpsum",
  "Lorem$Ipsum",
  "Lorem(Ipsum",
  "Lorem)Ipsum",
  "Lorem[Ipsum",
  "Lorem]Ipsum",
  "Lorem.Ipsum",
  "Lorem,Ipsum",
  "Lorem Ipsum" })
  void testCase_getHeader(final String header) {
    //setup
    final N testUnit = createNodeWithHeaderAndChildNodes(header);

    //execution
    final var result = testUnit.getHeader();

    //verification
    expect(result).isEqualTo(header);
  }

  @Test
  void testCase_getStoredSingleChildNode_1A() {
    //setup
    final N testUnit = createNodeWithHeaderAndChildNodes("a", "b");

    //execution
    final N result = testUnit.getStoredSingleChildNode();

    //verification
    expect(result.toString()).isEqualTo("b");
  }

  @Test
  void testCase_getStoredSingleChildNode_1B() {
    //setup
    final N testUnit = createBlankNode();

    //execution
    expectRunning(testUnit::getStoredSingleChildNode).throwsException().ofType(EmptyArgumentException.class);
  }

  @Test
  void testCase_toString_1() {
    //setup
    final N testUnit = createNodeWithHeaderAndChildNodes("a", "b", "c", "d");

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("a(b,c,d)");
  }

  @ParameterizedTest
  @CsvSource({
  "'Lorem(', 'Lorem$O'",
  "'Lorem)', 'Lorem$C'",
  "'Lorem,', 'Lorem$M'"
  })
  void testCase_toString_2(final String header, final String expectedResult) {
    //setup
    final N testUnit = createNodeWithHeader(header);

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo(expectedResult);
  }

  protected abstract N createBlankNode();

  protected abstract N createNodeWithHeader(String header);

  protected abstract N createNodeWithHeaderAndChildNodes(String header, String... childNodeHeaders);
}
