//package declaration
package ch.nolix.common.attributeAPI;

//interface
/**
 * A {@link SemanticVersioned} has a semantic version.
 * 
 * A semancit version consists of:
 * -major version number
 * -minor version number
 * -patch version number
 * 
 * @author Silvan Wyss
 * @month 2019-02
 * @lines 40
 */
public interface SemanticVersioned {
	
	//method declaration
	/**
	 * @return the major version number of the current {@link SemanticVersioned}.
	 */
	public abstract int getMajorVersion();
	
	//method declaration
	/**
	 * @return the minor version number of the current {@link SemanticVersioned}.
	 */
	public abstract int getMinorVersion();
	
	//method declaration
	/**
	 * @return the patch version number of the current {@link SemanticVersioned}.
	 */
	public abstract int getPatchVersion();
	
	//method
	/**
	 * @return the version of the current {@link SemanticVersioned}.
	 */
	public default String getVersion() {
		return (getMajorVersion() + "." + getMinorVersion() + "." + getPatchVersion());
	}
}
