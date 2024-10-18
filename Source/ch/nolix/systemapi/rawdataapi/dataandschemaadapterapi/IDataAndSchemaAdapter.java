package ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi;

import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataReader;
import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataWriter;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaReader;

public interface IDataAndSchemaAdapter extends IDataReader, IDataWriter, ISchemaReader {
}
