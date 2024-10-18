package ch.nolix.system.element.property;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;
import ch.nolix.systemapi.elementapi.propertyapi.IProperty;

public final class MutableOptionalValueExtractor<V> implements IProperty, INameHolder {

  private final String name;

  private final Consumer<V> setter;

  private final BooleanSupplier valuePresenceChecker;

  private final Supplier<V> getter;

  private final Function<INode<?>, V> valueCreator;

  private final Function<V, INode<?>> specificationCreator;

  public MutableOptionalValueExtractor(
    final String name,
    final Consumer<V> setter,
    final BooleanSupplier valuePresenceChecker,
    final Supplier<V> getter,
    final Function<INode<?>, V> valueCreator,
    final Function<V, INode<?>> specificationCreator) {

    GlobalValidator.assertThat(name).thatIsNamed(PascalCaseVariableCatalogue.NAME).isNotBlank();
    GlobalValidator.assertThat(setter).thatIsNamed("setter").isNotNull();
    GlobalValidator.assertThat(valuePresenceChecker).thatIsNamed("value presence checker").isNotNull();
    GlobalValidator.assertThat(getter).thatIsNamed("getter").isNotNull();
    GlobalValidator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
    GlobalValidator.assertThat(specificationCreator).thatIsNamed("specification creator").isNotNull();

    this.name = name;
    this.setter = setter;
    this.valuePresenceChecker = valuePresenceChecker;
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
      setter.accept(valueCreator.apply(attribute));
      return true;
    }

    return false;
  }

  @Override
  public void fillUpAttributesInto(final ILinkedList<INode<?>> list) {
    if (valuePresenceChecker.getAsBoolean()) {
      list.addAtEnd(specificationCreator.apply(getter.get()));
    }
  }
}
