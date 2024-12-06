package ch.nolix.applicationapi.relationaldocapi.backendapi.dataexaminerapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractableField;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;

public interface IAbstractableFieldExaminer {

  boolean allRealisingFieldsAreEmpty(IAbstractableField field);

  boolean canBeSetAsAbstract(IAbstractableField field);

  boolean canBeSetAsConcrete(IAbstractableField field);

  boolean canBeSetForReferences(IAbstractableField field);

  boolean canBeSetForValues(IAbstractableField field);

  boolean canSetCardinality(IAbstractableField field, Cardinality cardinality);

  boolean canSetName(IAbstractableField field, String name);

  IContainer<? extends IAbstractableField> getStoredRealisingFields(IAbstractableField field);

  boolean hasRealisingFields(IAbstractableField field);
}
