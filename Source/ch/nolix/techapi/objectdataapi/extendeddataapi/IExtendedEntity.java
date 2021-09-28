//package declaration
package ch.nolix.techapi.objectdataapi.extendeddataapi;

//own imports
import ch.nolix.techapi.databaseapi.databaseobjectapi.IExtendedDatabaseObject;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;

//interface
public interface IExtendedEntity<EC extends IExtendedEntity<EC>> extends IEntity<EC>, IExtendedDatabaseObject {}
