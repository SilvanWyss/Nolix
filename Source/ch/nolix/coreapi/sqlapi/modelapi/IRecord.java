package ch.nolix.coreapi.sqlapi.modelapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IOneBaseIndexed;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

/**
 * A {@link IRecord} represents a SQL record. A {@link IRecord} stores each
 * value as a {@link String}. An empty field, that means a null field, is
 * represented with a {@link String} that is 'NULL'.
 * 
 * @author Silvan Wyss
 * @version 2024-12-28
 */
public interface IRecord extends IContainer<String>, IOneBaseIndexed {
}
