//package declaration
package ch.nolix.tech.asset;

//own imports
import ch.nolix.core.bases.NamedElement;
import ch.nolix.core.container.AccessorContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.ErrorPredicate;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;

//class
/**
 * An asset is a piece of something that has a value.
 * Of an asset, a certain amount can be possessed.
 * An asset has a determined name.
 * An asset can have super assets.
 * An asset serves as any of its super assets.
 * An asset is not mutable. 
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 150
 */
public class Asset extends NamedElement {
	
	//multiple attribute
	//The super assets are the assets this asset can serve as.
	public final AccessorContainer<Asset> superAssets;

	//constructor
	/**
	 * Creates new asset with the given name.
	 * 
	 * @param name
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 */
	public Asset(final String name) {
		
		//Calls constructor of the base class.
		super(name);
		
		superAssets = new AccessorContainer<Asset>();
	}
	
	//constructor
	/**
	 * Creates new asset with the given name and assets.
	 * 
	 * @param name
	 * @param superAssets
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws InvalidArgumentException if the given name
	 * equals the name of one of the given super assets.
	 */
	public Asset(final String name, final Asset... superAssets) {
		
		//Calls constructor of the base class.
		super(name);
		
		final List<Asset> internalSuperAssets = new List<Asset>();
		
		//Iterates the given assets.
		for (final Asset sa : superAssets) {
			
			//Checks if the given name equals the name of the current asset.
			if (hasSameNameAs(sa)) {
				throw new InvalidArgumentException(
					new ArgumentName("name"),
					new Argument(name),
					new ErrorPredicate(
						"equals the name of the given super asset " + sa.getName()
					)
				);
			}
			
			internalSuperAssets.addAtEnd(sa);
		}
		
		this.superAssets = new AccessorContainer<Asset>(internalSuperAssets);
	}
	
	//method
	/**
	 * @return the number of super assets of this asset.
	 */
	public final int getSuperAssetCount() {
		return getSuperAssets().getElementCount();
	}
	
	//method
	/**
	 * @return the super assets of this asset.
	 */
	public final AccessorContainer<Asset> getSuperAssets() {
		return superAssets;
	}
	
	//method
	/**
	 * @param asset
	 * @return true if this asset is a direct sub asset of the given asset.
	 */
	public final boolean isDirectSubAssetOf(final Asset asset) {
		return getSuperAssets().contains(asset);
	}
	
	//method
	/**
	 * @param asset
	 * @return true if this asset is a direct super asset of the given asset.
	 */
	public final boolean isDirectSuperAssetOf(final Asset asset) {
		return asset.isDirectSubAssetOf(this);
	}
	
	//method
	/**
	 * @param asset
	 * @return true if this asset is a sub asset of the given asset.
	 */
	public final boolean isSubAssetOf(final Asset asset) {
		
		//Handles the case if this asset is a direct sub asset of the given asset.
		if (isDirectSubAssetOf(asset)) {
			return true;
		}
		
		//Handles the case if this asset is no direct sub asset of the given asset.
			//Iterates the super assets of this asset.
			for (final Asset sa : getSuperAssets()) {
				
				//Checks if the current super asset is a sub asset of the given asset.
				if (sa.isSubAssetOf(asset)) {
					return true;
				}
			}
		
		return false;
	}
	
	//method
	/**
	 * @param asset
	 * @return true if this asset is a super asset of the given asset.
	 */
	public final boolean isSuperAssetOf(final Asset asset) {
		return asset.isSubAssetOf(this);
	}
}
