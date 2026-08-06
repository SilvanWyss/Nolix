/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.middata.adapter;

import ch.nolix.baseapi.programcontrol.copy.EmptyCopyable;
import ch.nolix.systemapi.midschema.adapter.ISchemaReader;

/**
 * @author Silvan Wyss
 */
public interface DataAdapterAndSchemaReader
extends EmptyCopyable<DataAdapterAndSchemaReader>, DataAdapter, ISchemaReader {
  // This interface is a dedicated union of other interfaces.
}
