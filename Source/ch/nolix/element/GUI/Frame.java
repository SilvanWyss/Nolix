/*
 * file:	Frame.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	430
 */

//package declaration
package ch.nolix.element.GUI;

//Java imports
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;


//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.mathematics.Calculator;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.specification.Statement;
import ch.nolix.element.basic.Color;
import ch.nolix.element.data.BackgroundColor;

//class
public final class Frame extends GUI<Frame> {
	
	//constant
	public static final String SIMPLE_CLASS_NAME = "Frame";
	
	//default values
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 500;
	private static final int DEFAULT_X_POSITION = 200;
	private static final int DEFAULT_Y_POSITION = 100;
	public static final String DEFAULT_BACKGROUND_COLOR = Color.WHITE_STRING;
	
	//minimum values
	public static final int MINIMUM_WIDTH = 400;
	public static final int MINIMUM_HEIGHT = 200;
	
	//attribute header
	private static final String CLOSE_COMMAND_HEADER = "CloseCommand";
	
	//attributes
	private final FrameContext frameContext;
	private CursorIcon currentCursorIcon = CursorIcon.Arrow;
	private JFrame frame = new JFrame();
	
	//attribute
	@SuppressWarnings("serial")
	private final JPanel panel = new JPanel() {

		//method
		/**
		 * Paints on this panel using the given graphics.
		 * 
		 * @param graphics
		 */
		public void paintComponent(Graphics graphics) {
			
			//Calls method of the base class.
			super.paintComponent(graphics);
			
			//Lets the root rectangle of this frame paint on this panel.
			if (hasRootWidget()) {
				getRefRootWidget().paintUsingRelativePosition(graphics);
			}
		}
	};
	
	//optional attribute
	private Statement closeCommand;
	
	//constructor
	/**
	 * Creates new frame.
	 */
	public Frame() {
		
		//Creates the frame context of this frame.
		frameContext = new FrameContext(this);
		
		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new FrameCloseListener(this));
		frame.addComponentListener(new FrameResizeListener(this));
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setMinimumSize(new Dimension(MINIMUM_WIDTH, MINIMUM_HEIGHT));
		frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		frame.setLocation(DEFAULT_X_POSITION, DEFAULT_Y_POSITION);

		//Add listeners to this frame.
		panel.addKeyListener(new FrameKeyListener(this));
		panel.addMouseListener(new FrameMouseListener(this));
		panel.addMouseMotionListener(new FrameMouseMotionListener(this));
		panel.setFocusable(true);	//This is important that key events are handled.
		panel.requestFocus(); //This is important that key events are handled.
		
