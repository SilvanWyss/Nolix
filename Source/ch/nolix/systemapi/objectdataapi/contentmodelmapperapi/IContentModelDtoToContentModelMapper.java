package ch.nolix.systemapi.objectdataapi.contentmodelmapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IContentModel;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadto.IContentModelDto;

/**
 * A {@link IContentModelDtoToContentModelMapper} maps {@link IContentModelDto}s
 * to {@link IContentModel}s.
 * 
 * @author Silvan Wyss
 * @version 2023-12-28
 * @param <T> is the type of the {@link IContentModelDto}s a
 *            {@link IContentModelDtoToContentModelMapper} maps.
 */
public interface IContentModelDtoToContentModelMapper<T extends IContentModelDto> {

  /**
   * @param contentModelDto
   * @param referencedTables
   * @return a new {@link IContentModel} from the given contentModelDto using the
   *         given referencedTables.
   */
  IContentModel mapContentModelDtoToContentModel(
    T contentModelDto,
    IContainer<? extends ITable<IEntity>> referencedTables);
}