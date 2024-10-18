package ch.nolix.system.element.property;

import java.util.function.Consumer;
import java.util.function.Function;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.elementapi.propertyapi.IProperty;

public final class CatchingProperty<V> implements IProperty, INameHolder {

  private final String name;

  private final Consumer<V> setter;

  private final Function<INode<?>, V> valueCreator;

  public CatchingProperty(
    final String name,
    final Consumer<V> setter,
    final Function<INode<?>, V> valueCreator) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();
    GlobalValidator.assertThat(setter).thatIsNamed("setter").isNotNull();
    GlobalValidator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();

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
