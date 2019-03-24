//package declaration
package ch.nolix.system.databaseSchemaAdapter;

//own import
import ch.nolix.core.specificationAPI.ISpecifiedEnum;

//enum
public enum DatabaseState implements ISpecifiedEnum {
	Uninitialized,
	Ready,
	Locked
}
