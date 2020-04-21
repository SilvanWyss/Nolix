//package declaration
package ch.nolix.element.smartElementAPI;

//own import
import ch.nolix.element.baseAPI.IMutableElement;

//interface
public interface ISmartMutableElement<SMU extends ISmartMutableElement<SMU>>
extends IMutableElement<SMU>, ISmartElement<SMU> {}
