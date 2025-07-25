package ch.nolix.system.element.property;

import java.util.function.Consumer;
import java.util.function.Function;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.programatom.variable.LowerCaseVariableCatalog;
import ch.nolix.systemapi.elementapi.propertyapi.IProperty;

public final class CatchingProperty<V> implements IProperty, INameHolder {

  private final String name;

  private final Consumer<V> setter;

  private final Function<INode<?>, V> valueCreator;

  public CatchingProperty(
    final String name,
    final Consumer<V> setter,
    final Function<INode<?>, V> valueCreator) {

    Validator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();
    Validator.assertThat(setter).thatIsNamed("setter").isNotNull();
    Validator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();

    this.name = name;
    this.setter = setter;
    this.valueCreator = valueCreator;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean addedOrChangedAttribute(INode<?> attribute) {

    if (hasName(attribute.getHeader())) {
      setter.accept(valueCreator.apply(attribute));
      return true;
    }

    return false;
  }

  @Override
  public void fillUpAttributesInto(final ILinkedList<INode<?>> list) {
    //Does nothing.
  }
}
