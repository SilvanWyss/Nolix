//package declaration
package ch.nolix.systemapi.rawdatabaseapi.databaseandschemaadapterapi;

import ch.nolix.systemapi.rawdatabaseapi.databaseadapterapi.IDataReader;
import ch.nolix.systemapi.rawdatabaseapi.databaseadapterapi.IDataWriter;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaReader;

//interface
public interface IDataAndSchemaAdapter extends IDataReader, IDataWriter, ISchemaReader {}
