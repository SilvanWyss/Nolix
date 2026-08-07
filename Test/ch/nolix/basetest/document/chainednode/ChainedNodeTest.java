/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.document.chainednode;

import org.junit.jupiter.api.Test;

import ch.nolix.base.document.chainednode.ChainedNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;

/**
 * @author Silvan Wyss
 */
final class ChainedNodeTest extends StandardTest {
  @Test
  void testCase_equals_whenIsBlankAndAnUnequalChainedNodeIsGiven() {
    // setup
    final var testUnit = ChainedNode.fromString("");

    // execute
    final var result = testUnit.equals(ChainedNode.fromString("a"));

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_equals_whenIsBlankAndAnEqualChainedNodeIsGiven() {
    // setup
    final var testUnit = ChainedNode.fromString("");

    // execute
    final var result = testUnit.equals(ChainedNode.fromString(""));

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_getOneAttributeAsInt_whenDoesNotContainAttributes() {
    // setup
    final var testUnit = ChainedNode.withHeader("a");

    // setup verification
    expect(testUnit.containsChildNodes()).isFalse();

    // execute & verify
    expectRunning(testUnit::getSingleChildNodeAsInt)
      .throwsException()
      .ofType(EmptyArgumentException.class);
  }

  @Test
  void testCase_getOneAttributeAsInt_whenContainsOneAttributeThatDoesNotRepresentAnInt() {
    // setup
    final var testUnit = ChainedNode.fromString("a(b)");

    // setup verification
    expect(testUnit.getChildNodeCount()).isEqualTo(1);

    // execute & verify
    expectRunning(testUnit::getSingleChildNodeAsInt)
      .throwsException()
      .ofType(UnrepresentingArgumentException.class);
  }

  @Test
  void testCase_getOneAttributeAsInt_whenContainsOneAttributeThatRepresentsAnInt() {
    // setup
    final var testUnit = ChainedNode.fromString("a(10)");

    // setup verification
    expect(testUnit.getChildNodeCount()).isEqualTo(1);

    // execute
    final var result = testUnit.getSingleChildNodeAsInt();

    // verify
    expect(result).isEqualTo(10);
  }

  @Test
  void testCase_getOneAttributeAsInt_whenContainsSeveralAttributes() {
    // setup
    final var testUnit = ChainedNode.fromString("a(10, 20)");

    // setup verification
    expect(testUnit.getChildNodeCount()).isEqualTo(2);

    // execute & verify
    expectRunning(testUnit::getSingleChildNodeAsInt)
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }

  @Test
  void testCase_isBlank_whenIsBlank() {
    // setup
    final var testUnit = ChainedNode.fromString("");

    // execute
    final var result = testUnit.isBlank();

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_isBlank_whenHasHeaderOnly() {
    // setup
    final var testUnit = ChainedNode.withHeader("a");

    // execute
    final var result = testUnit.isBlank();

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_toInt_whenDoesNotRepresentInt_A1() {
    // setup
    final var testUnit = ChainedNode.EMPTY_CHAINED_NODE;

    // execute & verify
    expectRunning(testUnit::toInt)
      .throwsException()
      .ofType(UnrepresentingArgumentException.class)
      .withMessage("The given ChainedNode does not represent an Integer.");
  }

  @Test
  void testCase_toInt_whenDoesNotRepresentInt_A2() {
    // setup
    final var testUnit = ChainedNode.fromString("100(x)");

    // execute & verify
    expectRunning(testUnit::toInt)
      .throwsException()
      .ofType(UnrepresentingArgumentException.class)
      .withMessage("The given ChainedNode '100(x)' does not represent an Integer.");
  }

  @Test
  void testCase_toInt_whenRepresentsNegativeInt() {
    // setup
    final var testUnit = ChainedNode.withHeader("-100");

    // execute
    final var result = testUnit.toInt();

    // verify
    expect(result).isEqualTo(-100);
  }

  @Test
  void testCase_toInt_whenRepresentsPositiveInt() {
    // setup
    final var testUnit = ChainedNode.withHeader("100");

    // execute
    final var result = testUnit.toInt();

    // verify
    expect(result).isEqualTo(100);
  }

  @Test
  void testCase_toInt_whenRepresentsZeroInt() {
    // setup
    final var testUnit = ChainedNode.withHeader("0");

    // execute
    final var result = testUnit.toInt();

    // verify
    expect(result).isEqualTo(0);
  }

  @Test
  void testCase_toNode_whenIsBlank() {
    // setup
    final var testUnit = ChainedNode.fromString("");

    // execute
    final var result = testUnit.toNode();

    // verify
    expect(result).hasStringRepresentation("");
  }

  @Test
  void testCase_toNode_whenHasHeaderOnly() {
    // setup
    final var testUnit = ChainedNode.withHeader("a");

    // execute
    final var result = testUnit.toNode();

    // verify
    expect(result).hasStringRepresentation("a");
  }

  @Test
  void testCase_toNode_whenHasNextNode() {
    // setup
    final var testUnit = ChainedNode.fromString("a.b");

    // execute & verify
    expectRunning(testUnit::toNode)
      .throwsException()
      .ofType(UnrepresentingArgumentException.class)
      .withMessage("The given ChainedNode 'a.b' does not represent a Node.");
  }
}
