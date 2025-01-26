package ch.nolix.systemapi.objectdataapi.schemaviewapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.modelapi.IDatabaseObject;

public interface IColumnView<T> extends IDatabaseObject, IIdHolder, INameHolder {

  IContentModelView<T> getContentModel();

  T getStoredParentTable();

  boolean internalContainsGivenValueInPersistedData(String value);

  boolean internalContainsGivenValueInPersistedDataIgnoringGivenEntities(
    String value,
    IContainer<String> entitiesToIgnoreIds);
}
