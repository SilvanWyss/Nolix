/*
 * file:	MockDialog.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	40
 */

//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.specification.Specification;

//class
public final class MockDialog extends GUI<MockDialog> {
	
	//constructor
	/**
	 * Creates new mock dialog.
	 */
	public MockDialog() {}

	//constructor
	/**
	 * Creates new mock dialog with the given attributes.
	 * 
	 * @param attributes
	 * @throws Exception if the given attributes are not valid
	 */
	public MockDialog(List<Specification> attributes) {
		addOrChangeAttributes(attributes);
	}
	
	public void updateFromInteraction() {
		// TODO Auto-generated method stub
		
	}
}
