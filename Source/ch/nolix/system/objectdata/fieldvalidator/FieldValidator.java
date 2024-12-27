package ch.nolix.system.objectdata.fieldvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.fieldtool.FieldTool;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IFieldTool;
import ch.nolix.systemapi.objectdataapi.fieldvalidatorapi.IFieldValidator;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;

public class FieldValidator implements IFieldValidator {

  private static final IFieldTool FIELD_TOOL = new FieldTool();

  @Override
  public final void assertBelongsToEntity(final IField field) {
    if (!field.belongsToEntity()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(field, IEntity.class);
    }
  }

  @Override
  public final void assertDoesNotBelongToEntity(final IField field) {
    if (field.belongsToEntity()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(field, field.getStoredParentEntity());
    }
  }

  @Override
  public final void assertIsNotEmpty(final IField field) {
    if (field.isEmpty()) {
      throw EmptyArgumentException.forArgument(field);
    }
  }

  @Override
  public final void assertIsNotMandatoryAndEmptyBoth(final IField field) {
    if (FIELD_TOOL.isMandatoryAndEmptyBoth(field)) {
      throw EmptyArgumentException.forArgument(field);
    }
  }

  @Override
  public final void assertKnowsParentColumn(final IField field) {
    if (!field.knowsParentColumn()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(field, "does not know its parent column");
    }
  }
}
