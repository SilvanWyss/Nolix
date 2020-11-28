//package declaration
package ch.nolix.element.smartelementapi;

import ch.nolix.element.elementapi.IElementEnum;

//interface
public interface ISmartElementEnum<SEE extends ISmartElementEnum<SEE>> extends IElementEnum, ISmartElement<SEE> {}
