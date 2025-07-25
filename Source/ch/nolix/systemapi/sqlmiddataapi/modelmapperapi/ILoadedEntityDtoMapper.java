package ch.nolix.systemapi.sqlmiddataapi.modelmapperapi;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.middataapi.modelapi.EntityLoadingDto;
import ch.nolix.systemapi.middataapi.modelapi.ObjectValueFieldDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.ColumnViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.TableViewDto;

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
    IContainer<ColumnViewDto> columnViews);

  /**
   * @param sqlRecord
   * @param tableView
   * @return a new {@link EntityLoadingDto} from the given sqlRecord.
   */
  EntityLoadingDto mapSqlRecordToEntityLoadingDto(ISqlRecord sqlRecord, TableViewDto tableView);

}
