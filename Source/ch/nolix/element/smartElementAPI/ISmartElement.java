//package declaration
package ch.nolix.element.smartElementAPI;

import ch.nolix.common.generalskillapi.ISmartObject;
import ch.nolix.element.elementAPI.IElement;

//interface
public interface ISmartElement<SE extends ISmartElement<SE>> extends IElement, ISmartObject<SE> {}
