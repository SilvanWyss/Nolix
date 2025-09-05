package ch.nolix.systemapi.objectdata.schemamapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;

public interface IColumnMapper {
  IColumn mapFieldToColumn(IField field, IContainer<ITable> referencedTables);
}
