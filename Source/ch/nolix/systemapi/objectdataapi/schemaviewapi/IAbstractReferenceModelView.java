package ch.nolix.systemapi.objectdataapi.schemaviewapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;

public interface IAbstractReferenceModelView<E extends IEntity> extends IContentModelView {

  ITable<E> getStoredencedTable();
}
