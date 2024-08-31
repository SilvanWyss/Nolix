package ch.nolix.systemtest.noderawdatatest.nodesearchertest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.noderawdata.nodesearcher.TableNodeSearcher;

final class TableNodeSearcherTest extends StandardTest {

  @Test
  void testCase_tableNodeContainsEntityNodeWhoseFieldAtGivenIndexContainsGivenHeaderIgnoringGivenEntities_1A() {

    //Setups tableNode.
    final var tableNode = //
    MutableNode
      .createEmpty()
      .addChildNode(
        Node.withHeaderAndChildNode("Entity",
          Node.withHeader("id1"),
          Node.withHeader("save_stamp"),
          Node.withHeader("Donald"),
          Node.withHeader("Duck")),
        Node.withHeaderAndChildNode("Entity",
          Node.withHeader("id2"),
          Node.withHeader("save_stamp"),
          Node.withHeader("Dagobert"),
          Node.withHeader("Duck")));

    //Setups testUnit
    final var testUnit = new TableNodeSearcher();

    //execution
    final var result = testUnit
      .tableNodeContainsEntityNodeWhoseFieldAtGivenIndexContainsGivenHeaderIgnoringGivenEntities(
        tableNode,
        4,
        "Duck",
        ImmutableList.withElement("id1"));

    //verification
    expect(result);
  }

  @Test
  void testCase_tableNodeContainsEntityNodeWhoseFieldAtGivenIndexContainsGivenHeaderIgnoringGivenEntities_1B() {

    //Setups tableNode.
    final var tableNode = //
    MutableNode
      .createEmpty()
      .addChildNode(
        Node.withHeaderAndChildNode("Entity",
          Node.withHeader("id1"),
          Node.withHeader("save_stamp"),
          Node.withHeader("Donald"),
          Node.withHeader("Duck")),
        Node.withHeaderAndChildNode("Entity",
          Node.withHeader("id2"),
          Node.withHeader("save_stamp"),
          Node.withHeader("Dagobert"),
          Node.withHeader("Duck")));

    //Setups testUnit
    final var testUnit = new TableNodeSearcher();

    //execution
    final var result = testUnit
      .tableNodeContainsEntityNodeWhoseFieldAtGivenIndexContainsGivenHeaderIgnoringGivenEntities(
        tableNode,
        4,
        "Duck",
        ImmutableList.withElement("id2"));

    //verification
    expect(result);
  }

  @Test
  void testCase_tableNodeContainsEntityNodeWhoseFieldAtGivenIndexContainsGivenHeaderIgnoringGivenEntities_2A() {

    //Setups tableNode.
    final var tableNode = //
    MutableNode
      .createEmpty()
      .addChildNode(
        Node.withHeaderAndChildNode("Entity",
          Node.withHeader("id1"),
          Node.withHeader("save_stamp"),
          Node.withHeader("Donald"),
          Node.withHeader("Duck")),
        Node.withHeaderAndChildNode("Entity",
          Node.withHeader("id2"),
          Node.withHeader("save_stamp"),
          Node.withHeader("Dagobert"),
          Node.withHeader("Duck")));

    //Setups testUnit
    final var testUnit = new TableNodeSearcher();

    //execution
    final var result = testUnit
      .tableNodeContainsEntityNodeWhoseFieldAtGivenIndexContainsGivenHeaderIgnoringGivenEntities(
        tableNode,
        3,
        "Donald",
        ImmutableList.withElement("id2"));

    //verification
    expect(result);
  }

  @Test
  void testCase_tableNodeContainsEntityNodeWhoseFieldAtGivenIndexContainsGivenHeaderIgnoringGivenEntities_2B() {

    //Setups tableNode.
    final var tableNode = //
    MutableNode
      .createEmpty()
      .addChildNode(
        Node.withHeaderAndChildNode("Entity",
          Node.withHeader("id1"),
          Node.withHeader("save_stamp"),
          Node.withHeader("Donald"),
          Node.withHeader("Duck")),
        Node.withHeaderAndChildNode("Entity",
          Node.withHeader("id2"),
          Node.withHeader("save_stamp"),
          Node.withHeader("Dagobert"),
          Node.withHeader("Duck")));

    //Setups testUnit
    final var testUnit = new TableNodeSearcher();

    //execution
    final var result = testUnit
      .tableNodeContainsEntityNodeWhoseFieldAtGivenIndexContainsGivenHeaderIgnoringGivenEntities(
        tableNode,
        3,
        "Donald",
        ImmutableList.withElement("id1"));

    //verification
    expectNot(result);
  }
}
