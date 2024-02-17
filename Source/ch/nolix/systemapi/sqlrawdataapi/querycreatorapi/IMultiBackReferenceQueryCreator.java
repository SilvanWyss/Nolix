//package declaration
package ch.nolix.systemapi.sqlrawdataapi.querycreatorapi;

//interface
public interface IMultiBackReferenceQueryCreator {

  //method declaration
  String createQueryToLoadMultiBackReferenceEntries(String entityId, String multiBackReferenceColumnId);

  //method declaration
  String createQueryToLoadOptionalFirstMultiBackReferenceEntry(
    String multiBackReferenceColumnId,
    String backReferencedEntityId);
}
