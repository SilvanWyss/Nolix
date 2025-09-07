package ch.nolix.systemapi.objectschema.model;

import ch.nolix.coreapi.container.base.IContainer;

public interface IBaseBackReferenceModel extends IContentModel {
  IContainer<? extends IColumn> getStoredBackReferenceableColumns();
}
