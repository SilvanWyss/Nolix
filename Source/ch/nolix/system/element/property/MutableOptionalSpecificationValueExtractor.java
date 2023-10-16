//package declaration
package ch.nolix.system.element.property;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.propertyapi.IProperty;

//class
public final class MutableOptionalSpecificationValueExtractor implements IProperty, Named {

  //attribute
  private final String name;

  //attribute
  private final Consumer<INode<?>> setter;

  //attribute
  private final BooleanSupplier valuePresenceChecker;

  //attribute
  private final Supplier<Node> getter;

  //constructor
  public MutableOptionalSpecificationValueExtractor(
      final String name,
      final Consumer<INode<?>> setter,
      final BooleanSupplier valuePresenceChecker,
      final Supplier<Node> getter) {

    GlobalValidator.assertThat(name).thatIsNamed(PascalCaseCatalogue.NAME).isNotBlank();
    GlobalValidator.assertThat(setter).thatIsNamed("setter").isNotNull();
    GlobalValidator.assertThat(valuePresenceChecker).thatIsNamed("value presence checker").isNotNull();
    GlobalValidator.assertThat(getter).thatIsNamed("getter").isNotNull();

    this.name = name;
    this.setter = setter;
    this.valuePresenceChecker = valuePresenceChecker;
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
      setter.accept(attribute);
      return true;
    }

    return false;
  }

  //method
  @Override
  public void fillUpAttributesInto(final ILinkedList<INode<?>> list) {
    if (valuePresenceChecker.getAsBoolean()) {
      list.addAtEnd(getter.get());
    }
  }
}
