//package declaration
package ch.nolix.techapi.objectdataapi.structuraldataapi;

//own imports
import ch.nolix.common.skillapi.Clearable;

//interface
public interface IOptionalReference<
	OR extends IOptionalReference<OR, SE>,
	SE extends IStructuralEntity<SE, OR>
> extends Clearable, ISingleReference<OR, SE> {}
