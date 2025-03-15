package ch.nolix.application.relationaldoc.backend.dataeexaminer;

import ch.nolix.applicationapi.relationaldocapi.backendapi.dataexaminerapi.ICategorizableFieldExaminer;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableField;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;

public final class CategorizableFieldExaminer implements ICategorizableFieldExaminer {

  @Override
  public boolean allRealisingFieldsAreEmpty(final ICategorizableField field) {

    final var realisingFields = getStoredRealisingFields(field);

    return realisingFields.containsOnly(ICategorizableField::isEmpty);
  }

  @Override
  public boolean canBeSetAsAbstract(final ICategorizableField field) {
    return //
    field != null
    && canBeSetAsAbstractWhenIsNotNull(field);
  }

  @Override
  public boolean canBeSetAsConcrete(final ICategorizableField field) {
    return //
    field != null
    && canBeSetAsConcreteWhenIsNotNull(field);
  }

  @Override
  public boolean canBeSetForReferences(final ICategorizableField field) {
    return //
    field != null
    && canBeSetForReferencesWhenIsNotNull(field);
  }

  @Override
  public boolean canBeSetForValues(final ICategorizableField field) {
    return //
    field != null
    && canBeSetForValuesWhenIsNotNull(field);
  }

  @Override
  public boolean canSetCardinality(final ICategorizableField field, final Cardinality cardinality) {
    return //
    field != null
    && cardinality != null
    && canSetCardinalityWhenIsNotNull(field, cardinality);
  }

  @Override
  public boolean canSetName(final ICategorizableField field, final String name) {
    return //
    field != null
    && canSetName(name)
    && canSetNameWhenIsNotNull(field, name);
  }

  @Override
  public IContainer<? extends ICategorizableField> getStoredRealisingFields(final ICategorizableField field) {

    if (field == null || field.isConcrete()) {
      return ImmutableList.createEmpty();
    }

    return getStoredRealisingFieldsWhenIsAbstract(field);
  }

  @Override
  public boolean hasRealisingFields(final ICategorizableField field) {
    return //
    field != null
    && hasRealisingFieldsWhenIsNotNull(field);
  }

  private boolean canBeSetAsAbstractBecauseOfParentObject(final ICategorizableField field) {
    return //
    field != null
    && field.getStoredParentObject().isAbstract();
  }

  private boolean canBeSetAsAbstractWhenIsNotNull(final ICategorizableField field) {
    return //
    !field.inheritsFromBaseField()
    && canBeSetAsAbstractBecauseOfParentObject(field);
  }

  private boolean canBeSetAsConcreteWhenIsNotNull(final ICategorizableField field) {
    return allRealisingFieldsAreEmpty(field);
  }

  private boolean canBeSetForReferencesWhenIsNotNull(final ICategorizableField field) {
    return //
    field.isForReferences()
    || hasRealisingFields(field);
  }

  private boolean canBeSetForValuesWhenIsAbstractAndForReferences(final ICategorizableField field) {
    return allRealisingFieldsAreEmpty(field);
  }

  private boolean canBeSetForValuesWhenIsConcreteAndForReferences(final ICategorizableField field) {
    return !field.inheritsFromBaseField();
  }

  private boolean canBeSetForValuesWhenIsForReferences(final ICategorizableField field) {

    if (field.isAbstract()) {
      return canBeSetForValuesWhenIsAbstractAndForReferences(field);
    }

    return canBeSetForValuesWhenIsConcreteAndForReferences(field);
  }

  private boolean canBeSetForValuesWhenIsNotNull(final ICategorizableField field) {
    return //
    field.isForValues()
    || canBeSetForValuesWhenIsForReferences(field);
  }

  private boolean canSetCardinalityWhenIsNotNull(final ICategorizableField field, final Cardinality cardinality) {

    if (field.getCardinality() == cardinality || cardinality == Cardinality.TO_MANY) {
      return true;
    }

    if (field.isAbstract()) {
      return !hasRealisingFields(field);
    }

    return false;
  }

  private boolean canSetName(final String name) {
    return //
    name != null
    && !name.isBlank();
  }

  private boolean canSetNameBecauseOfSubTypesOfParentObject(
    final ICategorizableField field,
    final String name) {
    return //
    field != null
    && field.getStoredParentObject().getStoredSubTypes().containsNone(cst -> cst.hasName(name));
  }

  private boolean canSetNameWhenIsNotNull(final ICategorizableField field, final String name) {
    return //
    field.getStoredParentObject().getStoredFields().containsNone(f -> f.hasName(name))
    && canSetNameBecauseOfSubTypesOfParentObject(field, name);
  }

  private IContainer<? extends ICategorizableField> getStoredRealisingFieldsWhenIsAbstract(
    final ICategorizableField field) {
    return //
    field
      .getStoredParentObject()
      .getStoredSubTypes()
      .toMultiples(st -> st.getStoredDeclaredFields().getStoredSelected(df -> df.hasSameNameAs(field)));
  }

  private boolean hasRealisingFieldsWhenIsNotNull(final ICategorizableField field) {

    final var realisingFields = getStoredRealisingFields(field);

    return realisingFields.isEmpty();
  }
}
