//package declaration
package ch.nolix.system.element.property;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.systemapi.elementapi.propertyapi.IProperty;

//class
public final class MultiSpecificationValueExtractor implements IProperty, Named {

  // attribute
  private final String name;

  // attribute
  private final IElementTaker<INode<?>> adder;

  // attribute
  private final IElementGetter<IContainer<INode<?>>> getter;

  // constructor
  public MultiSpecificationValueExtractor(
      final String name,
      final IElementTaker<INode<?>> adder,
      final IElementGetter<IContainer<INode<?>>> getter) {

    GlobalValidator.assertThat(name).thatIsNamed(PascalCaseCatalogue.NAME).isNotBlank();
    GlobalValidator.assertThat(adder).thatIsNamed("adder").isNotNull();
    GlobalValidator.assertThat(getter).thatIsNamed("getter").isNotNull();

    this.name = name;
    this.adder = adder;
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
      adder.run(attribute);
      return true;
    }

    return false;
  }

  // method
  @Override
  public void fillUpAttributesInto(final ILinkedList<INode<?>> list) {
    list.addAtEnd(getter.getOutput());
  }
}
