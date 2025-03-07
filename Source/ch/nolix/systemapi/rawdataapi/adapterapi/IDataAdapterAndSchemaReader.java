package ch.nolix.systemapi.rawdataapi.adapterapi;

import ch.nolix.coreapi.structurecontrolapi.copierapi.EmptyCopyable;
import ch.nolix.systemapi.rawschemaapi.adapterapi.ISchemaReader;

/**
 * @author Silvan Wyss
 * @version 2025-01-01
 */
public interface IDataAdapterAndSchemaReader
extends EmptyCopyable<IDataAdapterAndSchemaReader>, IDataAdapter, ISchemaReader {
}
