package ch.nolix.system.majorschema.adapter;

import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.system.majorschema.modelmapper.ColumnDtoMapper;
import ch.nolix.system.majorschema.modelmapper.TableDtoMapper;
import ch.nolix.systemapi.majorschemaapi.adapterapi.ISchemaReader;
import ch.nolix.systemapi.majorschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.majorschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.majorschemaapi.modelmapperapi.IColumnDtoMapper;
import ch.nolix.systemapi.majorschemaapi.modelmapperapi.ITableDtoMapper;
import ch.nolix.systemapi.midschemaapi.flatmodelapi.FlatTableDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public abstract class AbstractSchemaReader implements ISchemaReader {

  private static final ITableDtoMapper TABLE_DTO_MAPPER = new TableDtoMapper();

  private static final IColumnDtoMapper COLUMN_DTO_MAPPER = new ColumnDtoMapper();

  private final ch.nolix.systemapi.midschemaapi.adapterapi.ISchemaReader midSchemaReader;

  protected AbstractSchemaReader(final ch.nolix.systemapi.midschemaapi.adapterapi.ISchemaReader midSchemaReader) {

    ResourceValidator.assertIsOpen(midSchemaReader);

    this.midSchemaReader = midSchemaReader;
  }

  @Override
  public final boolean columnIsEmpty(final String tableName, final String columnName) {
    return midSchemaReader.columnIsEmpty(tableName, columnName);
  }

  @Override
  public final ICloseController getStoredCloseController() {
    return midSchemaReader.getStoredCloseController();
  }

  @Override
  public final IContainer<ColumnDto> loadColumns(final String tableName) {

    final var midSchemaColumns = midSchemaReader.loadColumnsByTableName(tableName);

    return COLUMN_DTO_MAPPER.mapMidSchemaColumnDtosToColumnDtos(midSchemaColumns);
  }

  @Override
  public final FlatTableDto loadFlatTable(final String tableName) {
    return midSchemaReader.loadFlatTableByName(tableName);
  }

  @Override
  public final IContainer<FlatTableDto> loadFlatTables() {
    return midSchemaReader.loadFlatTables();
  }

  @Override
  public final TableDto loadTable(final String tableName) {

    final var midSchemaTableDto = midSchemaReader.loadTableByName(tableName);

    return TABLE_DTO_MAPPER.mapMidSchemaTableDtoToTableDto(midSchemaTableDto);
  }

  @Override
  public final int loadTableCount() {
    return midSchemaReader.getTableCount();
  }

  @Override
  public final IContainer<TableDto> loadTables() {

    final var midSchemaTableDtos = midSchemaReader.loadTables();

    return midSchemaTableDtos.to(TABLE_DTO_MAPPER::mapMidSchemaTableDtoToTableDto);
  }

  @Override
  public final ITime loadSchemaTimestamp() {
    return midSchemaReader.loadSchemaTimestamp();
  }

  @Override
  public final void noteClose() {
    //Does nothing.
  }
}
