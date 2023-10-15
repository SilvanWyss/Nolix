//package declaration
package ch.nolix.coretest.documenttest.nodetest;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public abstract class BaseNodeTest<BN extends BaseNode<BN>> extends Test {

  // method
  @TestCase
  public final void testCase_getStoredSingleChildNode_whenNodeDoesNotContainChildNodes() {

    // setup
    final var testUnit = createNodeWithHeaderAndChildNodes("a");

    // execution & verification
    expectRunning(testUnit::getStoredSingleChildNode).throwsException().ofType(InvalidArgumentException.class);
  }

  // method
  @TestCase
  public final void testCase_getStoredSingleChildNode_whenNodeContains1ChildNode() {

    // setup
    final var testUnit = createNodeWithHeaderAndChildNodes("a", "b");

    // execution
    final var result = testUnit.getStoredSingleChildNode();

    // verification
    expect(result).hasStringRepresentation("b");
  }

  // method
  @TestCase
  public void testCase_getStoredChildNodeAt1BasedIndex() {

    // setup
    final var testUnit = createNodeWithHeaderAndChildNodes("a", "b", "c", "d");

    // execution
    final var result1 = testUnit.getStoredChildNodeAt1BasedIndex(1);
    final var result2 = testUnit.getStoredChildNodeAt1BasedIndex(2);
    final var result3 = testUnit.getStoredChildNodeAt1BasedIndex(3);

    // verification part 1
    expect(result1.toString()).isEqualTo("b");
    expect(result2.toString()).isEqualTo("c");
    expect(result3.toString()).isEqualTo("d");

    // verification part 2
    expectRunning(
        () -> testUnit.getStoredChildNodeAt1BasedIndex(-1)).throwsException()
        .ofType(ArgumentIsOutOfRangeException.class);
    expectRunning(
        () -> testUnit.getStoredChildNodeAt1BasedIndex(0)).throwsException()
        .ofType(ArgumentIsOutOfRangeException.class);
    expectRunning(
        () -> testUnit.getStoredChildNodeAt1BasedIndex(4)).throwsException()
        .ofType(ArgumentIsOutOfRangeException.class);
  }

  // method
  @TestCase
  public void testCase_getHeader_1A() {

    // setup
    final var testUnit = createNodeWithHeaderAndChildNodes("Lorem(");

    // execution
    final var result = testUnit.getHeader();

    // verification
    expect(result).isEqualTo("Lorem(");
  }

  // method
  @TestCase
  public void testCase_getHeader_1B() {

    // setup
    final var testUnit = createNodeWithHeaderAndChildNodes("Lorem)");

    // execution
    final var result = testUnit.getHeader();

    // verification
    expect(result).isEqualTo("Lorem)");
  }

  // method
  @TestCase
  public void testCase_getHeader_1C() {

    // setup
    final var testUnit = createNodeWithHeaderAndChildNodes("Lorem.");

    // execution
    final var result = testUnit.getHeader();

    // verification
    expect(result).isEqualTo("Lorem.");
  }

  // method
  @TestCase
  public void testCase_getHeader_1D() {

    // setup
    final var testUnit = createNodeWithHeaderAndChildNodes("Lorem,");

    // execution
    final var result = testUnit.getHeader();

    // verification
    expect(result).isEqualTo("Lorem,");
  }

  // method
  @TestCase
  public void testCase_getStoredSingleChildNode_1A() {

    // setup
    final var testUnit = createNodeWithHeaderAndChildNodes("a", "b");

    // execution
    final var result = testUnit.getStoredSingleChildNode();

    // verification
    expect(result.toString()).isEqualTo("b");
  }

  // method
  @TestCase
  public void testCase_getStoredSingleChildNode_1B() {

    // setup
    final var testUnit = createBlankNode();

    // execution
    expectRunning(testUnit::getStoredSingleChildNode).throwsException().ofType(InvalidArgumentException.class);
  }

  // method
  @TestCase
  public void testCase_toString_1() {

    // setup
    final var testUnit = createNodeWithHeaderAndChildNodes("a", "b", "c", "d");

    // execution
    final var result = testUnit.toString();

    // verification
    expect(result).isEqualTo("a(b,c,d)");
  }

  // method
  @TestCase
  public void testCase_toString_2A() {

    // setup
    final var testUnit = createNodeWithHeaderAndChildNodes("Lorem(");

    // execution
    final var result = testUnit.toString();

    // verification
    expect(result).isEqualTo("Lorem$O");
  }

  // method
  @TestCase
  public void testCase_toString_2B() {

    // setup
    final var testUnit = createNodeWithHeaderAndChildNodes("Lorem)");

    // execution
    final var result = testUnit.toString();

    // verification
    expect(result).isEqualTo("Lorem$C");
  }

  // method
  @TestCase
  public void testCase_toString_2C() {

    // setup
    final var testUnit = createNodeWithHeaderAndChildNodes("Lorem,");

    // execution
    final var result = testUnit.toString();

    // verification
    expect(result).isEqualTo("Lorem$M");
  }

  // method
  @TestCase
  public final void testCase_toXml() {

    // setup
    final var testUnit = createNodeWithHeaderAndChildNodes("a");

    // execution
    final var result = testUnit.toXml();

    // verification
    expect(result).hasStringRepresentation("<a></a>");
  }

  // method declaration
  protected abstract BN createBlankNode();

  // method declaration
  protected abstract BN createNodeWithHeaderAndChildNodes(String header, String... childNodeHeaders);
}
