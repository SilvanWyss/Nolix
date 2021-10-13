//package declaration
package ch.nolix.techapi.objectdataapi.structuraldataapi;

//interface
public interface IReference<
	R extends IReference<R, SE>,
	SE extends IStructuralEntity<SE, R>>
extends ISingleReference<R, SE> {}
