package ch.nolix.techapi.relationaldocapi.datamodelapi;

public interface IReferenceContent extends IContent {

  IAbstractableObject getStoredReferencedType();
}
