//package declaration
package ch.nolix.system.element.property;

import java.util.function.Supplier;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.systemapi.elementapi.propertyapi.IProperty;

//class
public final class MutableSpecificationValueExtractor implements IProperty, Named {

  // attribute
  private final String name;

  // attribute
  private final IElementTaker<INode<?>> setter;

  // attribute
  private final Supplier<INode<?>> getter;

  // constructor
  public MutableSpecificationValueExtractor(
      final String name,
      final IElementTaker<INode<?>> setter,
      final Supplier<INode<?>> getter) {

    GlobalValidator.assertThat(name).thatIsNamed(PascalCaseCatalogue.NAME).isNotBlank();
    GlobalValidator.assertThat(setter).thatIsNamed("setter").isNotNull();
    GlobalValidator.assertThat(getter).thatIsNamed("getter").isNotNull();

    this.name = name;
    this.setter = setter;
    this.getter = getter;
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
      setter.run(attribute);
      return true;
    }

    return false;
  }

  // method
  @Override
  public void fillUpAttributesInto(final ILinkedList<INode<?>> list) {
    list.addAtEnd(getter.get());
  }
}
