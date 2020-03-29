//package declaration
package ch.nolix.system.databaseSchemaAdapter;

//own imports
import ch.nolix.element.baseAPI.IElementEnum;

//enum
public enum DatabaseState implements IElementEnum {
	Uninitialized,
	Ready,
	Locked
}
