/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.element.base;

import org.junit.jupiter.api.Test;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.foundation.util.VoidObject;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.system.element.base.AbstractElement;

/**
 * @author Silvan Wyss
 */
final class EqualsMethodTest extends StandardTest {
  @Test
  void testCase_equals_whenTheGivenObjectIsNull() {
    // setup
    final var testUnit = new AbstractElement() {
      @Override
      public ExtendedIterable<Node<?>> getAttributes() {
        return ImmutableList.withElements(ImmutableNode.withHeader("my_flag"));
      }
    };

    // execute
    final var result = testUnit.equals(null);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_equals_whenTheGivenObjectIsAVoidObject() {
    // setup
    final Object object = new VoidObject();
    final var testUnit = new AbstractElement() {
      @Override
      public ExtendedIterable<Node<?>> getAttributes() {
        return ImmutableList.withElements(ImmutableNode.withHeader("my_flag"));
      }
    };

    // execute
    final var result = testUnit.equals(object);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_equals_whenTheGivenObjectIsTheSame() {
    // setup
    final var testUnit = new AbstractElement() {
      @Override
      public ExtendedIterable<Node<?>> getAttributes() {
        return ImmutableList.withElements(ImmutableNode.withHeader("my_flag"));
      }
    };

    // execute
    final var result = testUnit.equals(testUnit);

    // verify
    expect(result).isTrue();
  }
}
