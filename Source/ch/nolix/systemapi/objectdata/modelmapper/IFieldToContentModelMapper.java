package ch.nolix.systemapi.objectdata.modelmapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectschema.model.IContentModel;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * A {@link IFieldToContentModelMapper} maps {@link IField}s to
 * {@link IContentModel}s.
 * 
 * @author Silvan Wyss
 * @version 2024-01-02
 * @param <F> is the type of the {@link IField}s a
 *            {@link IFieldToContentModelMapper} maps.
 */
public interface IFieldToContentModelMapper<F extends IField> {

  /**
   * @param field
   * @param referencedTables
   * @return a new {@link IContentModel} from the given field using the given
   *         referencedTables.
   */
  IContentModel mapFieldToContentModel(F field, IContainer<ITable> referencedTables);
}
