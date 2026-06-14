/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmiddata.loader;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.sql.connection.ISqlConnection;
import ch.nolix.system.sqlmiddata.modelmapper.LoadedEntityDtoMapper;
import ch.nolix.system.sqlmiddata.querycreator.EntityQueryCreator;
import ch.nolix.systemapi.middata.loader.ISchematicEntityLoader;
import ch.nolix.systemapi.middata.model.EntityLoadingDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;

/**
 * @author Silvan Wyss
 */
public final class SchematicEntityLoader implements ISchematicEntityLoader {
  private static final EntityQueryCreator ENTITY_QUERY_CREATOR = new EntityQueryCreator();

  private static final LoadedEntityDtoMapper LOADED_ENTITY_DTO_MAPPER = new LoadedEntityDtoMapper();

  private final ISqlConnection sqlConnection;

  private SchematicEntityLoader(final ISqlConnection sqlConnection) {
    Validator.assertThat(sqlConnection).thatIsNamed(ISqlConnection.class).isNotNull();

    this.sqlConnection = sqlConnection;
  }

  public static SchematicEntityLoader withSqlConnection(final ISqlConnection sqlConnection) {
    return new SchematicEntityLoader(sqlConnection);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IWellOrderContainer<EntityLoadingDto> loadEntitiesByTable(final TableInfoDto tableView) {
    final var query = ENTITY_QUERY_CREATOR.createQueryToLoadEntitiesByTable(tableView);
    final var records = sqlConnection.getRecordsFromQuery(query);

    return records.to(r -> LOADED_ENTITY_DTO_MAPPER.mapSqlRecordToEntityLoadingDto(r, tableView));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EntityLoadingDto loadEntityByTableAndId(final TableInfoDto tableView, final String id) {
    final var query = ENTITY_QUERY_CREATOR.createQueryToLoadEntityByTableAndId(id, tableView);
    final var sqlRecord = sqlConnection.getSingleRecordFromQuery(query);

    return LOADED_ENTITY_DTO_MAPPER.mapSqlRecordToEntityLoadingDto(sqlRecord, tableView);
  }
}
