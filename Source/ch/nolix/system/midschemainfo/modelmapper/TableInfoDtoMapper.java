/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.midschemainfo.modelmapper;

import ch.nolix.systemapi.midschema.databasestructure.FixDatabasePropertyCatalogue;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;
import ch.nolix.systemapi.midschemainfo.modelmapper.ITableInfoDtoMapper;

/**
 * @author Silvan Wyss
 */
public final class TableInfoDtoMapper implements ITableInfoDtoMapper {
  private static final ColumnInfoDtoMapper COLUMN_VIEW_DTO_MAPPER = new ColumnInfoDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public TableInfoDto mapMidSchemaTableDtoToTableViewDto(final TableDto table) {
    final var id = table.id();
    final var name = table.name();
    final var columns = table.columns();

    final var columnViews = //
    columns
      .toWithOneBasedIndex((i, c) -> //
      COLUMN_VIEW_DTO_MAPPER.mapMidSchemaColumnDtoToColumnViewDto(
        c,
        FixDatabasePropertyCatalogue.NUMBER_OF_ENTITY_META_FIELDS + i));

    return new TableInfoDto(id, name, columnViews);
  }
}
