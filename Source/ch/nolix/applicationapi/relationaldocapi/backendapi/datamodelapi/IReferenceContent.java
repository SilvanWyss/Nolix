package ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi;

public interface IReferenceContent extends IContent {

  IAbstractableObject getStoredReferencedType();
}