package ch.nolix.systemapi.sqlmiddata.modelmapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.middata.model.EntityLoadingDto;
import ch.nolix.systemapi.middata.model.FieldDto;
import ch.nolix.systemapi.midschemaview.model.ColumnViewDto;
import ch.nolix.systemapi.midschemaview.model.TableViewDto;

/**
 * @author Silvan Wyss
 */
public interface ILoadedEntityDtoMapper {
  /**
   * @param sqlRecord
   * @param columnViews
   * @return new {@link FieldDto}s from the given sqlRecord.
   */
  IContainer<FieldDto> mapSqlRecordToContentFieldDtos(
    ISqlRecord sqlRecord,
    IContainer<ColumnViewDto> columnViews);

  /**
   * @param sqlRecord
   * @param tableView
   * @return a new {@link EntityLoadingDto} from the given sqlRecord.
   */
  EntityLoadingDto mapSqlRecordToEntityLoadingDto(ISqlRecord sqlRecord, TableViewDto tableView);

}
