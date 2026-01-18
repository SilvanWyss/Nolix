/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.middata.adapter;

import ch.nolix.coreapi.objectcreation.copier.EmptyCopyable;
import ch.nolix.systemapi.midschema.adapter.ISchemaReader;

/**
 * @author Silvan Wyss
 */
public interface IDataAdapterAndSchemaReader
extends EmptyCopyable<IDataAdapterAndSchemaReader>, IDataAdapter, ISchemaReader {
  //This interface is just an union of other interfaces.
}
