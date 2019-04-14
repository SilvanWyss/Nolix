//package declaration
package ch.nolix.system.databaseSchemaAdapter;

//own import
import ch.nolix.core.specificationAPI.IElementEnum;

//enum
public enum DatabaseState implements IElementEnum {
	Uninitialized,
	Ready,
	Locked
}
