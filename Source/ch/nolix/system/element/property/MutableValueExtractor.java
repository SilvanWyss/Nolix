package ch.nolix.system.element.property;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.systemapi.element.property.IProperty;

/**
 * @author Silvan Wyss
 * @param <V> is the type of the valeus a {@link MutableValueExtractor}
 *            extracts.
 */
public final class MutableValueExtractor<V> implements IProperty, INameHolder {
  private final String name;

  private final Consumer<V> setter;

  private final Supplier<V> getter;

  private final Function<INode<?>, V> valueCreator;

  private final Function<V, INode<?>> specificationCreator;

  public MutableValueExtractor(
    final String name,
    final Consumer<V> setter,
    final Supplier<V> getter,
    final Function<INode<?>, V> valueCreator,
    final Function<V, INode<?>> specificationCreator) {
    Validator.assertThat(name).thatIsNamed(PascalCaseVariableCatalog.NAME).isNotBlank();
    Validator.assertThat(setter).thatIsNamed("setter").isNotNull();
    Validator.assertThat(getter).thatIsNamed("getter").isNotNull();
    Validator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
    Validator.assertThat(specificationCreator).thatIsNamed("specification creator").isNotNull();

    this.name = name;
    this.setter = setter;
    this.getter = getter;
    this.valueCreator = valueCreator;
    this.specificationCreator = specificationCreator;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean addedOrChangedAttribute(final INode<?> attribute) {
    if (attribute.hasHeader(getName())) {
      setter.accept(valueCreator.apply(attribute));
      return true;
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillUpAttributesInto(final ILinkedList<INode<?>> list) {
    list.addAtEnd(specificationCreator.apply(getter.get()));
  }
}
