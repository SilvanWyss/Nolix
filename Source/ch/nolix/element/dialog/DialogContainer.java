/*
 * file:	DialogContainer.java
 * auhtor:	Silvan Wyss
 * month:	2015
 * lines:	40
 */

//element
package ch.nolix.element.dialog;

import java.awt.Graphics;

//class
public final class DialogContainer
extends BorderableRectangle<SimpleBorderableRectangleStructure, DialogContainer> {

	//constants
	public static final String SIMPLE_CLASS_NAME = "DialogContainer";
	public static final String NONE = "NoDialogContainer";
	
	//attributes
	private InnerDialog innerDialog;
	
	//temp
	public DialogContainer(InnerDialog innerDialog) {
		
		//Calls constructor of the base class.
		super(new SimpleBorderableRectangleStructure(), new SimpleBorderableRectangleStructure(), new SimpleBorderableRectangleStructure());
		
		this.innerDialog = innerDialog;
	}
	
	//constructor
	/**
	 * Creates new dialog container that has default attributes.
	 */
	public DialogContainer() {
		
		//Calls constructor of the base class.
		super(new SimpleBorderableRectangleStructure(), new SimpleBorderableRectangleStructure(), new SimpleBorderableRectangleStructure());
				
		resetConfiguration();
	}
	
	//method
	/**
	 * @return the current height of the content of this borderable rectangle
	 */
	protected final int getContentHeight() {
		return innerDialog.getRefRootRectangle().getHeightWhenNotCollapsed();
	}
	
	//method
	/**
	 * @return the current width of the content of this borderable rectangle
	 */
	protected final int getContentWidth() {
		return innerDialog.getRefRootRectangle().getWidth();
	}
	
	public final boolean hasRole(final String role) {
		return false;
	}
	
	protected final void setRelativePosition(int distanceFromLeftPanelBorder, int distanceFromTopPanelBorder) {
		
		//Calls method of the base class.
		super.setRelativePosition(distanceFromLeftPanelBorder, distanceFromTopPanelBorder);
		
		innerDialog.getRefRootRectangle().setRelativePosition(getContentXPosition(), getContentYPosition());
	}

	@Override
	protected void paintContent(
			SimpleBorderableRectangleStructure rectangleStructure,
			Graphics graphics) {
		// TODO Auto-generated method stub
		
	}
}
