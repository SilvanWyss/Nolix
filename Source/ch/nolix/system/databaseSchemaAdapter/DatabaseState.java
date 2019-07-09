//package declaration
package ch.nolix.system.databaseSchemaAdapter;

import ch.nolix.element.elementAPI.IElementEnum;

//enum
public enum DatabaseState implements IElementEnum {
	Uninitialized,
	Ready,
	Locked
}
