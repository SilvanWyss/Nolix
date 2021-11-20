//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//interface
public interface IBaseBackReference<
	IMPL,
	E extends IEntity<IMPL>
> extends IProperty<IMPL> {}
