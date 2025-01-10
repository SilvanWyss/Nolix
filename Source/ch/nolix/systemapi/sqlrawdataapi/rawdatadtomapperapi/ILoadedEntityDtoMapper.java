package ch.nolix.systemapi.sqlrawdataapi.rawdatadtomapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.rawdataapi.dto.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.dto.EntityLoadingDto;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.IColumnView;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.ITableView;

/**
 * @author Silvan Wyss
 * @version 2024-12-28
 */
public interface ILoadedEntityDtoMapper {

  /**
   * @param paramRecord
   * @param columnViews
   * @return new {@link ContentFieldDto}s from the given paramRecord.
   * @throws RuntimeException if the given paramRecord is not valid.
   * @throws RuntimeException if the given columnViews is not valid.
   */
  IContainer<ContentFieldDto<Object>> mapRecordToContentFieldDtos(
    ISqlRecord paramRecord,
    IContainer<IColumnView> columnViews);

  /**
   * @param paramRecord
   * @param tableView
   * @return a new {@link EntityLoadingDto} from the given paramRecord.
   * @throws RuntimeException if the given paramRecord is not valid.
   * @throws RuntimeException if the given tableView is not valid.
   */
  EntityLoadingDto mapRecordToEntityLoadingDto(ISqlRecord paramRecord, ITableView tableView);

}
