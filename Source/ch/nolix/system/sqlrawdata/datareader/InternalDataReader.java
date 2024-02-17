//package declaration
package ch.nolix.system.sqlrawdata.datareader;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.sql.connection.SqlConnection;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedEntityDto;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IEntityQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiReferenceQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiValueQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.sqlsyntaxapi.ISqlSyntaxProvider;

//class
final class InternalDataReader {

  //constant
  private static final LoadedEntityDtoMapper LOADED_ENTITY_DTO_MAPPER = new LoadedEntityDtoMapper();

  //constant
  private static final ValueMapper VALUE_MAPPER = new ValueMapper();

  //attribute
  private final ISqlConnection sqlConnection;

  //attribute
  private final IEntityQueryCreator entityQueryCreator;

  //attribute
  private final IMultiValueQueryCreator multiValueQueryCreator;

  //attribute
  private final IMultiReferenceQueryCreator multiReferenceQueryCreator;

  //constructor
  public InternalDataReader(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final ISqlSyntaxProvider sqlSyntaxProvider) {

    GlobalValidator.assertThat(sqlConnection).thatIsNamed(SqlConnection.class).isNotNull();

    this.sqlConnection = sqlConnection;
    entityQueryCreator = sqlSyntaxProvider.getEntityQueryCreator();
    multiValueQueryCreator = sqlSyntaxProvider.getMultiValueQueryCreator();
    multiReferenceQueryCreator = sqlSyntaxProvider.getMultiReferenceQueryCreator();

    sqlConnection.executeStatement("USE " + databaseName);
  }

  //method
  public Time getSchemaTimestamp() {
    return Time.fromString(
      sqlConnection
        .getSingleRecordFromQuery(entityQueryCreator.createQueryToLoadSchemaTimestamp())
        .getStoredAt1BasedIndex(1));
  }

  //method
  public IContainer<String> loadMultiReferenceEntries(
    final String entityId,
    final IColumnInfo multiReferenceColumnInfo) {
    return sqlConnection
      .getRecordsFromQuery(
        multiReferenceQueryCreator.createQueryToLoadMultiReferenceEntries(
          entityId,
          multiReferenceColumnInfo.getColumnId()))
      .to(r -> r.getStoredAt1BasedIndex(1));
  }

  //method
  public IContainer<Object> loadMultiValueEntries(
    final String entityId,
    final IColumnInfo multiValueColumnInfo) {
    return sqlConnection
      .getRecordsFromQuery(
        multiValueQueryCreator.createQueryToLoadMultiValueEntries(
          entityId,
          multiValueColumnInfo.getColumnId()))
      .to(r -> VALUE_MAPPER.createValueFromString(r.getStoredAt1BasedIndex(1), multiValueColumnInfo));
  }

  //method
  public IContainer<ILoadedEntityDto> loadEntitiesOfTable(final ITableInfo tableInfo) {
    return sqlConnection
      .getRecordsFromQuery(entityQueryCreator.createQueryToLoadEntitiesOfTable(tableInfo))
      .to(r -> LOADED_ENTITY_DTO_MAPPER.createLoadedEntityDtoFrosqlRecord(r, tableInfo));
  }

  //method
  public ILoadedEntityDto loadEntity(final ITableInfo tableInfo, final String id) {
    return LOADED_ENTITY_DTO_MAPPER.createLoadedEntityDtoFrosqlRecord(
      sqlConnection.getSingleRecordFromQuery(entityQueryCreator.createQueryToLoadEntity(id, tableInfo)),
      tableInfo);
  }

  //method
  public boolean tableContainsEntityWithGivenValueAtGivenColumn(
    final String tableName,
    final IColumnInfo columnInfo,
    final String value) {
    return switch (columnInfo.getColumnPropertyType()) {
      case VALUE, OPTIONAL_VALUE, REFERENCE, OPTIONAL_REFERENCE, BACK_REFERENCE, OPTIONAL_BACK_REFERENCE ->
        tableContainsEntityWithGivenValueAtGivenSingleColumn(
          tableName,
          columnInfo.getColumnName(),
          value);
      case MULTI_VALUE ->
        multiValueEntryExistsForGivenColumnAndValue(columnInfo.getColumnId(), value);
      case MULTI_REFERENCE ->
        multiReferenceEntryExistsForGivenColumnAndReferencedEntity(columnInfo.getColumnId(), value);
      default ->
        throw InvalidArgumentException.forArgument(columnInfo.getColumnPropertyType());
    };
  }

  //method
  public boolean tableContainsEntityWithGivenId(final String tableName, final String id) {

    final var entityCount = Integer.valueOf(
      sqlConnection
        .getSingleRecordFromQuery(entityQueryCreator.createQueryToCountEntitiesWithGivenId(tableName, id))
        .getStoredAt1BasedIndex(1));

    return entityCount > 0;
  }

  //method
  private boolean multiReferenceEntryExistsForGivenColumnAndReferencedEntity(
    final String columnId,
    final String referencedEntityId) {
    return sqlConnection.getRecordsFromQuery(
      multiReferenceQueryCreator
        .createQueryToLoadOptionalFirstMultiReferenceEntry(
          columnId,
          referencedEntityId))
      .containsAny();
  }

  //method
  private boolean multiValueEntryExistsForGivenColumnAndValue(
    final String columnId,
    final String value) {
    return sqlConnection.getRecordsFromQuery(
      multiValueQueryCreator.createQueryToLoadOneOrNoneMultiValueEntryForGivenColumnAndValue(
        columnId,
        value))
      .containsAny();
  }

  //method
  private boolean tableContainsEntityWithGivenValueAtGivenSingleColumn(
    final String tableName,
    final String singleColumnName,
    final String value) {
    return Integer.valueOf(
      sqlConnection.getSingleRecordFromQuery(
        entityQueryCreator.createQueryToCountEntitiesWithGivenValueAtGivenColumn(
          tableName,
          singleColumnName,
          value))
        .getStoredAt1BasedIndex(1)) > 0;
  }
}
