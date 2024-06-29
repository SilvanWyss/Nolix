//package declaration
package ch.nolix.system.element.property;

//Java imports
import java.util.function.Function;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.stateapi.staterequestapi.MutabilityRequestable;
import ch.nolix.systemapi.elementapi.propertyapi.IBaseValue;

//class
/**
 * @author Silvan Wyss
 * @version 2017-10-29
 * @param <V> is the type of the values of a {@link BaseValue}.
 */
public abstract class BaseValue<V> implements IBaseValue, MutabilityRequestable, INameHolder {

  //attribute
  private final String name;

  //attribute
  private final Function<INode<?>, V> valueCreator;

  //attribute
  protected final Function<V, INode<?>> specificationCreator;

  //constructor
  /**
   * Creates a new {@link BaseValue} with the given name, valueCreator and
   * specificationCreator.
   * 
   * @param name
   * @param valueCreator
   * @param specificationCreator
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws ArgumentIsNullException  if the given valueCreator is null.
   * @throws ArgumentIsNullException  if the given specificationCreator is null.
   */
  BaseValue(
    final String name,
    final Function<INode<?>, V> valueCreator,
    final Function<V, INode<?>> specificationCreator) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();
    GlobalValidator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
    GlobalValidator.assertThat(specificationCreator).thatIsNamed("specificaiton creator").isNotNull();

    this.name = name;
    this.valueCreator = valueCreator;
    this.specificationCreator = specificationCreator;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final String getName() {
    return name;
  }

  //method
  /**
   * Adds or changes the value from the given attribute to the current
   * {@link BaseValue}.
   * 
   * @param attribute
   */
  @Override
  public final boolean addedOrChangedAttribute(final INode<?> attribute) {

    if (attribute.hasHeader(getName())) {
      addOrChangeValue(valueCreator.apply(attribute));
      return true;
    }

    return false;
  }

  //method declaration
  /**
   * Adds or change the given value to the current {@link BaseValue}.
   * 
   * @param value
   */
  protected abstract void addOrChangeValue(V value);
}
