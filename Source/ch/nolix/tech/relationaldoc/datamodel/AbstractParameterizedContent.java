//package declaration
package ch.nolix.tech.relationaldoc.datamodel;

//own imports
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IParameterizedValueContent;

//class
public abstract class AbstractParameterizedContent<V> extends Entity implements IParameterizedValueContent<V> {}
