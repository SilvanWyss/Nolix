/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.element.base;

import org.junit.jupiter.api.Test;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.system.element.base.AbstractElement;

/**
 * @author Silvan Wyss
 */
final class ElementTest extends StandardTest {
  @Test
  void testCase_hashCode() {
    // setup
    final var testUnit = new AbstractElement() {
      @Override
      public ExtendedIterable<Node<?>> getAttributes() {
        return ImmutableList.withElements(ImmutableNode.withHeader("my_flag"));
      }
    };

    // verify setup
    final var expectedResult = testUnit.getSpecification().hashCode();

    // execute
    final var result = testUnit.hashCode();

    // verify
    expect(result).isEqualTo(expectedResult);
  }
}
