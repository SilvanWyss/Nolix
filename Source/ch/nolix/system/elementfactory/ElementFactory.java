//package declaration
package ch.nolix.system.elementfactory;

import java.util.function.Function;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.mainapi.IMutableElement;

//class
public class ElementFactory<E> {

  // multi-attribute
  private final LinkedList<OneTypeElementFactory<?>> oneTypeElementFactories = new LinkedList<>();

  // method
  public final boolean canCreateElementFrom(final BaseNode<?> specification) {
    return oneTypeElementFactories.containsAny(otef -> otef.canCreateElementFrom(specification));
  }

  // method
  public final <E2 extends E> boolean canCreateElementOf(final Class<E2> type) {
    return oneTypeElementFactories.containsAny(otef -> otef.canCreateElementOf(type));
  }

  // method
  public final boolean canCreateElementOf(final String type) {
    return oneTypeElementFactories.containsAny(oeef -> oeef.canCreateElementOf(type));
  }

  // method
  @SuppressWarnings("unchecked")
  public <E2 extends E> E2 createElementFrom(final BaseNode<?> specification) {
    return (E2) getOneTypeElementFactoryFor(specification).createElementFrom(specification);
  }

  // method
  protected final <E2 extends E, ME extends IMutableElement> void registerElementClass_(
      final Class<ME> elementClass) {
    registerOneTypeElementFactory(new OneTypeElementFactory<E2>(elementClass));
  }

  // method
  protected final <E2 extends E> void registerElementClass_(
      final Class<E2> elementClass,
      final Function<INode<?>, E2> creator) {
    registerOneTypeElementFactory(new OneTypeElementFactory<>(elementClass, creator));
  }

  // method
  private <E2 extends E> void assertCannotCreateElementOf(final Class<E2> type) {
    if (canCreateElementOf(type)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "can already create a " + type.getName());
    }
  }

  // method
  private OneTypeElementFactory<?> getOneTypeElementFactoryFor(final BaseNode<?> specification) {
    return getOneTypeElementFactoryFor(specification.getHeader());
  }

  // method
  private OneTypeElementFactory<?> getOneTypeElementFactoryFor(final String type) {
    return oneTypeElementFactories.getStoredFirst(otef -> otef.canCreateElementOf(type));
  }

  // method
  private <E2 extends E> void registerOneTypeElementFactory(final OneTypeElementFactory<E2> oneTypeElementFactory) {

    assertCannotCreateElementOf(oneTypeElementFactory.internalGetElementClass());

    oneTypeElementFactories.addAtEnd(oneTypeElementFactory);
  }
}
