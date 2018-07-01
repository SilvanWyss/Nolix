//package declaration
package ch.nolix.element.GUI;

//Java imports
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

//own imports
import ch.nolix.core.mathematics.Calculator;
import ch.nolix.core.util.Timer;
import ch.nolix.element.painter.SwingPainter;
import ch.nolix.primitive.logger.Logger;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 290
 */
public final class Frame extends VisibleGUI<Frame> {
	
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
			
			final var timer = new Timer();
			timer.start();
			
			frame.setTitle(getTitle());	
			
			//Calls method of the base class.
			super.paintComponent(graphics);
			
			//Creates swing painter.
			final var swingPainter = new SwingPainter(graphics);
			
			//Paints the background of this frame.
				if (hasBackgroundColor()) {
					swingPainter.setColor(getBackgroundColor());
					swingPainter.paintFilledRectangle(getWidth(), getHeight());
				}
				
				if (hasBackgroundColorGradient()) {
					swingPainter.setColorGradient(getBackgroundColorGradient());
					swingPainter.paintFilledRectangle(getWidth(), getHeight());
				}
			
			//Lets the root rectangle of this frame paint on this panel.
			if (hasRootWidget()) {
				getRefRootWidget().paintUsingPositionOnParent(swingPainter);
			}
			
			Logger.logInfo("frame paint duration in milliseconds", timer.getRunMilliseconds());
		}
	};
	
	//constructor
	/**
	 * Creates a new frame.
	 */
	public Frame() {
			
		reset();
		approveProperties();
		
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
			panel.addMouseWheelListener(new FrameMouseWheelListener(this));
			
			//This is important that key events are handled.
			panel.setFocusable(true);
			
			//This is important that key events are handled.
			panel.requestFocus(); 
						
		refresh();
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
	 * Refreshes this frame.
	 */
	public void refresh() {
		
		final var timer = new Timer();
		timer.start();
		
		//panel.setBackground(getBackgroundColor().createSwingColor());
		frame.setCursor(getActiveCursorIcon().getJavaCursor());
		
		//Handles the case that this frame has a root widget.
		if (hasRootWidget()) {
			
			//Enumerates the content position of this frame.
			switch (getContentPosition()) {
				case LeftTop:
					
					getRefRootWidget().setPositionOnParent(
						0,
						0
					);
					
					break;
				case Left:
					
					getRefRootWidget().setPositionOnParent(
						0,
						Calculator.getMax(0, (getContentHeight() - getRefRootWidget().getHeight()) / 2)
					);
					
					break;					
				case LeftBottom:
					
					getRefRootWidget().setPositionOnParent(
						0,
						Calculator.getMax(0, getContentHeight() - getRefRootWidget().getHeight())
					);
					
					break;
				case Top:
					
					getRefRootWidget().setPositionOnParent(
						Calculator.getMax(0, (getContentWidth() - getRefRootWidget().getWidth()) / 2),
						0
					);
					
					break;
				case Center:
								
					getRefRootWidget().setPositionOnParent(
						Calculator.getMax(0, (getContentWidth() - getRefRootWidget().getWidth()) / 2),
						Calculator.getMax(0, (getContentHeight() - getRefRootWidget().getHeight()) / 2)
					);
					
					break;
				case Bottom:
					
					getRefRootWidget().setPositionOnParent(
						Calculator.getMax(0, (getContentWidth() - getRefRootWidget().getWidth()) / 2),
						Calculator.getMax(0, getContentHeight() - getRefRootWidget().getHeight())
					);
					
					break;
				case RightTop:
					
					getRefRootWidget().setPositionOnParent(
						Calculator.getMax(0, getContentWidth() - getRefRootWidget().getWidth()),
						0
					);
					
					break;
				case Right:
				
					getRefRootWidget().setPositionOnParent(
						Calculator.getMax(0, getWidth() - getRefRootWidget().getWidth()),
						Calculator.getMax(0, (frame.getComponent(0).getHeight() - getRefRootWidget().getHeight()) / 2)
					);
				
					break;
				case RightBottom:
					
					getRefRootWidget().setPositionOnParent(
						Calculator.getMax(0, getWidth() - getRefRootWidget().getWidth()),
						Calculator.getMax(0, getHeight() - getRefRootWidget().getHeight())
					);
					
					break;
			}
		}
				
		Logger.logInfo("frame set position duration in milliseconds", timer.getRunMilliseconds());
		
		frame.repaint();
	}
	
	//method
	/**
	 * Lets this frame note a resizing.
	 */
	protected final void noteResizing() {
		refresh();
	}
}
