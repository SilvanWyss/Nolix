package ch.nolix.applicationapi.relationaldocapi.backendapi.dataexaminerapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ISmartField;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;

public interface ISmartFieldExaminer {

  boolean allRealisingFieldsAreEmpty(ISmartField field);

  boolean canBeSetAsAbstract(ISmartField field);

  boolean canBeSetAsConcrete(ISmartField field);

  boolean canBeSetForReferences(ISmartField field);

  boolean canBeSetForValues(ISmartField field);

  boolean canSetCardinality(ISmartField field, Cardinality cardinality);

  boolean canSetName(ISmartField field, String name);

  IContainer<? extends ISmartField> getStoredRealisingFields(ISmartField field);

  boolean hasRealisingFields(ISmartField field);
}
