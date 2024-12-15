package ch.nolix.systemapi.objectschemaapi.schemaapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface IAbstractReferenceModel extends IContentModel {

  IContainer<ITable> getReferencedTables();
}
