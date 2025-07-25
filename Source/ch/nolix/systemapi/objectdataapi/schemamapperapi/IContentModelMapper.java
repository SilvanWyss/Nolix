package ch.nolix.systemapi.objectdataapi.schemamapperapi;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public interface IContentModelMapper {

  IContentModel mapFieldToContentModel(IField field, IContainer<ITable> referencedTables);
}
