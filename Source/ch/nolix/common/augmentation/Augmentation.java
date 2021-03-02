//package declaration
package ch.nolix.common.augmentation;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;

//class
public abstract class Augmentation<AP> {
	
	//attribute
	private final AP augmentedParent;
	
	//constructor
	public Augmentation(final AP augmentedParent) {
		
		//For a better performance, this implementation does not use all comfortable methods.
		if (augmentedParent == null) {
			throw new ArgumentIsNullException("augmented parent");
		}
		
		this.augmentedParent = augmentedParent;
	}
	
	//method
	protected final AP getRefAugmentedParent() {
		return augmentedParent;
	}
}
