//package declaration
package ch.nolix.element.smartElementAPI;

//own import
import ch.nolix.element.baseAPI.IElementEnum;

//interface
public interface ISmartElementEnum<SEE extends ISmartElementEnum<SEE>> extends IElementEnum, ISmartElement<SEE> {}
