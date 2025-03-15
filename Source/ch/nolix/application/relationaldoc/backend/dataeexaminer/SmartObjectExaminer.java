package ch.nolix.application.relationaldoc.backend.dataeexaminer;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ISmartField;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ISmartObject;

public final class SmartObjectExaminer {

  public boolean canAddBaseType(final ISmartObject smartObject, final ISmartObject baseType) {
    return canAddBaseType(baseType)
    && canAddBaseTypeBecauseOfName(smartObject, baseType)
    && canAddBaseTypeBecauseOfBaseTypes(smartObject, baseType)
    && canAddBaseTypeBecauseOfSubTypes(smartObject, baseType);
  }

  public boolean canAddField(final ISmartObject smartObject, final ISmartField field) {

    if (!canAddField(field) || smartObject == null) {
      return false;
    }

    if (smartObject.isConcrete() && field.isAbstract()) {
      return false;
    }

    return smartObject.getStoredFields().containsNone(f -> f.hasSameNameAs(field))
    && canAddFieldBecauseOfSubTypes(smartObject, field);
  }

  public boolean canBeSetAsConcrete(final ISmartObject smartObject) {
    return smartObject != null
    && smartObject.getStoredFields().containsNone(ISmartField::isAbstract);
  }

  public boolean canSetName(final ISmartObject smartObject, final String name) {
    return canSetName(name)
    && smartObject != null
    && smartObject.getStoredBaseTypes().containsNone(ao -> ao.hasName(name))
    && smartObject.getStoredSubTypes().containsNone(ao -> ao.hasName(name));
  }

  public boolean hasBaseType(
    final ISmartObject smartObject,
    final ISmartObject probableBaseType) {
    return smartObject != null
    && !smartObject.getStoredBaseTypes().contains(probableBaseType);
  }

  public boolean hasBaseTypes(final ISmartObject smartObject) {
    return smartObject != null
    && smartObject.getStoredDirectBaseTypes().containsAny();
  }

  private boolean canAddBaseTypeBecauseOfBaseTypes(
    final ISmartObject smartObject,
    final ISmartObject baseType) {

    if (smartObject == null) {
      return false;
    }

    final var baseTypes = smartObject.getStoredBaseTypes();
    return baseTypes.containsNone(bt -> bt.hasSameNameAs(baseType));
  }

  private boolean canAddBaseTypeBecauseOfName(
    final ISmartObject smartObject,
    final ISmartObject baseType) {
    return //
    smartObject != null
    && !smartObject.hasSameNameAs(baseType);
  }

  private boolean canAddBaseTypeBecauseOfSubTypes(
    final ISmartObject smartObject,
    final ISmartObject baseType) {

    if (smartObject == null) {
      return false;
    }

    final var subTypes = smartObject.getStoredSubTypes();
    return subTypes.containsNone(st -> st.hasSameNameAs(baseType));
  }

  private boolean canAddBaseType(final ISmartObject baseType) {
    return baseType != null
    && baseType.isAbstract();
  }

  private boolean canAddField(final ISmartField field) {
    return (field != null);
  }

  private boolean canAddFieldBecauseOfSubTypes(
    final ISmartObject smartObject,
    final ISmartField field) {
    return smartObject
      .getStoredSubTypes()
      .containsNone(st -> st.getStoredFields().containsAny(f -> f.hasSameNameAs(field)));
  }

  private boolean canSetName(final String name) {
    return name != null
    && !name.isBlank();
  }
}
