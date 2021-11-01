//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.skillapi.Clearable;

//interface
public interface IOptionalBackReference<
	OBR extends IOptionalBackReference<OBR, E>,
	E extends IEntity<E, OBR>
> extends Clearable, ISingleBackReference<OBR, E> {}
