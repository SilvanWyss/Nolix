package ch.nolix.systemapi.middata.adapter;

import ch.nolix.coreapi.creation.copier.EmptyCopyable;
import ch.nolix.systemapi.midschema.adapter.ISchemaReader;

/**
 * @author Silvan Wyss
 * @version 2025-01-01
 */
public interface IDataAdapterAndSchemaReader
extends EmptyCopyable<IDataAdapterAndSchemaReader>, IDataAdapter, ISchemaReader {
}
