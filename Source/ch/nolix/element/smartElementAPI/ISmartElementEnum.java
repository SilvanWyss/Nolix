//package declaration
package ch.nolix.element.smartElementAPI;

import ch.nolix.element.elementAPI.IElementEnum;

//interface
public interface ISmartElementEnum<SEE extends ISmartElementEnum<SEE>> extends IElementEnum, ISmartElement<SEE> {}
