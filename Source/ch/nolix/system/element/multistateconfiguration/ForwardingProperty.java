package ch.nolix.system.element.multistateconfiguration;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

public final class ForwardingProperty<S extends Enum<S>, V> extends Property<S> {

  private final IContainer<MaterializedProperty<S, V>> materializedProperties;

  @SafeVarargs
  private ForwardingProperty(
    final String name,
    final MaterializedProperty<S, V> materializedProperty,
    final MaterializedProperty<S, V>... materializedProperties) {
    this(name, ImmutableList.withElement(materializedProperty, materializedProperties));
  }

  private ForwardingProperty(final String name, final IContainer<MaterializedProperty<S, V>> materializedProperties) {

    super(name);

    this.materializedProperties = LinkedList.fromIterable(materializedProperties);
  }

  @SafeVarargs
  public static <S2 extends Enum<S2>, V2> ForwardingProperty<S2, V2> withNameAndForProperty(
    final String name,
    final MaterializedProperty<S2, V2> materializedProperty,
    final MaterializedProperty<S2, V2>... materializedProperties) {
    return new ForwardingProperty<>(name, materializedProperty, materializedProperties);
  }

  @Override
  public void setUndefined() {
    materializedProperties.forEach(MaterializedProperty::setUndefined);
  }

  public void setValueForState(final S state, final V value) {
    for (final var mp : materializedProperties) {
      mp.setValueForState(state, value);
    }
  }

  @Override
  protected void fillUpValuesSpecificationInto(ILinkedList<INode<?>> list) {
    //Does nothing.
  }

  @Override
  protected void setFrom(Property<S> property) {
    //Does nothing.
  }

  @Override
  protected void setValueFromSpecification(final INode<?> specification) {
    for (final var mp : materializedProperties) {
      mp.setValueFromSpecification(specification);
    }
  }
}
