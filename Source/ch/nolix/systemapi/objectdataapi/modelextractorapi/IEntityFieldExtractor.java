package ch.nolix.systemapi.objectdataapi.modelextractorapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;

/**
 * @author Silvan Wyss
 * @version 2025-02-01
 * @param <E> is the type of the {@link IEntity}s a
 *            {@link IEntityFieldExtractor} can extract {@link IField}s from.
 * @param <F> is the type of the {@link IField}s of the {@link IEntity}s a
 *            {@link IEntityFieldExtractor} can extract.
 */
public interface IEntityFieldExtractor<E extends IEntity, F extends IField> {

  /**
   * @param entity
   * @return the {@link IField}s from the given entity.
   */
  IContainer<F> extractStoredFieldsFromEntity(final E entity);
}
