//package declaration
package ch.nolix.system.element.multistateconfiguration;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;

//class
public abstract class Property<S extends Enum<S>> implements INameHolder {

  //attribute
  private final String name;

  //attribute
  protected MultiStateConfiguration<?, S> parent;

  //constructor
  protected Property(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();

    this.name = name;
  }

  //method
  @Override
  public final String getName() {
    return name;
  }

  //method declaration
  protected abstract void fillUpValuesSpecificationInto(ILinkedList<INode<?>> list);

  //method declaration
  protected abstract void setFrom(Property<S> property);

  //method declaration
  protected abstract void setUndefined();

  //method declaration
  protected abstract void setValueFromSpecification(INode<?> specification);

  //method
  final void setParent(final MultiStateConfiguration<?, S> parent) {

    GlobalValidator.assertThat(parent).thatIsNamed(LowerCaseCatalogue.PARENT).isNotNull();

    this.parent = parent;
  }
}
