//package declaration
package ch.nolix.systemapi.rawdatabaseapi.databaseandschemaadapterapi;

import ch.nolix.systemapi.rawdatabaseapi.databaseadapterapi.IDatabaseReader;
import ch.nolix.systemapi.rawdatabaseapi.databaseadapterapi.IDataWriter;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaReader;

//interface
public interface IDataAndSchemaAdapter extends IDatabaseReader, IDataWriter, ISchemaReader {}
