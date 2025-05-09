package ch.nolix.systemapi.sqlmiddataapi.modelmapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.middataapi.modelapi.EntityLoadingDto;
import ch.nolix.systemapi.middataapi.modelapi.ObjectValueFieldDto;
import ch.nolix.systemapi.middataapi.schemaviewmodel.ColumnSchemaViewDto;
import ch.nolix.systemapi.middataapi.schemaviewmodel.TableSchemaViewDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-28
 */
public interface ILoadedEntityDtoMapper {

  /**
   * @param sqlRecord
   * @param columnViews
   * @return new {@link ObjectValueFieldDto}s from the given sqlRecord.
   */
  IContainer<ObjectValueFieldDto> mapSqlRecordToContentFieldDtos(
    ISqlRecord sqlRecord,
    IContainer<ColumnSchemaViewDto> columnViews);

  /**
   * @param sqlRecord
   * @param tableView
   * @return a new {@link EntityLoadingDto} from the given sqlRecord.
   */
  EntityLoadingDto mapSqlRecordToEntityLoadingDto(ISqlRecord sqlRecord, TableSchemaViewDto tableView);

}
