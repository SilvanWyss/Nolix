/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.middata.adapter;

import ch.nolix.baseapi.programcontrol.copy.EmptyCopyable;
import ch.nolix.systemapi.midschema.adapter.ISchemaReader;

/**
 * @author Silvan Wyss
 */
public interface IDataAdapterAndSchemaReader
extends EmptyCopyable<IDataAdapterAndSchemaReader>, IDataAdapter, ISchemaReader {
  // This interface is a dedicated union of other interfaces.
}
