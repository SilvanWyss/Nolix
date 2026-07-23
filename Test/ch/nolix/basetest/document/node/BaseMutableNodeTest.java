/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.document.node;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ch.nolix.base.document.node.AbstractMutableNode;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;

/**
 * @author Silvan Wyss
 * @param <N> the type of the tested {@link AbstractMutableNode}s of a
 *            {@link BaseMutableNodeTest}
 */
abstract class BaseMutableNodeTest<N extends AbstractMutableNode<N>> extends BaseNodeTest<N> {
  @Test
  void testCase_addPostfixToHeader_whenDoesNotHaveHeader_andTheGivenPostfixIsBlank() {
    // setup
    final N testUnit = createBlankNode();

    // execute
    expectRunning(() -> testUnit.addPostfixToHeader(" "))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessageThatMatches("The given postfix is blank.");

    // verify
    expect(testUnit.hasHeader()).isFalse();
  }

  @Test
  void testCase_addPostfixToHeader_whenDoesNotHaveHeader_andTheGivenPostfixIsNotBlank() {
    // setup
    final N testUnit = createBlankNode();

    // execute
    testUnit.addPostfixToHeader("1");

    // verify
    expect(testUnit.getHeader()).isEqualTo("1");
  }

  @Test
  void testCase_addPostfixToHeader_whenHasHeader_andTheGivenPostfixIsBlank() {
    // setup
    final N testUnit = createNodeWithHeader("Color");

    // execute
    expectRunning(() -> testUnit.addPostfixToHeader(" "))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessageThatMatches("The given postfix is blank.");

    // verify
    expect(testUnit.getHeader()).isEqualTo("Color");
  }

  @Test
  void testCase_addPostfixToHeader_whenHasHeader_andTheGivenPostfixIsNotBlank() {
    // setup
    final N testUnit = createNodeWithHeader("Color");

    // execute
    testUnit.addPostfixToHeader("1");

    // verify
    expect(testUnit.getHeader()).isEqualTo("Color1");
  }

  @Test
  void testCase_addPrefixToHeader_whenDoesNotHaveHeader_andTheGivenPrefixIsBlank() {
    // setup
    final N testUnit = createBlankNode();

    // execute
    expectRunning(() -> testUnit.addPrefixToHeader(" "))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessageThatMatches("The given prefix is blank.");

    // verify
    expect(testUnit.hasHeader()).isFalse();
  }

  @Test
  void testCase_addPrefixToHeader_whenDoesNotHaveHeader_andTheGivenPrefixIsNotBlank() {
    // setup
    final N testUnit = createBlankNode();

    // execute
    testUnit.addPrefixToHeader("Background");

    // verify
    expect(testUnit.getHeader()).isEqualTo("Background");
  }

  @Test
  void testCase_addPrefixToHeader_whenHasHeader_andTheGivenPrefixIsBlank() {
    // setup
    final N testUnit = createNodeWithHeader("Color");

    // execute
    expectRunning(() -> testUnit.addPrefixToHeader(" "))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessageThatMatches("The given prefix is blank.");

    // verify
    expect(testUnit.getHeader()).isEqualTo("Color");
  }

  @Test
  void testCase_addPrefixToHeader_whenHasHeader_andTheGivenPrefixIsNotBlank() {
    // setup
    final N testUnit = createNodeWithHeader("Color");

    // execute
    testUnit.addPrefixToHeader("Background");

    // verify
    expect(testUnit.getHeader()).isEqualTo("BackgroundColor");
  }

  @Test
  void testCase_removeHeader() {
    // setup
    final N testUnit = createBlankNode();
    testUnit.setHeader("Lorem");

    // setup verification
    expect(testUnit.hasHeader()).isTrue();

    // execute
    testUnit.removeHeader();

    // verify
    expect(testUnit.hasHeader()).isFalse();
  }

  @ParameterizedTest
  @ValueSource(strings = {
  "",
  "a",
  "(a)",
  "a(b)",
  "a(b,c)",
  "a(b(c))",
  "(a(b,c))",
  "a(b,c,d)",
  "a(b(c(d)))",
  "(a(b,c,d))",
  "a(b(c),d(e),f(g))"
  })
  void testCase_resetFromString(final String string) {
    // setup
    final N testUnit = createBlankNode();

    // execute
    testUnit.resetFromString(string);

    // verify
    expect(testUnit).hasStringRepresentation(string);
  }

  @Test
  void testCase_resetFromString_whenTheGivenStringIsNotValid() {
    // setup
    final N testUnit = createBlankNode();

    // execute & verify
    expectRunning(() -> testUnit.resetFromString("a(b).c"))
      .throwsException()
      .ofType(UnrepresentingArgumentException.class);
  }

  @Test
  void testCase_setHeader() {
    // setup
    final N testUnit = createBlankNode();
    testUnit.setHeader("Lorem");

    // setup verification
    expect(testUnit.hasHeader());

    // execute
    testUnit.setHeader("Ipsum");

    // verify
    expect(testUnit.getHeader()).isEqualTo("Ipsum");
  }
}
