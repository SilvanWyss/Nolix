/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.document.chainednode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ch.nolix.base.document.chainednode.ImmutableChainedNode;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;

/**
 * @author Silvan Wyss
 */
final class ImmutableChainedNodeCreationTest extends StandardTest {
  @Test
  void testCase_fromNode_whenNodeIsBlank() {
    // setup
    final var node = ImmutableNode.EMPTY_NODE;

    // setup verification
    expect(node.isBlank()).isTrue();

    // execute
    final var result = ImmutableChainedNode.fromNode(node);

    // verify
    expect(result).hasStringRepresentation("");
  }

  @Test
  void testCase_fromNode_whenNodeHasHeaderOnly() {
    // setup
    final var node = ImmutableNode.withHeader("a");

    // execute
    final var result = ImmutableChainedNode.fromNode(node);

    // verify
    expect(result).hasStringRepresentation("a");
  }

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
    // execute
    final var result = ImmutableChainedNode.fromString(string);

    // verify
    expect(result).hasStringRepresentation(string);
  }

  @Test
  void testCase_withHeader_whenNullHeaderIsGiven() {
    // execute & verify
    expectRunning(() -> ImmutableChainedNode.withHeader(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given header is null.");
  }

  @Test
  void testCase_withHeader_whenHeaderIsGiven() {
    // execute
    final var result = ImmutableChainedNode.withHeader("a");

    // verify
    expect(result).hasStringRepresentation("a");
  }
}
