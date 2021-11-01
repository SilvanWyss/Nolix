//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//interface
public interface IBackReference<
	BR extends IBackReference<BR, E>,
	E extends IEntity<E, BR>
> extends ISingleBackReference<BR, E> {}
