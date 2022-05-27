//package declaration
package ch.nolix.core.skilluniversalapi;

//interface
/**
 * A {@link ISmartObject} provides some general methods that are about itself.
 * 
 * @author Silvan Wyss
 * @date 2018-11-25
 * @param <SO> is the type of a {@link ISmartObject}.
 */
public interface ISmartObject<SO extends ISmartObject<SO>> extends Castable, Listable<SO>, Transformable<SO> {}
