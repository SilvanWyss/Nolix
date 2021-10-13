//package declaration
package ch.nolix.techapi.objectdataapi.structuraldataapi;

//own imports
import ch.nolix.common.skillapi.Clearable;

//interface
public interface IOptionalBackReference<
	OBR extends IOptionalBackReference<OBR, SE>,
	SE extends IStructuralEntity<SE, OBR>
> extends Clearable, ISingleBackReference<OBR, SE> {}
