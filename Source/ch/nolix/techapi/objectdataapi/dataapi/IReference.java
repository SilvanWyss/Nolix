//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//interface
public interface IReference<
	R extends IReference<R, E>,
	E extends IEntity<E, R>>
extends ISingleReference<R, E> {}
