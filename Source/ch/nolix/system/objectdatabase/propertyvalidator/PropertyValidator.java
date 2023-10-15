//package declaration
package ch.nolix.system.objectdatabase.propertyvalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdatabase.propertyhelper.PropertyHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IPropertyHelper;
import ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi.IPropertyValidator;

//class
public class PropertyValidator implements IPropertyValidator {

  // constant
  private static final IPropertyHelper PROPERTY_HELPER = new PropertyHelper();

  // method
  @Override
  public final void assertBelongsToEntity(final IProperty property) {
    if (!property.belongsToEntity()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(property, IEntity.class);
    }
  }

  // method
  @Override
  public final void assertDoesNotBelongToEntity(final IProperty property) {
    if (property.belongsToEntity()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(property, property.getStoredParentEntity());
    }
  }

  // method
  @Override
  public final void assertIsNotEmpty(final IProperty property) {
    if (property.isEmpty()) {
      throw EmptyArgumentException.forArgument(property);
    }
  }

  // method
  @Override
  public final void assertIsNotMandatoryAndEmptyBoth(final IProperty property) {
    if (PROPERTY_HELPER.isMandatoryAndEmptyBoth(property)) {
      throw EmptyArgumentException.forArgument(property);
    }
  }

  // method
  @Override
  public final void assertKnowsParentColumn(final IProperty property) {
    if (!property.knowsParentColumn()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(property, "does not know its parent column");
    }
  }
}
