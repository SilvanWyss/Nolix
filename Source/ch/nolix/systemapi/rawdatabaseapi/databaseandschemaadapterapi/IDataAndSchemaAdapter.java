//package declaration
package ch.nolix.systemapi.rawdatabaseapi.databaseandschemaadapterapi;

import ch.nolix.systemapi.rawdatabaseapi.dataadapterapi.IDataReader;
import ch.nolix.systemapi.rawdatabaseapi.dataadapterapi.IDataWriter;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaReader;

//interface
public interface IDataAndSchemaAdapter extends IDataReader, IDataWriter, ISchemaReader {
}
