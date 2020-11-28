//package declaration
package ch.nolix.system.databaseschemaadapter;

import ch.nolix.element.elementapi.IElementEnum;

//enum
public enum DatabaseState implements IElementEnum {
	Uninitialized,
	Ready,
	Locked
}
