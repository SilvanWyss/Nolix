//package declaration
package ch.nolix.system.element.property;

import java.util.function.Consumer;
import java.util.function.Supplier;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.propertyapi.IProperty;

//class
public final class MultiSpecificationValueExtractor implements IProperty, INameHolder {

  //attribute
  private final String name;

  //attribute
  private final Consumer<INode<?>> adder;

  //attribute
  private final Supplier<IContainer<INode<?>>> getter;

  //constructor
  public MultiSpecificationValueExtractor(
    final String name,
    final Consumer<INode<?>> adder,
    final Supplier<IContainer<INode<?>>> getter) {

    GlobalValidator.assertThat(name).thatIsNamed(PascalCaseCatalogue.NAME).isNotBlank();
    GlobalValidator.assertThat(adder).thatIsNamed("adder").isNotNull();
    GlobalValidator.assertThat(getter).thatIsNamed("getter").isNotNull();

    this.name = name;
    this.adder = adder;
    this.getter = getter;
  }

  //method
  @Override
  public String getName() {
    return name;
  }

  //method
  @Override
  public boolean addedOrChangedAttribute(final INode<?> attribute) {

    if (attribute.hasHeader(getName())) {
      adder.accept(attribute);
      return true;
    }

    return false;
  }

  //method
  @Override
  public void fillUpAttributesInto(final ILinkedList<INode<?>> list) {
    list.addAtEnd(getter.get());
  }
}
