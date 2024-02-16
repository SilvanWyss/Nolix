//package declaration
package ch.nolix.systemapi.sqlrawdataapi.querycreatorapi;

//interface
public interface IMultiReferenceQueryCreator {

  //method declaration
  String createQueryToLoadMultiReferenceEntries(String entityId, String multiReferenceColumnId);

  //method declaration
  String createQueryToLoadOptionalMultiReferenceEntryForGivenColumnAndReferencedEntity(
    String columnId,
    String referencedEntityId);
}