		paint();
	}
	
	//method
	/**
	 * @return the attributes of this frame
	 */
	public List<Specification> getAttributes() {
		
		List<Specification> attributes = super.getAttributes();
		
		if (hasCloseCommand()) {
			attributes.addAtEnd(new Specification(CLOSE_COMMAND_HEADER, closeCommand.toString()));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the current height of this frame
	 */
	public final int getHeight() {
		return frame.getHeight();
	}
	
	//method
	/**
	 * @return the frame context of this frame
	 */
	public final FrameContext getRefFrameContext() {
		return frameContext;
	}
	
	//method
	/**
	 * @return height of the pane of this frame
	 */
	public final int getPaneHeight() {
		return frame.getComponent(0).getHeight();
	}
	
	//method
	/**
	 * @return width of the pane of this frame
	 */
	public final int getPaneWidth() {
		return frame.getComponent(0).getWidth();
	}
	
	//method
	/**
	 * @return the width of this frame
	 */
	public final int getWidth() {
		return frame.getWidth();
	}
	
	//method
	/**
	 * @return true if this frame has a close command
	 */
	public final boolean hasCloseCommand() {
		return (closeCommand != null);
	}
	
	//method
	/**
	 * Removes the close command of this frame.
	 */
	public final void removeCloseCommand() {
		closeCommand = null;
	}
	
	//method
	/**
	 * Resets this frame.
	 */
	public void reset() {
		
		//Calls method of the base class.
		super.reset();
		
		removeCloseCommand();
	}
	
	//method
	/**
	 * Resets the configuration of this frame.
	 */
	public final void resetConfiguration() {
		
		//Calls method of the base class.
		super.resetConfiguration();
		
		setBackgroundColor(new BackgroundColor(DEFAULT_BACKGROUND_COLOR));
	}
	
	//method
	/**
	 * Sets the given attribute to this frame.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public void addOrChangeAttribute(Specification attribute) {
		switch (attribute.getHeader()) {
			case CLOSE_COMMAND_HEADER:
				setCloseCommand(attribute.getOneAttributeToString());
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Sets the close command of this main frame.
	 * 
	 * @param closeCommand
	 * @throws Exception if the given close command is not valid
	 */
	public final void setCloseCommand(String closeCommand) {
		this.closeCommand = new Statement(closeCommand);
	}
	
	/*
	public void updateFromInteraction() {
		
		updateFromConfiguration();
		
		paint();
		Point mousePosition = getRefPanel().getMousePosition(); //The mouse position is null if the mouse is outside of this frame.
		if (mousePosition != null) {
			setMousePosition((int)mousePosition.getX(), (int)mousePosition.getY());
		}
		else {
			setMousePosition(0, 0);
		}
		
		noteMouseMove();
		paint();
	}
	*/
	
	//method
	/**
	 * @return panel of this frame
	 */
	protected final JPanel getRefPanel() {
		return panel;
	}
	
	//method
	/**
	 * Notes a close button click.
	 * That means: run the close command of this main frame if this main frame has a close command, exits program otherwise.
	 */
	protected void noteCloseButtonClick() {
		if (hasCloseCommand()) {
			getRefController().run(closeCommand);
		}
		else {
			System.exit(0);
		}
	}
	

	
	//method
	/**
	 * Lets this frame note a resizing.
	 */
	protected final void noteResizing() {
		paint();
	}
	
	//method
	/**
	 * Lets this frame note a typed key.
	 * 
	 * @param keyEvent
	 */
	public final void noteKeyTyping(KeyEvent keyEvent) {
		
		//Calls method of the base class.
		super.noteKeyTyping(keyEvent);
		
		paint();
	}
	
	//method
	/**
	 * Paints this frame.
	 */
	protected void paint() {
		
		frame.setTitle(getTitle());	
		panel.setBackground(getBackgroundColor().getJavaColor());
		frame.setCursor(currentCursorIcon.getJavaCursor());
		
		if (hasRootWidget()) {
			switch (getContentPosition()) {
				case LeftTop:
					
					getRefRootWidget().setRelativePosition(
						0,
						0
					);
					
					break;
				case Left:
					
					getRefRootWidget().setRelativePosition(
						0,
						Calculator.getMax(0, (getPaneHeight() - getRefRootWidget().getHeight()) / 2)
					);
					
					break;					
				case LeftBottom:
					
					getRefRootWidget().setRelativePosition(
						0,
						Calculator.getMax(0, getPaneHeight() - getRefRootWidget().getHeight())
					);
					
					break;
				case Top:
					
					getRefRootWidget().setRelativePosition(
						Calculator.getMax(0, (getPaneWidth() - getRefRootWidget().getWidth()) / 2),
						0
					);
					
					break;
				case Center:
								
					getRefRootWidget().setRelativePosition(
						Calculator.getMax(0, (getPaneWidth() - getRefRootWidget().getWidth()) / 2),
						Calculator.getMax(0, (getPaneHeight() - getRefRootWidget().getHeight()) / 2)
					);
					
					break;
				case Bottom:
					
					getRefRootWidget().setRelativePosition(
						Calculator.getMax(0, (getPaneWidth() - getRefRootWidget().getWidth()) / 2),
						Calculator.getMax(0, getPaneHeight() - getRefRootWidget().getHeight())
					);
					
					break;
				case RightTop:
					
					getRefRootWidget().setRelativePosition(
						Calculator.getMax(0, getPaneWidth() - getRefRootWidget().getWidth()),
						0
					);
					
					break;
				case Right:
				
					getRefRootWidget().setRelativePosition(
						Calculator.getMax(0, getWidth() - getRefRootWidget().getWidth()),
						Calculator.getMax(0, (frame.getComponent(0).getHeight() - getRefRootWidget().getHeight()) / 2)
					);
				
					break;
				case RightBottom:
					
					getRefRootWidget().setRelativePosition(
						Calculator.getMax(0, getWidth() - getRefRootWidget().getWidth()),
						Calculator.getMax(0, getHeight() - getRefRootWidget().getHeight())
					);
					
					break;
			}
		}
		
		frame.repaint();
	}
	
	//method
	/**
	 * Sets the cursor icon of this frame.
	 * 
	 * @param currentCursorIcon
	 */
	public final void showCursorIcon(CursorIcon currentCursorIcon) {
		this.currentCursorIcon = currentCursorIcon;
	}

	@Override
	public int getMouseXPosition() {
		if (getRefPanel().getMousePosition() != null)
		return (int)getRefPanel().getMousePosition().getX();
		return 0;
	}

	@Override
	public int getMouseYPosition() {
		if (getRefPanel().getMousePosition() != null)
		return (int)getRefPanel().getMousePosition().getY();
		return 0;
	}
}
