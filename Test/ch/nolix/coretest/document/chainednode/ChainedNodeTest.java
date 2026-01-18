/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coretest.document.chainednode;

import org.junit.jupiter.api.Test;

import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class ChainedNodeTest extends StandardTest {
  @Test
  void testCase_equals_whenIsBlankAndAnUnequalChainedNodeIsGiven() {
    //setup
    final var testUnit = ChainedNode.fromString("");

    //execution
    final var result = testUnit.equals(ChainedNode.fromString("a"));

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_equals_whenIsBlankAndAnEqualChainedNodeIsGiven() {
    //setup
    final var testUnit = ChainedNode.fromString("");

    //execution
    final var result = testUnit.equals(ChainedNode.fromString(""));

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_getOneAttributeAsInt_whenDoesNotContainAttributes() {
    //setup
    final var testUnit = ChainedNode.withHeader("a");

    //setup verification
    expect(testUnit.containsChildNodes()).isFalse();

    //execution & verification
    expectRunning(testUnit::getSingleChildNodeAsInt)
      .throwsException()
      .ofType(EmptyArgumentException.class);
  }

  @Test
  void testCase_getOneAttributeAsInt_whenContainsOneAttributeThatDoesNotRepresentAnInt() {
    //setup
    final var testUnit = ChainedNode.fromString("a(b)");

    //setup verification
    expect(testUnit.getChildNodeCount()).isEqualTo(1);

    //execution & verification
    expectRunning(testUnit::getSingleChildNodeAsInt)
      .throwsException()
      .ofType(UnrepresentingArgumentException.class);
  }

  @Test
  void testCase_getOneAttributeAsInt_whenContainsOneAttributeThatRepresentsAnInt() {
    //setup
    final var testUnit = ChainedNode.fromString("a(10)");

    //setup verification
    expect(testUnit.getChildNodeCount()).isEqualTo(1);

    //execution
    final var result = testUnit.getSingleChildNodeAsInt();

    //verification
    expect(result).isEqualTo(10);
  }

  @Test
  void testCase_getOneAttributeAsInt_whenContainsSeveralAttributes() {
    //setup
    final var testUnit = ChainedNode.fromString("a(10, 20)");

    //setup verification
    expect(testUnit.getChildNodeCount()).isEqualTo(2);

    //execution & verification
    expectRunning(testUnit::getSingleChildNodeAsInt)
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  @Test
  void testCase_isBlank_whenIsBlank() {
    //setup
    final var testUnit = ChainedNode.fromString("");

    //execution
    final var result = testUnit.isBlank();

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_isBlank_whenHasHeaderOnly() {
    //setup
    final var testUnit = ChainedNode.withHeader("a");

    //execution
    final var result = testUnit.isBlank();

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_toInt_whenDoesNotRepresentInt_A1() {
    //setup
    final var testUnit = new ChainedNode();

    //execution & verification
    expectRunning(testUnit::toInt)
      .throwsException()
      .ofType(UnrepresentingArgumentException.class)
      .withMessage("The given ChainedNode does not represent an Integer.");
  }

  @Test
  void testCase_toInt_whenDoesNotRepresentInt_A2() {
    //setup
    final var testUnit = ChainedNode.fromString("100(x)");

    //execution & verification
    expectRunning(testUnit::toInt)
      .throwsException()
      .ofType(UnrepresentingArgumentException.class)
      .withMessage("The given ChainedNode '100(x)' does not represent an Integer.");
  }

  @Test
  void testCase_toInt_whenRepresentsNegativeInt() {
    //setup
    final var testUnit = ChainedNode.withHeader("-100");

    //execution
    final var result = testUnit.toInt();

    //verification
    expect(result).isEqualTo(-100);
  }

  @Test
  void testCase_toInt_whenRepresentsPositiveInt() {
    //setup
    final var testUnit = ChainedNode.withHeader("100");

    //execution
    final var result = testUnit.toInt();

    //verification
    expect(result).isEqualTo(100);
  }

  @Test
  void testCase_toInt_whenRepresentsZeroInt() {
    //setup
    final var testUnit = ChainedNode.withHeader("0");

    //execution
    final var result = testUnit.toInt();

    //verification
    expect(result).isEqualTo(0);
  }

  @Test
  void testCase_toNode_whenIsBlank() {
    //setup
    final var testUnit = ChainedNode.fromString("");

    //execution
    final var result = testUnit.toNode();

    //verification
    expect(result).hasStringRepresentation("");
  }

  @Test
  void testCase_toNode_whenHasHeaderOnly() {
    //setup
    final var testUnit = ChainedNode.withHeader("a");

    //execution
    final var result = testUnit.toNode();

    //verification
    expect(result).hasStringRepresentation("a");
  }

  @Test
  void testCase_toNode_whenHasNextNode() {
    //setup
    final var testUnit = ChainedNode.fromString("a.b");

    //execution & verification
    expectRunning(testUnit::toNode)
      .throwsException()
      .ofType(UnrepresentingArgumentException.class)
      .withMessage("The given ChainedNode 'a.b' does not represent a Node.");
  }
}
