package ch.nolix.systemapi.objectschema.model;

import ch.nolix.coreapi.container.base.IContainer;

public interface IBaseReferenceModel extends IContentModel {
  IContainer<? extends ITable> getReferenceableTables();
}
