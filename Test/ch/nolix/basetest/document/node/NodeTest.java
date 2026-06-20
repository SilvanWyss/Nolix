/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.document.node;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.base.document.node.ImmutableNode;

/**
 * @author Silvan Wyss
 */
final class NodeTest extends BaseNodeTest<ImmutableNode> {
  @Test
  void testCase_asWithHeader_1A() {
    //setup
    final var testUnit = ImmutableNode.fromString("a(x,y)");

    //execution
    final var result = testUnit.withNewHeader("b");

    //verification
    expect(result).hasStringRepresentation("b(x,y)");
  }

  @Test
  void testCase_asWithHeader_1B() {
    //setup
    final var testUnit = ImmutableNode.fromString("(x,y)");

    //execution
    final var result = testUnit.withNewHeader("a");

    //verification
    expect(result).hasStringRepresentation("a(x,y)");
  }

  @Test
  void testCase_asWithHeader_1C() {
    //setup
    final var testUnit = ImmutableNode.fromString("a");

    //execution
    final var result = testUnit.withNewHeader("b");

    //verification
    expect(result).hasStringRepresentation("b");
  }

  @ParameterizedTest
  @CsvSource({
  "a, <a></a>",
  "a(b), <a>b</a>",
  "a(b(c)), <a><b>c</b></a>"
  })
  void testCase_toXml(final String nodeStringRepresentation, final String expectedXmlStringRepresentation) {
    //setup
    final var testUnit = ImmutableNode.fromString(nodeStringRepresentation);

    //execution
    final var result = testUnit.toXml();

    //verification
    expect(result).hasStringRepresentation(expectedXmlStringRepresentation);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ImmutableNode createBlankNode() {
    return ImmutableNode.EMPTY_NODE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ImmutableNode createNodeWithHeader(final String header) {
    return ImmutableNode.withHeader(header);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ImmutableNode createNodeWithHeaderAndChildNodes(final String header, final String... childNodes) {
    return ImmutableNode.withHeaderAndChildNodes(header, childNodes);
  }
}
