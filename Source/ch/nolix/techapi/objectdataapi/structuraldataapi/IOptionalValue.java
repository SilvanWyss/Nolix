//package declaration
package ch.nolix.techapi.objectdataapi.structuraldataapi;

//own imports
import ch.nolix.common.skillapi.Clearable;

//interface
public interface IOptionalValue<OV extends IOptionalValue<OV, V>, V> extends Clearable, ISingleValue<OV, V> {}
