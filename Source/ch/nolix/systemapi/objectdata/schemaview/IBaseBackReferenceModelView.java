package ch.nolix.systemapi.objectdata.schemaview;

import ch.nolix.coreapi.container.base.IContainer;

public interface IBaseBackReferenceModelView<C extends IColumnView<T>, T> extends IContentModelView<T> {
  IContainer<? extends C> getStoredBackReferenceableColumnViews();
}
