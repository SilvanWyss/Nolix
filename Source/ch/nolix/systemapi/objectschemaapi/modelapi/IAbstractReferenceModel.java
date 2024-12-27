package ch.nolix.systemapi.objectschemaapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface IAbstractReferenceModel extends IContentModel {

  IContainer<ITable> getReferencedTables();
}
