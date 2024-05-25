//package declaration
package ch.nolix.systemapi.sqlrawdataapi.querycreatorapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IMultiValueQueryCreator {

  //method declaration
  String createQueryToCountMultiValueEntriesForGivenColumnAndValueIgnoringGivenEntities(
    String columnId,
    String value,
    IContainer<String> entitiesToIgnoreIds);

  //method declaration
  String createQueryToLoadMultiValueEntries(String entityId, String multiValueColumnId);

  //method declaration
  String createQueryToLoadOneOrNoneMultiValueEntryForGivenColumnAndValue(String columnId, String value);
}
