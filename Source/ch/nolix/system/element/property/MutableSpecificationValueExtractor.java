package ch.nolix.system.element.property;

import java.util.function.Consumer;
import java.util.function.Supplier;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;
import ch.nolix.systemapi.elementapi.propertyapi.IProperty;

public final class MutableSpecificationValueExtractor implements IProperty, INameHolder {

  private final String name;

  private final Consumer<INode<?>> setter;

  private final Supplier<INode<?>> getter;

  public MutableSpecificationValueExtractor(
    final String name,
    final Consumer<INode<?>> setter,
    final Supplier<INode<?>> getter) {

    GlobalValidator.assertThat(name).thatIsNamed(PascalCaseVariableCatalog.NAME).isNotBlank();
    GlobalValidator.assertThat(setter).thatIsNamed("setter").isNotNull();
    GlobalValidator.assertThat(getter).thatIsNamed("getter").isNotNull();

    this.name = name;
    this.setter = setter;
    this.getter = getter;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean addedOrChangedAttribute(final INode<?> attribute) {

    if (attribute.hasHeader(getName())) {
      setter.accept(attribute);
      return true;
    }

    return false;
  }

  @Override
  public void fillUpAttributesInto(final ILinkedList<INode<?>> list) {
    list.addAtEnd(getter.get());
  }
}
