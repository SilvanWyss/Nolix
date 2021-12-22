//package declaration
package ch.nolix.techapi.rawobjectdataapi.dataandschemaadapterapi;

//own imports
import ch.nolix.techapi.rawobjectdataapi.dataadapterapi.IDataReader;
import ch.nolix.techapi.rawobjectdataapi.dataadapterapi.IDataWriter;
import ch.nolix.techapi.rawobjectschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.techapi.rawobjectschemaapi.schemaadapterapi.ISchemaWriter;

//interface
public interface IDataAndSchemaAdapter extends IDataReader, IDataWriter, ISchemaReader, ISchemaWriter {}
