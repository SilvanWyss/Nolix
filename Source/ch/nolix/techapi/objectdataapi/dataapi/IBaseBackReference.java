//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//interface
public interface IBaseBackReference<
	P extends IProperty<P>,
	E extends IEntity<E, P>
> extends IProperty<P> {}
