package ch.nolix.application.relationaldoc.backend.dataeexaminer;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableField;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableObject;

public final class CategorizableObjectExaminer {

  public boolean canAddBaseType(final ICategorizableObject categorizableObject, final ICategorizableObject baseType) {
    return canAddBaseType(baseType)
    && canAddBaseTypeBecauseOfName(categorizableObject, baseType)
    && canAddBaseTypeBecauseOfBaseTypes(categorizableObject, baseType)
    && canAddBaseTypeBecauseOfSubTypes(categorizableObject, baseType);
  }

  public boolean canAddField(final ICategorizableObject categorizableObject, final ICategorizableField field) {

    if (!canAddField(field) || categorizableObject == null) {
      return false;
    }

    if (categorizableObject.isConcrete() && field.isAbstract()) {
      return false;
    }

    return categorizableObject.getStoredFields().containsNone(f -> f.hasSameNameAs(field))
    && canAddFieldBecauseOfSubTypes(categorizableObject, field);
  }

  public boolean canBeSetAsConcrete(final ICategorizableObject categorizableObject) {
    return categorizableObject != null
    && categorizableObject.getStoredFields().containsNone(ICategorizableField::isAbstract);
  }

  public boolean canSetName(final ICategorizableObject categorizableObject, final String name) {
    return canSetName(name)
    && categorizableObject != null
    && categorizableObject.getStoredBaseTypes().containsNone(ao -> ao.hasName(name))
    && categorizableObject.getStoredSubTypes().containsNone(ao -> ao.hasName(name));
  }

  public boolean hasBaseType(
    final ICategorizableObject categorizableObject,
    final ICategorizableObject probableBaseType) {
    return categorizableObject != null
    && !categorizableObject.getStoredBaseTypes().contains(probableBaseType);
  }

  public boolean hasBaseTypes(final ICategorizableObject categorizableObject) {
    return categorizableObject != null
    && categorizableObject.getStoredDirectBaseTypes().containsAny();
  }

  private boolean canAddBaseTypeBecauseOfBaseTypes(
    final ICategorizableObject categorizableObject,
    final ICategorizableObject baseType) {

    if (categorizableObject == null) {
      return false;
    }

    final var baseTypes = categorizableObject.getStoredBaseTypes();
    return baseTypes.containsNone(bt -> bt.hasSameNameAs(baseType));
  }

  private boolean canAddBaseTypeBecauseOfName(
    final ICategorizableObject categorizableObject,
    final ICategorizableObject baseType) {
    return //
    categorizableObject != null
    && !categorizableObject.hasSameNameAs(baseType);
  }

  private boolean canAddBaseTypeBecauseOfSubTypes(
    final ICategorizableObject categorizableObject,
    final ICategorizableObject baseType) {

    if (categorizableObject == null) {
      return false;
    }

    final var subTypes = categorizableObject.getStoredSubTypes();
    return subTypes.containsNone(st -> st.hasSameNameAs(baseType));
  }

  private boolean canAddBaseType(final ICategorizableObject baseType) {
    return baseType != null
    && baseType.isAbstract();
  }

  private boolean canAddField(final ICategorizableField field) {
    return (field != null);
  }

  private boolean canAddFieldBecauseOfSubTypes(
    final ICategorizableObject categorizableObject,
    final ICategorizableField field) {
    return categorizableObject
      .getStoredSubTypes()
      .containsNone(st -> st.getStoredFields().containsAny(f -> f.hasSameNameAs(field)));
  }

  private boolean canSetName(final String name) {
    return name != null
    && !name.isBlank();
  }
}
