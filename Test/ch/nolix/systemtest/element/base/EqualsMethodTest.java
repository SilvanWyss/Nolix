package ch.nolix.systemtest.element.base;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.misc.dataobject.VoidObject;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.system.element.base.AbstractElement;

final class EqualsMethodTest extends StandardTest {
  @Test
  void testCase_equals_whenTheGivenObjectIsNull() {
    //setup
    final var testUnit = new AbstractElement() {
      @Override
      public IContainer<INode<?>> getAttributes() {
        return ImmutableList.withElement(Node.withHeader("my_flag"));
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
      public IContainer<INode<?>> getAttributes() {
        return ImmutableList.withElement(Node.withHeader("my_flag"));
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
      public IContainer<INode<?>> getAttributes() {
        return ImmutableList.withElement(Node.withHeader("my_flag"));
      }
    };

    //execution
    final var result = testUnit.equals(testUnit);

    //verification
    expect(result).isTrue();
  }
}
