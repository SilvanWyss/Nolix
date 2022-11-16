//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//interface
public interface IMultiBackReference<
	IMPL,
	E extends IEntity<IMPL>
> extends IBaseBackReference<IMPL, E> {}
