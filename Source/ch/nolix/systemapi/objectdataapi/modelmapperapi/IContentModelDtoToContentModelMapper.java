package ch.nolix.systemapi.objectdataapi.modelmapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IContentModelView;
import ch.nolix.systemapi.rawschemaapi.modelapi.IContentModelDto;

/**
 * A {@link IContentModelDtoToContentModelMapper} maps {@link IContentModelDto}s
 * to {@link IContentModelView}s.
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
   * @return a new {@link IContentModelView} from the given contentModelDto using
   *         the given referencedTables.
   */
  IContentModelView mapContentModelDtoToContentModelView(
    T contentModelDto,
    IContainer<? extends ITable<IEntity>> referencedTables);
}
