//package declaration
package ch.nolix.coretest.documenttest.nodetest;

//JUnit imports
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

//own imports
import ch.nolix.core.document.node.BaseMutableNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;

//class
abstract class BaseMutableNodeTest<MN extends BaseMutableNode<MN>> extends BaseNodeTest<MN> {

  //method
  @Test
  void testCase_removeHeader() {

    //setup
    final var testUnit = createBlankNode();
    testUnit.setHeader("Lorem");

    //setup verification
    expect(testUnit.hasHeader());

    //execution
    testUnit.removeHeader();

    //verification
    expectNot(testUnit.hasHeader());
  }

  //method
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
    final var testUnit = createBlankNode();

    //execution
    testUnit.resetFromString(string);

    //verification
    expect(testUnit).hasStringRepresentation(string);
  }

  //method
  @Test
  void testCase_resetFromString_whenTheGivenStringIsNotValid() {

    //setup
    final var testUnit = createBlankNode();

    //execution & verification
    expectRunning(() -> testUnit.resetFromString("a(b).c"))
      .throwsException()
      .ofType(UnrepresentingArgumentException.class);
  }

  //method
  @Test
  void testCase_setHeader() {

    //setup
    final var testUnit = createBlankNode();
    testUnit.setHeader("Lorem");

    //setup verification
    expect(testUnit.hasHeader());

    //execution
    testUnit.setHeader("Ipsum");

    //verification
    expect(testUnit.getHeader()).isEqualTo("Ipsum");
  }
}
