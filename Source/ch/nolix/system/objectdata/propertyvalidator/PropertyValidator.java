//package declaration
package ch.nolix.system.objectdata.propertyvalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.fieldtool.FieldTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IFieldTool;
import ch.nolix.systemapi.objectdataapi.propertyvalidatorapi.IPropertyValidator;

//class
public class PropertyValidator implements IPropertyValidator {

  //constant
  private static final IFieldTool PROPERTY_TOOL = new FieldTool();

  //method
  @Override
  public final void assertBelongsToEntity(final IField field) {
    if (!field.belongsToEntity()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(field, IEntity.class);
    }
  }

  //method
  @Override
  public final void assertDoesNotBelongToEntity(final IField field) {
    if (field.belongsToEntity()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(field, field.getStoredParentEntity());
    }
  }

  //method
  @Override
  public final void assertIsNotEmpty(final IField field) {
    if (field.isEmpty()) {
      throw EmptyArgumentException.forArgument(field);
    }
  }

  //method
  @Override
  public final void assertIsNotMandatoryAndEmptyBoth(final IField field) {
    if (PROPERTY_TOOL.isMandatoryAndEmptyBoth(field)) {
      throw EmptyArgumentException.forArgument(field);
    }
  }

  //method
  @Override
  public final void assertKnowsParentColumn(final IField field) {
    if (!field.knowsParentColumn()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(field, "does not know its parent column");
    }
  }
}
