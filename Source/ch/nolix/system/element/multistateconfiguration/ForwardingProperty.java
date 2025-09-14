package ch.nolix.system.element.multistateconfiguration;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.document.node.INode;

public final class ForwardingProperty<S extends Enum<S>, V> extends AbstractProperty<S> {
  private final IContainer<AbstractMaterializedProperty<S, V>> abstractMaterializedProperties;

  @SafeVarargs
  private ForwardingProperty(
    final String name,
    final AbstractMaterializedProperty<S, V> materializedProperty,
    final AbstractMaterializedProperty<S, V>... materializedProperties) {
    this(name, ImmutableList.withElement(materializedProperty, materializedProperties));
  }

  private ForwardingProperty(
    final String name,
    final IContainer<AbstractMaterializedProperty<S, V>> materializedProperties) {
    super(name);

    this.abstractMaterializedProperties = LinkedList.fromIterable(materializedProperties);
  }

  @SafeVarargs
  public static <S2 extends Enum<S2>, V2> ForwardingProperty<S2, V2> withNameAndForProperty(
    final String name,
    final AbstractMaterializedProperty<S2, V2> materializedProperty,
    final AbstractMaterializedProperty<S2, V2>... materializedProperties) {
    return new ForwardingProperty<>(name, materializedProperty, materializedProperties);
  }

  @Override
  public void setUndefined() {
    abstractMaterializedProperties.forEach(AbstractMaterializedProperty::setUndefined);
  }

  public void setValueForState(final S state, final V value) {
    for (final var p : abstractMaterializedProperties) {
      p.setValueForState(state, value);
    }
  }

  @Override
  protected void fillUpValuesSpecificationInto(ILinkedList<INode<?>> list) {
    //Does nothing.
  }

  @Override
  protected void setFrom(AbstractProperty<S> property) {
    //Does nothing.
  }

  @Override
  protected void setValueFromSpecification(final INode<?> specification) {
    for (final var p : abstractMaterializedProperties) {
      p.setValueFromSpecification(specification);
    }
  }
}
