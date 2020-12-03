//package declaration
package ch.nolix.common.generalskillapi;

//interface
/**
 * A {@link ISmartObject} provides some general methods that are about itself.
 * 
 * @author Silvan Wyss
 * @date 2018-11-25
 * @lines 10
 * @param <SO> The type of a {@link ISmartObject}.
 */
public interface ISmartObject<SO extends ISmartObject<SO>> extends Castable, Listable<SO>, Transformable<SO> {}
