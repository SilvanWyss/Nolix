//package declaration
package ch.nolix.coretest.documenttest.chainednodetest;

//own imports
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class ChainedNodeCreationTest extends StandardTest {

  //method
  @TestCase
  public void testCase_fromNode_whenNodeIsBlank() {

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
  @TestCase
  public void testCase_fromNode_whenNodeHasHeaderOnly() {

    //setup
    final var node = Node.withHeader("a");

    //execution
    final var result = ChainedNode.fromNode(node);

    //verification
    expect(result).hasStringRepresentation("a");
  }

  //method
  @TestCase
  public void testCase_fromString_A1() {

    //execution
    final var result = ChainedNode.fromString("");

    //verification
    expectNot(result.hasHeader());
    expectNot(result.containsChildNodes());
    expectNot(result.hasNextNode());
    expect(result.toString()).isEqualTo("");
  }

  //method
  @TestCase
  public void testCase_fromString_A2() {

    //execution
    final var result = ChainedNode.fromString("a");

    //verification
    expect(result.hasHeader());
    expectNot(result.containsChildNodes());
    expectNot(result.hasNextNode());
    expect(result.toString()).isEqualTo("a");
  }

  //method
  @TestCase
  public void testCase_fromString_A3() {

    //execution
    final var result = ChainedNode.fromString("a(b)");

    //verification
    expect(result.hasHeader());
    expect(result.containsChildNodes());
    expectNot(result.hasNextNode());
    expect(result.toString()).isEqualTo("a(b)");
  }

  //method
  @TestCase
  public void testCase_fromString_B1() {

    //execution
    final var result = ChainedNode.fromString("a.b");

    //verification
    expect(result.hasHeader());
    expectNot(result.containsChildNodes());
    expect(result.hasNextNode());
    expect(result.toString()).isEqualTo("a.b");
  }

  //method
  @TestCase
  public void testCase_fromString_B2() {

    //execution
    final var result = ChainedNode.fromString("a(b).c");

    //verification
    expect(result.hasHeader());
    expect(result.containsChildNodes());
    expect(result.hasNextNode());
    expect(result.toString()).isEqualTo("a(b).c");
  }

  //method
  @TestCase
  public void testCase_fromString_B3() {

    //execution
    final var result = ChainedNode.fromString("a.b(c)");

    //verification
    expect(result.hasHeader());
    expectNot(result.containsChildNodes());
    expect(result.hasNextNode());
    expect(result.toString()).isEqualTo("a.b(c)");
  }

  //method
  @TestCase
  public void testCase_fromString_B4() {

    //execution
    final var result = ChainedNode.fromString("(a.b).c");

    //verification
    expectNot(result.hasHeader());
    expect(result.containsChildNodes());
    expect(result.hasNextNode());
    expect(result.toString()).isEqualTo("(a.b).c");
  }

  //method
  @TestCase
  public void testCase_fromString_B5() {

    //execution
    final var result = ChainedNode.fromString("a.(b.c)");

    //verification
    expect(result.hasHeader());
    expectNot(result.containsChildNodes());
    expect(result.hasNextNode());
    expect(result.toString()).isEqualTo("a.(b.c)");
  }

  //method
  @TestCase
  public void testCase_fromString_B6() {

    //execution
    final var result = ChainedNode.fromString("a.b.c");

    //verification
    expect(result.hasHeader());
    expectNot(result.containsChildNodes());
    expect(result.hasNextNode());
    expect(result.toString()).isEqualTo("a.b.c");
  }

  //method
  @TestCase
  public void testCase_fromString_C1() {

    //execution
    final var result = ChainedNode.fromString("a(b,c,d)");

    //verification
    expect(result.hasHeader());
    expect(result.containsChildNodes());
    expectNot(result.hasNextNode());
    expect(result.toString()).isEqualTo("a(b,c,d)");
  }

  //method
  @TestCase
  public void testCase_fromString_C2() {

    //execution
    final var result = ChainedNode.fromString("a(b(c),d(e),f(g))");

    //verification
    expect(result.hasHeader());
    expect(result.containsChildNodes());
    expectNot(result.hasNextNode());
    expect(result.toString()).isEqualTo("a(b(c),d(e),f(g))");
  }

  //method
  @TestCase
  public void testCase_fromString_C3() {

    //execution
    final var result = ChainedNode.fromString("a(b.c,d.e,f.g)");

    //verification
    expect(result.hasHeader());
    expect(result.containsChildNodes());
    expectNot(result.hasNextNode());
    expect(result.toString()).isEqualTo("a(b.c,d.e,f.g)");
  }

  //method
  @TestCase
  public void testCase_fromString_C4() {

    //execution
    final var result = ChainedNode.fromString("a(b(c).d,e(f).g,h(i).j)");

    //verification
    expect(result.hasHeader());
    expect(result.containsChildNodes());
    expectNot(result.hasNextNode());
    expect(result.toString()).isEqualTo("a(b(c).d,e(f).g,h(i).j)");
  }

  //method
  @TestCase
  public void testCase_fromString_C5() {

    //execution
    final var result = ChainedNode.fromString("a(b.c(d),e.(f.g),h.(i,j))");

    //verification
    expect(result.hasHeader());
    expect(result.containsChildNodes());
    expectNot(result.hasNextNode());
    expect(result.toString()).isEqualTo("a(b.c(d),e.(f.g),h.(i,j))");
  }

  //method
  @TestCase
  public void testCase_fromString_C6() {

    //execution
    final var result = ChainedNode.fromString("a(b.c.d,e.f.g,h.i.j)");

    //verification
    expect(result.hasHeader());
    expect(result.containsChildNodes());
    expectNot(result.hasNextNode());
    expect(result.toString()).isEqualTo("a(b.c.d,e.f.g,h.i.j)");
  }

  //method
  @TestCase
  public void testCase_withHeader_whenNullHeaderIsGiven() {

    //execution & verification
    expectRunning(() -> ChainedNode.withHeader(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given header is null.");
  }

  //method
  @TestCase
  public void testCase_withHeader_whenHeaderIsGiven() {

    //execution
    final var result = ChainedNode.withHeader("a");

    //verification
    expect(result).hasStringRepresentation("a");
  }
}
