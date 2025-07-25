package ch.nolix.systemapi.objectdataapi.schemamapperapi;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public interface IColumnMapper {

  IColumn mapFieldToColumn(IField field, IContainer<ITable> referencedTables);
}
