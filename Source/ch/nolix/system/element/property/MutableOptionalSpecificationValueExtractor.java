package ch.nolix.system.element.property;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.programatom.variable.PascalCaseVariableCatalog;
import ch.nolix.systemapi.element.property.IProperty;

public final class MutableOptionalSpecificationValueExtractor implements IProperty, INameHolder {

  private final String name;

  private final Consumer<INode<?>> setter;

  private final BooleanSupplier valuePresenceChecker;

  private final Supplier<Node> getter;

  public MutableOptionalSpecificationValueExtractor(
    final String name,
    final Consumer<INode<?>> setter,
    final BooleanSupplier valuePresenceChecker,
    final Supplier<Node> getter) {

    Validator.assertThat(name).thatIsNamed(PascalCaseVariableCatalog.NAME).isNotBlank();
    Validator.assertThat(setter).thatIsNamed("setter").isNotNull();
    Validator.assertThat(valuePresenceChecker).thatIsNamed("value presence checker").isNotNull();
    Validator.assertThat(getter).thatIsNamed("getter").isNotNull();

    this.name = name;
    this.setter = setter;
    this.valuePresenceChecker = valuePresenceChecker;
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
    if (valuePresenceChecker.getAsBoolean()) {
      list.addAtEnd(getter.get());
    }
  }
}
