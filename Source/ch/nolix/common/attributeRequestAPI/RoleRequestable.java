//package declaration
package ch.nolix.common.attributeRequestAPI;

//interface
/**
 * A {@link RoleRequestable} can be asked if it has a given role.
 * 
 * @author Silvan Wyss
 * @month 2020-07
 * @lines 20
 */
public interface RoleRequestable {
	
	//method declaration
	/**
	 * @param role
	 * @return true if the current {@link RoleRequestable} has the given role.
	 */
	public abstract boolean hasRole(final String role);
}
