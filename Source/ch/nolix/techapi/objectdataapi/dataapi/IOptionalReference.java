//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.skillapi.Clearable;

//interface
public interface IOptionalReference<
	OR extends IOptionalReference<OR, E>,
	E extends IEntity<E, OR>
> extends Clearable, ISingleReference<OR, E> {}
