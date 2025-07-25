package ch.nolix.coreapi.sqlapi.modelapi;

import ch.nolix.coreapi.attribute.mandatoryattribute.IOneBasedIndexHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

/**
 * A {@link ISqlRecord} represents a SQL record. A {@link ISqlRecord} stores
 * each value as a {@link String}. An empty field, that means a null field, is
 * represented with a {@link String} that is 'NULL'.
 * 
 * @author Silvan Wyss
 * @version 2024-12-28
 */
public interface ISqlRecord extends IContainer<String>, IOneBasedIndexHolder {
}
