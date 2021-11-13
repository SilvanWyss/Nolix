//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//interface
public interface IBaseReference<
	P extends IProperty<P>,
	E extends IEntity<E, P>
> extends IProperty<P> {}
