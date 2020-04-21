//package declaration
package ch.nolix.element.smartElementAPI;

//own imports
import ch.nolix.common.generalSkillAPI.ISmartObject;
import ch.nolix.element.baseAPI.IElement;

//interface
public interface ISmartElement<SE extends ISmartElement<SE>> extends IElement, ISmartObject<SE> {}
