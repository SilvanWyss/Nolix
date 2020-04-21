//package declaration
package ch.nolix.element.smartElementAPI;

import ch.nolix.element.elementAPI.IMutableElement;

//interface
public interface ISmartMutableElement<SMU extends ISmartMutableElement<SMU>>
extends IMutableElement<SMU>, ISmartElement<SMU> {}
