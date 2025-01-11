package ch.nolix.systemapi.sqlrawdataapi.rawdatadtomapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.rawdataapi.model.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.model.EntityLoadingDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.ColumnViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.TableViewDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-28
 */
public interface ILoadedEntityDtoMapper {

  /**
   * @param sqlRecord
   * @param columnViews
   * @return new {@link ContentFieldDto}s from the given sqlRecord.
   */
  IContainer<ContentFieldDto<Object>> mapSqlRecordToContentFieldDtos(
    ISqlRecord sqlRecord,
    IContainer<ColumnViewDto> columnViews);

  /**
   * @param sqlRecord
   * @param tableView
   * @return a new {@link EntityLoadingDto} from the given sqlRecord.
   */
  EntityLoadingDto mapSqlRecordToEntityLoadingDto(ISqlRecord sqlRecord, TableViewDto tableView);

}
