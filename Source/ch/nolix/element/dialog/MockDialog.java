/*
 * file:	MockDialog.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	40
 */

//package declaration
package ch.nolix.element.dialog;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.specification.Specification;

//class
public final class MockDialog extends Dialog<MockDialog> {
	
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

	//method
	/**
	 * @throws Exception
	 */
	public FrameContext getRefFrameContext() {
		throw new RuntimeException("Unsupported method");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
