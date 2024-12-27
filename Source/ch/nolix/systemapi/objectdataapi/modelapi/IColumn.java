package ch.nolix.systemapi.objectdataapi.modelapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;

public interface IColumn extends IDatabaseObject, IIdHolder, INameHolder {

  IContentModel getContentModel();

  ITable<IEntity> getStoredParentTable();

  boolean internalContainsGivenValueInPersistedData(String value);

  boolean internalContainsGivenValueInPersistedDataIgnoringGivenEntities(
    String value,
    IContainer<String> entitiesToIgnoreIds);
}
