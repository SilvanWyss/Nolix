//package declaration
package ch.nolix.core.skillapi;

//interface
/**
 * A {@link IBuilder} can build objects.
 * 
 * @author Silvan Wyss
 * @date 2019-03-10
 * @param <O> is the type of the objects a {@link IBuilder} builds.
 */
public interface IBuilder<O> {
	
	//method declaration
	/**
	 * @return a new {@link Object} from the current {@link IBuilder}.
	 */
	O build();
}
