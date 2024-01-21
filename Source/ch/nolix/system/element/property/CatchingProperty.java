//package declaration
package ch.nolix.system.element.property;

//Java imports
import java.util.function.Consumer;
import java.util.function.Function;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.elementapi.propertyapi.IProperty;

//class
public final class CatchingProperty<V> implements IProperty, INameHolder {

  //attribute
  private final String name;

  //attribute
  private final Consumer<V> setter;

  //attribute
  private final Function<INode<?>, V> valueCreator;

  //constructor
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

  //method
  @Override
  public String getName() {
    return name;
  }

  //method
  @Override
  public boolean addedOrChangedAttribute(INode<?> attribute) {

    if (hasName(attribute.getHeader())) {
      setter.accept(valueCreator.apply(attribute));
      return true;
    }

    return false;
  }

  //method
  @Override
  public void fillUpAttributesInto(final ILinkedList<INode<?>> list) {
    //Does nothing.
  }
}
