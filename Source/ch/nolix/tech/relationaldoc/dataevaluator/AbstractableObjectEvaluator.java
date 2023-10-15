//package declaration
package ch.nolix.tech.relationaldoc.dataevaluator;

//own imports
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableField;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;

//class
public final class AbstractableObjectEvaluator {

  // method
  public boolean canAddBaseType(final IAbstractableObject abstractableObject, final IAbstractableObject baseType) {
    return canAddBaseType(baseType)
        && abstractableObject != null
        && !abstractableObject.hasSameNameAs(baseType)
        && canAddBaseTypeBecauseOfBaseTypes(abstractableObject, baseType)
        && canAddBaseTypeBecauseOfSubTypes(abstractableObject, baseType);
  }

  // method
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

  // method
  public boolean canBeSetAsConcrete(final IAbstractableObject abstractableObject) {
    return abstractableObject != null
        && abstractableObject.getStoredFields().containsNone(IAbstractableField::isAbstract);
  }

  // method
  public boolean canSetName(final IAbstractableObject abstractableObject, final String name) {
    return canSetName(name)
        && abstractableObject != null
        && abstractableObject.getStoredBaseTypes().containsNone(ao -> ao.hasName(name))
        && abstractableObject.getStoredSubTypes().containsNone(ao -> ao.hasName(name));
  }

  // method
  public boolean hasBaseType(final IAbstractableObject abstractableObject, final IAbstractableObject probableBaseType) {
    return abstractableObject != null
        && !abstractableObject.getStoredBaseTypes().contains(probableBaseType);
  }

  // method
  public boolean hasBaseTypes(final IAbstractableObject abstractableObject) {
    return abstractableObject != null
        && abstractableObject.getStoredDirectBaseTypes().containsAny();
  }

  // method
  private boolean canAddBaseTypeBecauseOfBaseTypes(
      final IAbstractableObject abstractableObject,
      final IAbstractableObject baseType) {

    if (abstractableObject == null) {
      return false;
    }

    final var baseTypes = abstractableObject.getStoredBaseTypes();
    return baseTypes.containsNone(bt -> bt.hasSameNameAs(baseType));
  }

  // method
  private boolean canAddBaseTypeBecauseOfSubTypes(
      final IAbstractableObject abstractableObject,
      final IAbstractableObject baseType) {

    if (abstractableObject == null) {
      return false;
    }

    final var subTypes = abstractableObject.getStoredSubTypes();
    return subTypes.containsNone(st -> st.hasSameNameAs(baseType));
  }

  // method
  private boolean canAddBaseType(final IAbstractableObject baseType) {
    return baseType != null
        && baseType.isAbstract();
  }

  // method
  private boolean canAddField(final IAbstractableField field) {
    return (field != null);
  }

  // method
  private boolean canAddFieldBecauseOfSubTypes(
      final IAbstractableObject abstractableObject,
      final IAbstractableField field) {
    return abstractableObject
        .getStoredSubTypes()
        .containsNone(st -> st.getStoredFields().containsAny(f -> f.hasSameNameAs(field)));
  }

  // method
  private boolean canSetName(final String name) {
    return name != null
        && !name.isBlank();
  }
}
