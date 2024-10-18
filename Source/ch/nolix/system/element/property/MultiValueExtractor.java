package ch.nolix.system.element.property;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;
import ch.nolix.systemapi.elementapi.propertyapi.IProperty;

public final class MultiValueExtractor<V> implements IProperty, INameHolder {

  private final String name;

  private final Consumer<V> adder;

  private final Supplier<IContainer<V>> getter;

  private final Function<INode<?>, V> valueCreator;

  private final Function<V, INode<?>> specificationCreator;

  public MultiValueExtractor(
    final String name,
    final Consumer<V> adder,
    final Supplier<IContainer<V>> getter,
    final Function<INode<?>, V> valueCreator,
    final Function<V, INode<?>> specificationCreator) {

    GlobalValidator.assertThat(name).thatIsNamed(PascalCaseVariableCatalogue.NAME).isNotBlank();
    GlobalValidator.assertThat(adder).thatIsNamed("adder").isNotNull();
    GlobalValidator.assertThat(getter).thatIsNamed("getter").isNotNull();
    GlobalValidator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
    GlobalValidator.assertThat(specificationCreator).thatIsNamed("specification creator").isNotNull();

    this.name = name;
    this.adder = adder;
    this.getter = getter;
    this.valueCreator = valueCreator;
    this.specificationCreator = specificationCreator;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean addedOrChangedAttribute(final INode<?> attribute) {

    if (attribute.hasHeader(getName())) {
      adder.accept(valueCreator.apply(attribute));
      return true;
    }

    return false;
  }

  @Override
  public void fillUpAttributesInto(final ILinkedList<INode<?>> list) {
    for (final var v : getter.get()) {
      list.addAtEnd(specificationCreator.apply(v));
    }
  }
}
