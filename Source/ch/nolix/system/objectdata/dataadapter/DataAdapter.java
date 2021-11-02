//package declaration
package ch.nolix.system.objectdata.dataadapter;

//own imports
import ch.nolix.system.objectdata.data.Database;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.system.objectdata.data.Property;
import ch.nolix.system.objectdata.data.Table;
import ch.nolix.techapi.objectdataapi.extendeddataadapterapi.IExtendedDataAdapter;

//class
public abstract class DataAdapter implements IExtendedDataAdapter<Database, Table, Entity, Property> {}
