/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.element.base;

import org.junit.jupiter.api.Test;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.document.node.Node;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.system.element.base.AbstractElement;

/**
 * @author Silvan Wyss
 */
final class ElementTest extends StandardTest {
  @Test
  void testCase_hashCode() {
    //setup
    final var testUnit = new AbstractElement() {
      @Override
      public IWellOrderContainer<INode<?>> getAttributes() {
        return ImmutableList.withElements(Node.withHeader("my_flag"));
      }
    };

    //verification setup
    final var expectedResult = testUnit.getSpecification().hashCode();

    //execution
    final var result = testUnit.hashCode();

    //verification
    expect(result).isEqualTo(expectedResult);
  }
}
