//package declaration
package ch.nolix.common.skillAPI;

//interface
/**
 * A {@link IBuilder} can build objects.
 * 
 * @author Silvan Wyss
 * @month 2019-03
 * @lines 20
 * @param <O> The type of the objects a {@link IBuilder} builds.
 */
public interface IBuilder<O> {
	
	//method declaration
	/**
	 * @return a new {@link O} from the current {@link IBuilder}.
	 */
	O build();
}
