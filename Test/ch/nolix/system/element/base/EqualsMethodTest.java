package ch.nolix.system.element.base;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

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
