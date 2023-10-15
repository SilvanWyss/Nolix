//package declaration
package ch.nolix.system.element.property;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;
import ch.nolix.systemapi.elementapi.propertyapi.IProperty;

//class
public final class MutableOptionalValueExtractor<V> implements IProperty, Named {

  // attribute
  private final String name;

  // attribute
  private final Consumer<V> setter;

  // attribute
  private final BooleanSupplier valuePresenceChecker;

  // attribute
  private final Supplier<V> getter;

  // attribute
  private final IElementTakerElementGetter<INode<?>, V> valueCreator;

  // attribute
  private final IElementTakerElementGetter<V, INode<?>> specificationCreator;

  // constructor
  public MutableOptionalValueExtractor(
      final String name,
      final Consumer<V> setter,
      final BooleanSupplier valuePresenceChecker,
      final Supplier<V> getter,
      final IElementTakerElementGetter<INode<?>, V> valueCreator,
      final IElementTakerElementGetter<V, INode<?>> specificationCreator) {

    GlobalValidator.assertThat(name).thatIsNamed(PascalCaseCatalogue.NAME).isNotBlank();
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

  // method
  @Override
  public String getName() {
    return name;
  }

  // method
  @Override
  public boolean addedOrChangedAttribute(final INode<?> attribute) {

    if (attribute.hasHeader(getName())) {
      setter.accept(valueCreator.getOutput(attribute));
      return true;
    }

    return false;
  }

  // method
  @Override
  public void fillUpAttributesInto(final ILinkedList<INode<?>> list) {
    if (valuePresenceChecker.getAsBoolean()) {
      list.addAtEnd(specificationCreator.getOutput(getter.get()));
    }
  }
}
