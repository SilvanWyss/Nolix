package ch.nolix.coretest.document.node;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ch.nolix.core.document.node.AbstractMutableNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;

abstract class BaseMutableNodeTest<N extends AbstractMutableNode<N>> extends BaseNodeTest<N> {

  @Test
  void testCase_addPostfixToHeader_whenDoesNotHaveHeader_andTheGivenPostfixIsBlank() {

    //setup
    final N testUnit = createBlankNode();

    //execution
    expectRunning(() -> testUnit.addPostfixToHeader(" "))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessageThatMatches("The given postfix is blank.");

    //verification
    expect(testUnit.hasHeader()).isFalse();
  }

  @Test
  void testCase_addPostfixToHeader_whenDoesNotHaveHeader_andTheGivenPostfixIsNotBlank() {

    //setup
    final N testUnit = createBlankNode();

    //execution
    testUnit.addPostfixToHeader("1");

    //verification
    expect(testUnit.getHeader()).isEqualTo("1");
  }

  @Test
  void testCase_addPostfixToHeader_whenHasHeader_andTheGivenPostfixIsBlank() {

    //setup
    final N testUnit = createNodeWithHeader("Color");

    //execution
    expectRunning(() -> testUnit.addPostfixToHeader(" "))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessageThatMatches("The given postfix is blank.");

    //verification
    expect(testUnit.getHeader()).isEqualTo("Color");
  }

  @Test
  void testCase_addPostfixToHeader_whenHasHeader_andTheGivenPostfixIsNotBlank() {

    //setup
    final N testUnit = createNodeWithHeader("Color");

    //execution
    testUnit.addPostfixToHeader("1");

    //verification
    expect(testUnit.getHeader()).isEqualTo("Color1");
  }

  @Test
  void testCase_addPrefixToHeader_whenDoesNotHaveHeader_andTheGivenPrefixIsBlank() {

    //setup
    final N testUnit = createBlankNode();

    //execution
    expectRunning(() -> testUnit.addPrefixToHeader(" "))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessageThatMatches("The given prefix is blank.");

    //verification
    expect(testUnit.hasHeader()).isFalse();
  }

  @Test
  void testCase_addPrefixToHeader_whenDoesNotHaveHeader_andTheGivenPrefixIsNotBlank() {

    //setup
    final N testUnit = createBlankNode();

    //execution
    testUnit.addPrefixToHeader("Background");

    //verification
    expect(testUnit.getHeader()).isEqualTo("Background");
  }

  @Test
  void testCase_addPrefixToHeader_whenHasHeader_andTheGivenPrefixIsBlank() {

    //setup
    final N testUnit = createNodeWithHeader("Color");

    //execution
    expectRunning(() -> testUnit.addPrefixToHeader(" "))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessageThatMatches("The given prefix is blank.");

    //verification
    expect(testUnit.getHeader()).isEqualTo("Color");
  }

  @Test
  void testCase_addPrefixToHeader_whenHasHeader_andTheGivenPrefixIsNotBlank() {

    //setup
    final N testUnit = createNodeWithHeader("Color");

    //execution
    testUnit.addPrefixToHeader("Background");

    //verification
    expect(testUnit.getHeader()).isEqualTo("BackgroundColor");
  }

  @Test
  void testCase_removeHeader() {

    //setup
    final N testUnit = createBlankNode();
    testUnit.setHeader("Lorem");

    //setup verification
    expect(testUnit.hasHeader()).isTrue();

    //execution
    testUnit.removeHeader();

    //verification
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

    //setup
    final N testUnit = createBlankNode();

    //execution
    testUnit.resetFromString(string);

    //verification
    expect(testUnit).hasStringRepresentation(string);
  }

  @Test
  void testCase_resetFromString_whenTheGivenStringIsNotValid() {

    //setup
    final N testUnit = createBlankNode();

    //execution & verification
    expectRunning(() -> testUnit.resetFromString("a(b).c"))
      .throwsException()
      .ofType(UnrepresentingArgumentException.class);
  }

  @Test
  void testCase_setHeader() {

    //setup
    final N testUnit = createBlankNode();
    testUnit.setHeader("Lorem");

    //setup verification
    expect(testUnit.hasHeader());

    //execution
    testUnit.setHeader("Ipsum");

    //verification
    expect(testUnit.getHeader()).isEqualTo("Ipsum");
  }
}
