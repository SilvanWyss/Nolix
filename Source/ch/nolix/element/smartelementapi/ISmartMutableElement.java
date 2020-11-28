//package declaration
package ch.nolix.element.smartelementapi;

import ch.nolix.element.elementapi.IMutableElement;

//interface
public interface ISmartMutableElement<SMU extends ISmartMutableElement<SMU>>
extends IMutableElement<SMU>, ISmartElement<SMU> {}
