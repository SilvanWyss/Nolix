//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//interface
public interface IMultiBackReference<
	IMPL,
	E extends IEntity<IMPL>
> extends IBaseBackReference<IMPL, E> {}
