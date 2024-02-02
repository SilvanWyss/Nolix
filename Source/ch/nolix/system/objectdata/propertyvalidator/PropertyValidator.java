//package declaration
package ch.nolix.system.objectdata.propertyvalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.propertytool.PropertyTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.objectdataapi.propertytoolapi.IPropertyTool;
import ch.nolix.systemapi.objectdataapi.propertyvalidatorapi.IPropertyValidator;

//class
public class PropertyValidator implements IPropertyValidator {

  //constant
  private static final IPropertyTool PROPERTY_TOOL = new PropertyTool();

  //method
  @Override
  public final void assertBelongsToEntity(final IProperty property) {
    if (!property.belongsToEntity()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(property, IEntity.class);
    }
  }

  //method
  @Override
  public final void assertDoesNotBelongToEntity(final IProperty property) {
    if (property.belongsToEntity()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(property, property.getStoredParentEntity());
    }
  }

  //method
  @Override
  public final void assertIsNotEmpty(final IProperty property) {
    if (property.isEmpty()) {
      throw EmptyArgumentException.forArgument(property);
    }
  }

  //method
  @Override
  public final void assertIsNotMandatoryAndEmptyBoth(final IProperty property) {
    if (PROPERTY_TOOL.isMandatoryAndEmptyBoth(property)) {
      throw EmptyArgumentException.forArgument(property);
    }
  }

  //method
  @Override
  public final void assertKnowsParentColumn(final IProperty property) {
    if (!property.knowsParentColumn()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(property, "does not know its parent column");
    }
  }
}
