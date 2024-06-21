//package declaration
package ch.nolix.coretest.documenttest.chainednodetest;

//JUnit imports
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

//own imports
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class ChainedNodeCreationTest extends StandardTest {

  //method
  @Test
  void testCase_fromNode_whenNodeIsBlank() {

    //setup
    final var node = Node.EMPTY_NODE;

    //setup verification
    expect(node.isBlank());

    //execution
    final var result = ChainedNode.fromNode(node);

    //verification
    expect(result).hasStringRepresentation("");
  }

  //method
  @Test
  void testCase_fromNode_whenNodeHasHeaderOnly() {

    //setup
    final var node = Node.withHeader("a");

    //execution
    final var result = ChainedNode.fromNode(node);

    //verification
    expect(result).hasStringRepresentation("a");
  }

  //method
  @ParameterizedTest
  @ValueSource(strings = {
  "",
  "a",
  "a.b",
  "a(b)",
  "a.b.c",
  "a(b,c)",
  "a(b(c))",
  "a.b.c.d",
  "a(b,c,d)",
  "a(b(c(d)))",
  "a.b.c.d.e.f.g.h",
  "a(b,c,d,e,f,g,h)",
  "a(b(c(d(e(f(g(h)))))))",
  "a(b).c(d).e(f).g(h)"
  })
  void testCase_fromString(final String string) {

    //execution
    final var result = ChainedNode.fromString(string);

    //verification
    expect(result).hasStringRepresentation(string);
  }

  //method
  @Test
  void testCase_withHeader_whenNullHeaderIsGiven() {

    //execution & verification
    expectRunning(() -> ChainedNode.withHeader(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given header is null.");
  }

  //method
  @Test
  void testCase_withHeader_whenHeaderIsGiven() {

    //execution
    final var result = ChainedNode.withHeader("a");

    //verification
    expect(result).hasStringRepresentation("a");
  }
}
