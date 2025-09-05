package ch.nolix.systemapi.objectdata.schemamapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectschema.model.IContentModel;
import ch.nolix.systemapi.objectschema.model.ITable;

public interface IContentModelMapper {
  IContentModel mapFieldToContentModel(IField field, IContainer<ITable> referencedTables);
}
