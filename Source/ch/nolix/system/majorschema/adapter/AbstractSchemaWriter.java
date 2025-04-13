package ch.nolix.system.majorschema.adapter;

import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.system.majorschema.midschemamodelmapper.MidSchemaColumnDtoMapper;
import ch.nolix.system.majorschema.midschemamodelmapper.MidSchemaTableDtoMapper;
import ch.nolix.systemapi.majorschemaapi.adapterapi.ISchemaWriter;
import ch.nolix.systemapi.majorschemaapi.midschemamodelmapperapi.IMidSchemaColumnDtoMapper;
import ch.nolix.systemapi.majorschemaapi.midschemamodelmapperapi.IMidSchemaTableDtoMapper;
import ch.nolix.systemapi.majorschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.majorschemaapi.modelapi.TableDto;

public abstract class AbstractSchemaWriter implements ISchemaWriter {

  private static final IMidSchemaTableDtoMapper MID_SCHEMA_TABLE_DTO_MAPPER = new MidSchemaTableDtoMapper();

  private static final IMidSchemaColumnDtoMapper MID_SCHEMA_COLUMN_DTO_MAPPER = new MidSchemaColumnDtoMapper();

  private final ch.nolix.systemapi.midschemaapi.adapterapi.ISchemaWriter midSchemaWriter;

  protected AbstractSchemaWriter(final ch.nolix.systemapi.midschemaapi.adapterapi.ISchemaWriter midSchemaWriter) {

    ResourceValidator.assertIsOpen(midSchemaWriter);

    this.midSchemaWriter = midSchemaWriter;
  }

  @Override
  public final void addColumn(final String tableName, final ColumnDto column) {

    final var midSchemaColumns = MID_SCHEMA_COLUMN_DTO_MAPPER.mapColumnDtoToMidSchemaColumnDtos(column);

    midSchemaWriter.addColumns(tableName, midSchemaColumns);
  }

  @Override
  public final void addTable(final TableDto table) {

    final var midSchemaTable = MID_SCHEMA_TABLE_DTO_MAPPER.mapTableDtoToMidSchemaTableDto(table);

    midSchemaWriter.addTable(midSchemaTable);
  }

  @Override
  public final void deleteColumn(final String tableName, final String columnName) {
    midSchemaWriter.deleteColumn(tableName, columnName);
  }

  @Override
  public final void deleteTable(final String tableName) {
    midSchemaWriter.deleteTable(tableName);
  }

  @Override
  public final int getSaveCount() {
    return midSchemaWriter.getSaveCount();
  }

  @Override
  public final ICloseController getStoredCloseController() {
    return midSchemaWriter.getStoredCloseController();
  }

  @Override
  public final boolean hasChanges() {
    return midSchemaWriter.hasChanges();
  }

  @Override
  public final void noteClose() {
    //Does nothing.
  }

  @Override
  public final void renameTable(final String tableName, final String newTableName) {
    midSchemaWriter.renameTable(tableName, newTableName);
  }

  @Override
  public final void reset() {
    midSchemaWriter.reset();
  }

  @Override
  public final void saveChanges() {
    midSchemaWriter.saveChanges();
  }
}
