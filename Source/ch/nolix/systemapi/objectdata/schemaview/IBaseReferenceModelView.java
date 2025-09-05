package ch.nolix.systemapi.objectdata.schemaview;

import ch.nolix.coreapi.container.base.IContainer;

public interface IBaseReferenceModelView<T> extends IContentModelView<T> {
  IContainer<? extends T> getStoredReferenceableTables();
}
