/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.sql.model;

import ch.nolix.baseapi.attribute.mandatoryattribute.OneBasedIndexHolder;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * A {@link ISqlRecord} represents a SQL record. A {@link ISqlRecord} stores
 * each value as a {@link String}. An empty field, that means a null field, is
 * represented with a {@link String} that is 'NULL'.
 * 
 * @author Silvan Wyss
 */
public interface ISqlRecord extends ExtendedIterable<String>, OneBasedIndexHolder {
  //This interface is a dedicated union of other interfaces.
}
