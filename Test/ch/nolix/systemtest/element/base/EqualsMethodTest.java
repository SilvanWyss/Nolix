/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.element.base;

import org.junit.jupiter.api.Test;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.document.node.Node;
import ch.nolix.base.misc.dataobject.VoidObject;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.system.element.base.AbstractElement;

/**
 * @author Silvan Wyss
 */
final class EqualsMethodTest extends StandardTest {
  @Test
  void testCase_equals_whenTheGivenObjectIsNull() {
    //setup
    final var testUnit = new AbstractElement() {
      @Override
      public IWellOrderContainer<INode<?>> getAttributes() {
        return ImmutableList.withElements(Node.withHeader("my_flag"));
      }
    };

    //execution
    final var result = testUnit.equals(null);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_equals_whenTheGivenObjectIsAVoidObject() {
    //setup
    final Object object = new VoidObject();
    final var testUnit = new AbstractElement() {
      @Override
      public IWellOrderContainer<INode<?>> getAttributes() {
        return ImmutableList.withElements(Node.withHeader("my_flag"));
      }
    };

    //execution
    final var result = testUnit.equals(object);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_equals_whenTheGivenObjectIsTheSame() {
    //setup
    final var testUnit = new AbstractElement() {
      @Override
      public IWellOrderContainer<INode<?>> getAttributes() {
        return ImmutableList.withElements(Node.withHeader("my_flag"));
      }
    };

    //execution
    final var result = testUnit.equals(testUnit);

    //verification
    expect(result).isTrue();
  }
}
