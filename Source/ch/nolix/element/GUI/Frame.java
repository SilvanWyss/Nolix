//package declaration
package ch.nolix.element.GUI;

//Java imports
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.mathematics.Calculator;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specification.Statement;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 360
 */
public class Frame extends VisibleGUI<Frame> {
	
	//constant
	public static final String TYPE_NAME = "Frame";
	
	//default values
	public static final int DEFAULT_WIDTH = 1200;
	public static final int DEFAULT_HEIGHT = 800;
	
	//minimum values
	public static final int MIN_WIDTH = 400;
	public static final int MIN_HEIGHT = 200;
	
	//default x-position of a frame on the screen
	private static final int DEFAULT_X_POSITION = 200;
	
	//default y-position of a frame on the screen
	private static final int DEFAULT_Y_POSITION = 100;
	
	//attribute header
	private static final String CLOSE_COMMAND_HEADER = "CloseCommand";
	
	//attribute
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
				getRefRootWidget().paintUsingPositionOnContainer(graphics);
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
			
		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new FrameCloseListener(this));
		frame.addComponentListener(new FrameResizeListener(this));
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		frame.setLocation(DEFAULT_X_POSITION, DEFAULT_Y_POSITION);

		//Add listeners to this frame.
			panel.addKeyListener(new FrameKeyListener(this));
			panel.addMouseListener(new FrameMouseListener(this));
			panel.addMouseMotionListener(new FrameMouseMotionListener(this));
			
			//This is important that key events are handled.
			panel.setFocusable(true);
			
			//This is important that key events are handled.
			panel.requestFocus(); 
		
		resetConfiguration();		
		refresh();
	}
	
	//method
	/**
	 * Adds or changes the given attribute to this frame.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final StandardSpecification attribute) {
		
		//Enumerates the header of the given attribute.
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
	 * Closes this frame.
	 */
	public void close() {
		
		//Calls method of the base class.
		super.close();
		
		frame.dispose();
	}
	
	//method
	/**
	 * @return the attributes of this frame.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		//Handles the option that this frame has a close command.
		if (hasCloseCommand()) {
			attributes.addAtEnd(
				new StandardSpecification(CLOSE_COMMAND_HEADER, closeCommand.toString())
			);
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return height of the content of this frame.
	 */
	public int getContentHeight() {
		return frame.getComponent(0).getHeight();
	}
	
	//method
	/**
	 * @return width of the content of this frame.
	 */
	public int getContentWidth() {
		return frame.getComponent(0).getWidth();
	}
	
	//method
	/**
	 * @return the x-position of the cursor on this frame.
	 */
	public int getCursorXPosition() {
		
		if (panel.getMousePosition() == null) {
			return 0;
		}
		
		return (int)panel.getMousePosition().getX();
	}

	//method
	/**
	 * @return the y-position of the cursor on this frame.
	 */
	public int getCursorYPosition() {
		
		if (panel.getMousePosition() == null) {
			return 0;
		}
		return (int)panel.getMousePosition().getY();
	}
	
	//method
	/**
	 * @return the height of this frame.
	 */
	public int getHeight() {
		return frame.getHeight();
	}
	
	//method
	/**
	 * @return the width of this frame.
	 */
	public int getWidth() {
		return frame.getWidth();
	}
	
	//method
	/**
	 * @return true if this frame has a close command.
	 */
	public boolean hasCloseCommand() {
		return (closeCommand != null);
	}
	
	//method
	/**
	 * Lets this frame not a close button click.
	 * That means:
	 * -Run the close command of this main frame if it has a close command.
	 * -Exits the program otherwise.
	 */
	public void noteCloseButtonClick() {
		
		//Handles the case that this frame has no close command.
		if (!hasCloseCommand()) {
			close();
		}
		
		//Handles the case that this frame has a close command.
		else {
			getRefController().run(closeCommand);			
		}
	}
	
	//method
	/**
	 * Refreshes this frame.
	 */
	public void refresh() {
		
		frame.setTitle(getTitle());	
		panel.setBackground(getBackgroundColor().getJavaColor());
		frame.setCursor(getActiveCursorIcon().getJavaCursor());
		
		//Handles the option that this frame has a root widget.
		if (hasRootWidget()) {
			
			//Enumerates the content position of this frame.
			switch (getContentPosition()) {
				case LeftTop:
					
					getRefRootWidget().setPositionOnContainer(
						0,
						0
					);
					
					break;
				case Left:
					
					getRefRootWidget().setPositionOnContainer(
						0,
						Calculator.getMax(0, (getContentHeight() - getRefRootWidget().getHeight()) / 2)
					);
					
					break;					
				case LeftBottom:
					
					getRefRootWidget().setPositionOnContainer(
						0,
						Calculator.getMax(0, getContentHeight() - getRefRootWidget().getHeight())
					);
					
					break;
				case Top:
					
					getRefRootWidget().setPositionOnContainer(
						Calculator.getMax(0, (getContentWidth() - getRefRootWidget().getWidth()) / 2),
						0
					);
					
					break;
				case Center:
								
					getRefRootWidget().setPositionOnContainer(
						Calculator.getMax(0, (getContentWidth() - getRefRootWidget().getWidth()) / 2),
						Calculator.getMax(0, (getContentHeight() - getRefRootWidget().getHeight()) / 2)
					);
					
					break;
				case Bottom:
					
					getRefRootWidget().setPositionOnContainer(
						Calculator.getMax(0, (getContentWidth() - getRefRootWidget().getWidth()) / 2),
						Calculator.getMax(0, getContentHeight() - getRefRootWidget().getHeight())
					);
					
					break;
				case RightTop:
					
					getRefRootWidget().setPositionOnContainer(
						Calculator.getMax(0, getContentWidth() - getRefRootWidget().getWidth()),
						0
					);
					
					break;
				case Right:
				
					getRefRootWidget().setPositionOnContainer(
						Calculator.getMax(0, getWidth() - getRefRootWidget().getWidth()),
						Calculator.getMax(0, (frame.getComponent(0).getHeight() - getRefRootWidget().getHeight()) / 2)
					);
				
					break;
				case RightBottom:
					
					getRefRootWidget().setPositionOnContainer(
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
	 * Removes the close command of this frame.
	 */
	public void removeCloseCommand() {
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
	 * Sets the close command of this frame.
	 * 
	 * @param closeCommand
	 * @throws InvalidArgumentException if the given close command is not valid.
	 */
	public Frame setCloseCommand(final String closeCommand) {
		
		this.closeCommand = new Statement(closeCommand);
		
		return this;
	}
	
	//method
	/**
	 * Lets this frame note a resizing.
	 */
	protected final void noteResizing() {
		refresh();
	}
}
