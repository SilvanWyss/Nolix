//package declaration
package ch.nolix.system.sqldatabaserawdata.databasereader;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.sql.SqlConnection;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedEntityDto;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IEntityQueryCreator;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IMultiReferenceQueryCreator;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IMultiValueQueryCreator;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.ISqlSyntaxProvider;

//class
final class InternalDatabaseReader {

  //constant
  private static final LoadedEntityDtoMapper LOADED_ENTITY_DTO_MAPPER = new LoadedEntityDtoMapper();

  //constant
  private static final ValueMapper VALUE_MAPPER = new ValueMapper();

  //attribute
  private final SqlConnection sqlConnection;

  //attribute
  private final IEntityQueryCreator entityQueryCreator;

  //attribute
  private final IMultiValueQueryCreator multiValueQueryCreator;

  //attribute
  private final IMultiReferenceQueryCreator multiReferenceQueryCreator;

  //constructor
  public InternalDatabaseReader(
    final String databaseName,
    final SqlConnection sqlConnection,
    final ISqlSyntaxProvider sqlSyntaxProvider) {

    GlobalValidator.assertThat(sqlConnection).thatIsNamed(SqlConnection.class).isNotNull();

    this.sqlConnection = sqlConnection;
    entityQueryCreator = sqlSyntaxProvider.getEntityQueryCreator();
    multiValueQueryCreator = sqlSyntaxProvider.getMultiValueQueryCreator();
    multiReferenceQueryCreator = sqlSyntaxProvider.getMultiReferenceQueryCreator();

    sqlConnection.execute("USE " + databaseName);
  }

  //method
  public Time getSchemaTimestamp() {
    return Time.fromString(
      sqlConnection.getOneRecord(entityQueryCreator.createQueryToLoadSchemaTimestamp()).get(0));
  }

  //method
  public IContainer<String> loadMultiReferenceEntries(
    final String entityId,
    final IColumnInfo multiReferenceColumnInfo) {
    return sqlConnection
      .getRecords(
        multiReferenceQueryCreator.createQueryToLoadMultiReferenceEntries(
          entityId,
          multiReferenceColumnInfo.getColumnId()))
      .to(r -> r.get(0));
  }

  //method
  public IContainer<Object> loadMultiValueEntries(
    final String entityId,
    final IColumnInfo multiValueColumnInfo) {
    return sqlConnection
      .getRecords(
        multiValueQueryCreator.createQueryToLoadMultiValueEntries(
          entityId,
          multiValueColumnInfo.getColumnId()))
      .to(r -> VALUE_MAPPER.createValueFromString(r.get(0), multiValueColumnInfo));
  }

  //method
  public IContainer<ILoadedEntityDto> loadEntitiesOfTable(final ITableInfo tableInfo) {
    return sqlConnection
      .getRecords(entityQueryCreator.createQueryToLoadEntitiesOfTable(tableInfo))
      .to(r -> LOADED_ENTITY_DTO_MAPPER.createLoadedEntityDtoFrosqlRecord(r, tableInfo));
  }

  //method
  public ILoadedEntityDto loadEntity(final ITableInfo tableInfo, final String id) {
    return LOADED_ENTITY_DTO_MAPPER.createLoadedEntityDtoFrosqlRecord(
      sqlConnection.getOneRecord(entityQueryCreator.createQueryToLoadEntity(id, tableInfo)),
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
        .getOneRecord(entityQueryCreator.createQueryToCountEntitiesWithGivenId(tableName, id))
        .get(0));

    return entityCount > 0;
  }

  //method
  private boolean multiReferenceEntryExistsForGivenColumnAndReferencedEntity(
    final String columnId,
    final String referencedEntityId) {
    return sqlConnection.getRecords(
      multiReferenceQueryCreator
        .createQueryToLoadOneOrNoneMultiReferenceEntryForGivenColumnAndReferencedEntity(
          columnId,
          referencedEntityId))
      .containsAny();
  }

  //method
  private boolean multiValueEntryExistsForGivenColumnAndValue(
    final String columnId,
    final String value) {
    return sqlConnection.getRecords(
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
      sqlConnection.getOneRecord(
        entityQueryCreator.createQueryToCountEntitiesWithGivenValueAtGivenColumn(
          tableName,
          singleColumnName,
          value))
        .get(0)) > 0;
  }
}
