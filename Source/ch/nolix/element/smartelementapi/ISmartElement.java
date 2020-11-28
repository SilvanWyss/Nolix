//package declaration
package ch.nolix.element.smartelementapi;

import ch.nolix.common.generalskillapi.ISmartObject;
import ch.nolix.element.elementapi.IElement;

//interface
public interface ISmartElement<SE extends ISmartElement<SE>> extends IElement, ISmartObject<SE> {}
