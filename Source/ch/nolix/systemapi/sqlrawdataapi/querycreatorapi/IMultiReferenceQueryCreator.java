//package declaration
package ch.nolix.systemapi.sqlrawdataapi.querycreatorapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IMultiReferenceQueryCreator {

  //method declaration
  String createQueryToCountMultiReferenceEntriesForGivenColumnAndReferencedEntityIgnoringGivenEntities(
    String columnId,
    String referencedEntityId,
    IContainer<String> entitiesToIgnoreIds);

  //method declaration
  String createQueryToLoadMultiReferenceEntries(String entityId, String multiReferenceColumnId);

  //method declaration
  String createQueryToLoadOptionalFirstMultiReferenceEntry(
    String multiReferenceColumnId,
    String referencedEntityId);
}
