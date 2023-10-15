//package declaration
package ch.nolix.system.element.property;

import java.util.function.Consumer;
import java.util.function.Supplier;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;
import ch.nolix.systemapi.elementapi.propertyapi.IProperty;

//class
public final class MultiValueExtractor<V> implements IProperty, Named {

  // attribute
  private final String name;

  // attribute
  private final Consumer<V> adder;

  // attribute
  private final Supplier<IContainer<V>> getter;

  // attribute
  private final IElementTakerElementGetter<INode<?>, V> valueCreator;

  // attribute
  private final IElementTakerElementGetter<V, INode<?>> specificationCreator;

  // constructor
  public MultiValueExtractor(
      final String name,
      final Consumer<V> adder,
      final Supplier<IContainer<V>> getter,
      final IElementTakerElementGetter<INode<?>, V> valueCreator,
      final IElementTakerElementGetter<V, INode<?>> specificationCreator) {

    GlobalValidator.assertThat(name).thatIsNamed(PascalCaseCatalogue.NAME).isNotBlank();
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

  // method
  @Override
  public String getName() {
    return name;
  }

  // method
  @Override
  public boolean addedOrChangedAttribute(final INode<?> attribute) {

    if (attribute.hasHeader(getName())) {
      adder.accept(valueCreator.getOutput(attribute));
      return true;
    }

    return false;
  }

  // method
  @Override
  public void fillUpAttributesInto(final ILinkedList<INode<?>> list) {
    for (final var v : getter.get()) {
      list.addAtEnd(specificationCreator.getOutput(v));
    }
  }
}
