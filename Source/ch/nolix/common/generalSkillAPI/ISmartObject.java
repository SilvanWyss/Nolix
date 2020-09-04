//package declaration
package ch.nolix.common.generalSkillAPI;

//interface
/**
 * A {@link ISmartObject} provides general methods on itself
 * that are independent from its specific purpose.
 * 
 * @author Silvan Wyss
 * @month 2018-11
 * @lines 10
 * @param <SO> The type of a {@link ISmartObject}.
 */
public interface ISmartObject<SO extends ISmartObject<SO>> extends Castable, Listable<SO>, Transformable<SO> {}
