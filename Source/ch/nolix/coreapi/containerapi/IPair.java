//package declaration
package ch.nolix.coreapi.containerapi;

//interface
/**
 * A {@link IPair} contains 2 elements.
 * 
 * @author Silvan Wyss
 * @date 2022-07-02
 * @param <E1> is the type of the element1 of a {@link IPair}.
 * @param <E2> is the type of the element2 of a {@link IPair}.
 */
public interface IPair<E1, E2> {
	
	//method declaration
	/**
	 * @return the element1 of the current {@link IPair}.
	 */
	E1 getRefElement1();
	
	//method declaration
	/**
	 * @return the element2 of the current {@link IPair}.
	 */
	E2 getRefElement2();
}
