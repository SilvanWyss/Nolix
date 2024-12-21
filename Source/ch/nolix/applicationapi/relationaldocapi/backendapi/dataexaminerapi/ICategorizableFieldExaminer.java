package ch.nolix.applicationapi.relationaldocapi.backendapi.dataexaminerapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableField;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;

public interface ICategorizableFieldExaminer {

  boolean allRealisingFieldsAreEmpty(ICategorizableField field);

  boolean canBeSetAsAbstract(ICategorizableField field);

  boolean canBeSetAsConcrete(ICategorizableField field);

  boolean canBeSetForReferences(ICategorizableField field);

  boolean canBeSetForValues(ICategorizableField field);

  boolean canSetCardinality(ICategorizableField field, Cardinality cardinality);

  boolean canSetName(ICategorizableField field, String name);

  IContainer<? extends ICategorizableField> getStoredRealisingFields(ICategorizableField field);

  boolean hasRealisingFields(ICategorizableField field);
}
