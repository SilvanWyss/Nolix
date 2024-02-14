//package declaration
package ch.nolix.systemapi.rawdataapi.querycreatorapi;

//interface
public interface IMultiValueQueryCreator {

  //method declaration
  String createQueryToLoadMultiValueEntries(String entityId, String multiValueColumnId);

  //method declaration
  String createQueryToLoadOneOrNoneMultiValueEntryForGivenColumnAndValue(String columnId, String value);
}
