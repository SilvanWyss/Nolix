//package declaration
package ch.nolix.coretest.documenttest.chainednodetest;

//own imports
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class ChainedNodeTest extends Test {

  //method
  @TestCase
  public void testCase_equals_whenIsBlankAndAnUnequalChainedNodeIsGiven() {

    //setup
    final var testUnit = ChainedNode.fromString("");

    //execution
    final var result = testUnit.equals(ChainedNode.fromString("a"));

    //verification
    expectNot(result);
  }

  //method
  @TestCase
  public void testCase_equals_whenIsBlankAndAnEqualChainedNodeIsGiven() {

    //setup
    final var testUnit = ChainedNode.fromString("");

    //execution
    final var result = testUnit.equals(ChainedNode.fromString(""));

    //verification
    expect(result);
  }

  //method
  @TestCase
  public void testCase_getOneAttributeAsInt_whenDoesNotContainAttributes() {

    //setup
    final var testUnit = ChainedNode.withHeader("a");

    //setup verification
    expectNot(testUnit.containsChildNodes());

    //execution & verification
    expectRunning(testUnit::getSingleChildNodeAsInt)
      .throwsException()
      .ofType(EmptyArgumentException.class);
  }

  //method
  @TestCase
  public void testCase_getOneAttributeAsInt_whenContainsOneAttributeThatDoesNotRepresentAnInt() {

    //setup
    final var testUnit = ChainedNode.fromString("a(b)");

    //setup verification
    expect(testUnit.getChildNodeCount()).isEqualTo(1);

    //execution & verification
    expectRunning(testUnit::getSingleChildNodeAsInt)
      .throwsException()
      .ofType(UnrepresentingArgumentException.class);
  }

  //method
  @TestCase
  public void testCase_getOneAttributeAsInt_whenContainsOneAttributeThatRepresentsAnInt() {

    //setup
    final var testUnit = ChainedNode.fromString("a(10)");

    //setup verification
    expect(testUnit.getChildNodeCount()).isEqualTo(1);

    //execution
    final var result = testUnit.getSingleChildNodeAsInt();

    //verification
    expect(result).isEqualTo(10);
  }

  //method
  @TestCase
  public void testCase_getOneAttributeAsInt_whenContainsSeveralAttributes() {

    //setup
    final var testUnit = ChainedNode.fromString("a(10, 20)");

    //setup verification
    expect(testUnit.getChildNodeCount()).isEqualTo(2);

    //execution & verification
    expectRunning(testUnit::getSingleChildNodeAsInt)
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  //method
  @TestCase
  public void testCase_isBlank_whenIsBlank() {

    //setup
    final var testUnit = ChainedNode.fromString("");

    //execution
    final var result = testUnit.isBlank();

    //verification
    expect(result);
  }

  //method
  @TestCase
  public void testCase_isBlank_whenHasHeaderOnly() {

    //setup
    final var testUnit = ChainedNode.withHeader("a");

    //execution
    final var result = testUnit.isBlank();

    //verification
    expectNot(result);
  }

  //method
  @TestCase
  public void testCase_toInt_whenDoesNotRepresentInt_A1() {

    //setup
    final var testUnit = new ChainedNode();

    //execution & verification
    expectRunning(testUnit::toInt)
      .throwsException()
      .ofType(UnrepresentingArgumentException.class)
      .withMessage("The given ChainedNode does not represent an Integer.");
  }

  //method
  @TestCase
  public void testCase_toInt_whenDoesNotRepresentInt_A2() {

    //setup
    final var testUnit = ChainedNode.fromString("100(x)");

    //execution & verification
    expectRunning(testUnit::toInt)
      .throwsException()
      .ofType(UnrepresentingArgumentException.class)
      .withMessage("The given ChainedNode '100(x)' does not represent an Integer.");
  }

  //method
  @TestCase
  public void testCase_toInt_whenRepresentsNegativeInt() {

    //setup
    final var testUnit = ChainedNode.withHeader("-100");

    //execution
    final var result = testUnit.toInt();

    //verification
    expect(result).isEqualTo(-100);
  }

  //method
  @TestCase
  public void testCase_toInt_whenRepresentsPositiveInt() {

    //setup
    final var testUnit = ChainedNode.withHeader("100");

    //execution
    final var result = testUnit.toInt();

    //verification
    expect(result).isEqualTo(100);
  }

  //method
  @TestCase
  public void testCase_toInt_whenRepresentsZeroInt() {

    //setup
    final var testUnit = ChainedNode.withHeader("0");

    //execution
    final var result = testUnit.toInt();

    //verification
    expect(result).isEqualTo(0);
  }

  //method
  @TestCase
  public void testCase_toNode_whenIsBlank() {

    //setup
    final var testUnit = ChainedNode.fromString("");

    //execution
    final var result = testUnit.toNode();

    //verification
    expect(result).hasStringRepresentation("");
  }

  //method
  @TestCase
  public void testCase_toNode_whenHasHeaderOnly() {

    //setup
    final var testUnit = ChainedNode.withHeader("a");

    //execution
    final var result = testUnit.toNode();

    //verification
    expect(result).hasStringRepresentation("a");
  }

  //method
  @TestCase
  public void testCase_toNode_whenHasNextNode() {

    //setup
    final var testUnit = ChainedNode.fromString("a.b");

    //execution & verification
    expectRunning(testUnit::toNode)
      .throwsException()
      .ofType(UnrepresentingArgumentException.class)
      .withMessage("The given ChainedNode 'a.b' does not represent a Node.");
  }
}
