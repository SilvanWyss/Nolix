package ch.nolix.application.relationaldoc.backend.dataeexaminer;

import ch.nolix.applicationapi.relationaldocapi.backendapi.dataexaminerapi.ISmartFieldExaminer;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ISmartField;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;

public final class SmartFieldExaminer implements ISmartFieldExaminer {

  @Override
  public boolean allRealisingFieldsAreEmpty(final ISmartField field) {

    final var realisingFields = getStoredRealisingFields(field);

    return realisingFields.containsOnly(ISmartField::isEmpty);
  }

  @Override
  public boolean canBeSetAsAbstract(final ISmartField field) {
    return //
    field != null
    && canBeSetAsAbstractWhenIsNotNull(field);
  }

  @Override
  public boolean canBeSetAsConcrete(final ISmartField field) {
    return //
    field != null
    && canBeSetAsConcreteWhenIsNotNull(field);
  }

  @Override
  public boolean canBeSetForReferences(final ISmartField field) {
    return //
    field != null
    && canBeSetForReferencesWhenIsNotNull(field);
  }

  @Override
  public boolean canBeSetForValues(final ISmartField field) {
    return //
    field != null
    && canBeSetForValuesWhenIsNotNull(field);
  }

  @Override
  public boolean canSetCardinality(final ISmartField field, final Cardinality cardinality) {
    return //
    field != null
    && cardinality != null
    && canSetCardinalityWhenIsNotNull(field, cardinality);
  }

  @Override
  public boolean canSetName(final ISmartField field, final String name) {
    return //
    field != null
    && canSetName(name)
    && canSetNameWhenIsNotNull(field, name);
  }

  @Override
  public IContainer<? extends ISmartField> getStoredRealisingFields(final ISmartField field) {

    if (field == null || field.isConcrete()) {
      return ImmutableList.createEmpty();
    }

    return getStoredRealisingFieldsWhenIsAbstract(field);
  }

  @Override
  public boolean hasRealisingFields(final ISmartField field) {
    return //
    field != null
    && hasRealisingFieldsWhenIsNotNull(field);
  }

  private boolean canBeSetAsAbstractBecauseOfParentObject(final ISmartField field) {
    return //
    field != null
    && field.getStoredParentObject().isAbstract();
  }

  private boolean canBeSetAsAbstractWhenIsNotNull(final ISmartField field) {
    return //
    !field.inheritsFromBaseField()
    && canBeSetAsAbstractBecauseOfParentObject(field);
  }

  private boolean canBeSetAsConcreteWhenIsNotNull(final ISmartField field) {
    return allRealisingFieldsAreEmpty(field);
  }

  private boolean canBeSetForReferencesWhenIsNotNull(final ISmartField field) {
    return //
    field.isForReferences()
    || hasRealisingFields(field);
  }

  private boolean canBeSetForValuesWhenIsAbstractAndForReferences(final ISmartField field) {
    return allRealisingFieldsAreEmpty(field);
  }

  private boolean canBeSetForValuesWhenIsConcreteAndForReferences(final ISmartField field) {
    return !field.inheritsFromBaseField();
  }

  private boolean canBeSetForValuesWhenIsForReferences(final ISmartField field) {

    if (field.isAbstract()) {
      return canBeSetForValuesWhenIsAbstractAndForReferences(field);
    }

    return canBeSetForValuesWhenIsConcreteAndForReferences(field);
  }

  private boolean canBeSetForValuesWhenIsNotNull(final ISmartField field) {
    return //
    field.isForValues()
    || canBeSetForValuesWhenIsForReferences(field);
  }

  private boolean canSetCardinalityWhenIsNotNull(final ISmartField field, final Cardinality cardinality) {

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
    final ISmartField field,
    final String name) {
    return //
    field != null
    && field.getStoredParentObject().getStoredSubTypes().containsNone(cst -> cst.hasName(name));
  }

  private boolean canSetNameWhenIsNotNull(final ISmartField field, final String name) {
    return //
    field.getStoredParentObject().getStoredFields().containsNone(f -> f.hasName(name))
    && canSetNameBecauseOfSubTypesOfParentObject(field, name);
  }

  private IContainer<? extends ISmartField> getStoredRealisingFieldsWhenIsAbstract(
    final ISmartField field) {
    return //
    field
      .getStoredParentObject()
      .getStoredSubTypes()
      .toMultiples(st -> st.getStoredDeclaredFields().getStoredSelected(df -> df.hasSameNameAs(field)));
  }

  private boolean hasRealisingFieldsWhenIsNotNull(final ISmartField field) {

    final var realisingFields = getStoredRealisingFields(field);

    return realisingFields.isEmpty();
  }
}
