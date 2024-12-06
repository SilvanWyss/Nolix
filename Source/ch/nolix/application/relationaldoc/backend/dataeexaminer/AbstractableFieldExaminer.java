package ch.nolix.application.relationaldoc.backend.dataeexaminer;

import ch.nolix.applicationapi.relationaldocapi.backendapi.dataexaminerapi.IAbstractableFieldExaminer;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractableField;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;

public final class AbstractableFieldExaminer implements IAbstractableFieldExaminer {

  @Override
  public boolean allRealisingFieldsAreEmpty(final IAbstractableField field) {

    final var realisingFields = getStoredRealisingFields(field);

    return realisingFields.containsOnly(IAbstractableField::isEmpty);
  }

  @Override
  public boolean canBeSetAsAbstract(final IAbstractableField field) {
    return //
    field != null
    && canBeSetAsAbstractWhenIsNotNull(field);
  }

  @Override
  public boolean canBeSetAsConcrete(final IAbstractableField field) {
    return //
    field != null
    && canBeSetAsConcreteWhenIsNotNull(field);
  }

  @Override
  public boolean canBeSetForReferences(final IAbstractableField field) {
    return //
    field != null
    && canBeSetForReferencesWhenIsNotNull(field);
  }

  @Override
  public boolean canBeSetForValues(final IAbstractableField field) {
    return //
    field != null
    && canBeSetForValuesWhenIsNotNull(field);
  }

  @Override
  public boolean canSetCardinality(final IAbstractableField field, final Cardinality cardinality) {
    return //
    field != null
    && cardinality != null
    && canSetCardinalityWhenIsNotNull(field, cardinality);
  }

  @Override
  public boolean canSetName(final IAbstractableField field, final String name) {
    return //
    field != null
    && canSetName(name)
    && canSetNameWhenIsNotNull(field, name);
  }

  @Override
  public IContainer<? extends IAbstractableField> getStoredRealisingFields(final IAbstractableField field) {

    if (field == null || field.isConcrete()) {
      return ImmutableList.createEmpty();
    }

    return getStoredRealisingFieldsWhenIsAbstract(field);
  }

  @Override
  public boolean hasRealisingFields(final IAbstractableField field) {
    return //
    field != null
    && hasRealisingFieldsWhenIsNotNull(field);
  }

  private boolean canBeSetAsAbstractBecauseOfParentObject(final IAbstractableField field) {
    return //
    field != null
    && field.getStoredParentObject().isAbstract();
  }

  private boolean canBeSetAsAbstractWhenIsNotNull(final IAbstractableField field) {
    return //
    !field.inheritsFromBaseField()
    && canBeSetAsAbstractBecauseOfParentObject(field);
  }

  private boolean canBeSetAsConcreteWhenIsNotNull(final IAbstractableField field) {
    return allRealisingFieldsAreEmpty(field);
  }

  private boolean canBeSetForReferencesWhenIsNotNull(final IAbstractableField field) {
    return //
    field.isForReferences()
    || hasRealisingFields(field);
  }

  private boolean canBeSetForValuesWhenIsAbstractAndForReferences(final IAbstractableField field) {
    return allRealisingFieldsAreEmpty(field);
  }

  private boolean canBeSetForValuesWhenIsConcreteAndForReferences(final IAbstractableField field) {
    return !field.inheritsFromBaseField();
  }

  private boolean canBeSetForValuesWhenIsForReferences(final IAbstractableField field) {

    if (field.isAbstract()) {
      return canBeSetForValuesWhenIsAbstractAndForReferences(field);
    }

    return canBeSetForValuesWhenIsConcreteAndForReferences(field);
  }

  private boolean canBeSetForValuesWhenIsNotNull(final IAbstractableField field) {
    return //
    field.isForValues()
    || canBeSetForValuesWhenIsForReferences(field);
  }

  private boolean canSetCardinalityWhenIsNotNull(final IAbstractableField field, final Cardinality cardinality) {

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
    final IAbstractableField field,
    final String name) {
    return //
    field != null
    && field.getStoredParentObject().getStoredSubTypes().containsNone(cst -> cst.hasName(name));
  }

  private boolean canSetNameWhenIsNotNull(final IAbstractableField field, final String name) {
    return //
    field.getStoredParentObject().getStoredFields().containsNone(f -> f.hasName(name))
    && canSetNameBecauseOfSubTypesOfParentObject(field, name);
  }

  private IContainer<? extends IAbstractableField> getStoredRealisingFieldsWhenIsAbstract(
    final IAbstractableField field) {
    return //
    field
      .getStoredParentObject()
      .getStoredSubTypes()
      .toMultiple(st -> st.getStoredDeclaredFields().getStoredSelected(df -> df.hasSameNameAs(field)));
  }

  private boolean hasRealisingFieldsWhenIsNotNull(final IAbstractableField field) {

    final var realisingFields = getStoredRealisingFields(field);

    return realisingFields.isEmpty();
  }
}
