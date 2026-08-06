/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.schemamapper;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.baseapi.datamodel.fieldproperty.DataType;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectschema.model.Column;
import ch.nolix.systemapi.objectdata.model.BaseBackReference;
import ch.nolix.systemapi.objectdata.model.BaseReference;
import ch.nolix.systemapi.objectdata.model.BaseValueField;
import ch.nolix.systemapi.objectdata.model.Field;
import ch.nolix.systemapi.objectdata.schemamapper.IColumnMapper;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public final class ColumnMapper implements IColumnMapper {
  @Override
  public IColumn mapFieldToColumn(final Field field, final String columnId, final ExtendedIterable<ITable> tables) {
    if (field instanceof final BaseValueField<?> baseValueField) {
      return //
      Column.withIdAndNameAndContentModel(
        columnId,
        field.getName(),
        field.getType(),
        DataType.forType(baseValueField.getValueType()),
        ImmutableList.createEmpty(),
        ImmutableList.createEmpty());
    }

    if (field instanceof final BaseReference baseReference) {
      final var referenceableTableNames = baseReference.getReferenceableTableNames();
      final var referenceableTables = tables.getStoredSelected(t -> referenceableTableNames.containsAny(t.getName()));

      return //
      Column.withIdAndNameAndContentModel(
        columnId,
        field.getName(),
        field.getType(),
        DataType.STRING,
        referenceableTables,
        ImmutableList.createEmpty());
    }

    if (field instanceof final BaseBackReference baseBackReference) {
      final var backReferencedFieldName = baseBackReference.getBackReferencedFieldName();
      final var backReferenceableTableNames = baseBackReference.getBackReferenceableTableNames();

      final var backReferenceableTables = //
      tables.getStoredSelected(t -> backReferenceableTableNames.contains(t.getName()));

      final var backReferenceableColumns = //
      backReferenceableTables.to(t -> t.getStoredColumns().getStoredFirst(c -> c.hasName(backReferencedFieldName)));

      return //
      Column.withIdAndNameAndContentModel(
        columnId,
        field.getName(),
        field.getType(),
        DataType.STRING,
        ImmutableList.createEmpty(),
        backReferenceableColumns);
    }

    throw InvalidArgumentException.forArgument(field);
  }
}
