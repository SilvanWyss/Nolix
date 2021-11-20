//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//interface
public interface IBaseReference<
	IMPL,
	E extends IEntity<IMPL>
> extends IProperty<IMPL> {}
