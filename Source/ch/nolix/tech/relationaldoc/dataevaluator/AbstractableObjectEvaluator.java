package ch.nolix.tech.relationaldoc.dataevaluator;

import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableField;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;

public final class AbstractableObjectEvaluator {

  public boolean canAddBaseType(final IAbstractableObject abstractableObject, final IAbstractableObject baseType) {
    return canAddBaseType(baseType)
    && canAddBaseTypeBecauseOfName(abstractableObject, baseType)
    && canAddBaseTypeBecauseOfBaseTypes(abstractableObject, baseType)
    && canAddBaseTypeBecauseOfSubTypes(abstractableObject, baseType);
  }

  public boolean canAddField(final IAbstractableObject abstractableObject, final IAbstractableField field) {

    if (!canAddField(field) || abstractableObject == null) {
      return false;
    }

    if (abstractableObject.isConcrete() && field.isAbstract()) {
      return false;
    }

    return abstractableObject.getStoredFields().containsNone(f -> f.hasSameNameAs(field))
    && canAddFieldBecauseOfSubTypes(abstractableObject, field);
  }

  public boolean canBeSetAsConcrete(final IAbstractableObject abstractableObject) {
    return abstractableObject != null
    && abstractableObject.getStoredFields().containsNone(IAbstractableField::isAbstract);
  }

  public boolean canSetName(final IAbstractableObject abstractableObject, final String name) {
    return canSetName(name)
    && abstractableObject != null
    && abstractableObject.getStoredBaseTypes().containsNone(ao -> ao.hasName(name))
    && abstractableObject.getStoredSubTypes().containsNone(ao -> ao.hasName(name));
  }

  public boolean hasBaseType(final IAbstractableObject abstractableObject, final IAbstractableObject probableBaseType) {
    return abstractableObject != null
    && !abstractableObject.getStoredBaseTypes().contains(probableBaseType);
  }

  public boolean hasBaseTypes(final IAbstractableObject abstractableObject) {
    return abstractableObject != null
    && abstractableObject.getStoredDirectBaseTypes().containsAny();
  }

  private boolean canAddBaseTypeBecauseOfBaseTypes(
    final IAbstractableObject abstractableObject,
    final IAbstractableObject baseType) {

    if (abstractableObject == null) {
      return false;
    }

    final var baseTypes = abstractableObject.getStoredBaseTypes();
    return baseTypes.containsNone(bt -> bt.hasSameNameAs(baseType));
  }

  private boolean canAddBaseTypeBecauseOfName(
    final IAbstractableObject abstractableObject,
    final IAbstractableObject baseType) {
    return //
    abstractableObject != null
    && !abstractableObject.hasSameNameAs(baseType);
  }

  private boolean canAddBaseTypeBecauseOfSubTypes(
    final IAbstractableObject abstractableObject,
    final IAbstractableObject baseType) {

    if (abstractableObject == null) {
      return false;
    }

    final var subTypes = abstractableObject.getStoredSubTypes();
    return subTypes.containsNone(st -> st.hasSameNameAs(baseType));
  }

  private boolean canAddBaseType(final IAbstractableObject baseType) {
    return baseType != null
    && baseType.isAbstract();
  }

  private boolean canAddField(final IAbstractableField field) {
    return (field != null);
  }

  private boolean canAddFieldBecauseOfSubTypes(
    final IAbstractableObject abstractableObject,
    final IAbstractableField field) {
    return abstractableObject
      .getStoredSubTypes()
      .containsNone(st -> st.getStoredFields().containsAny(f -> f.hasSameNameAs(field)));
  }

  private boolean canSetName(final String name) {
    return name != null
    && !name.isBlank();
  }
}
