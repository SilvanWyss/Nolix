//package declaration
package ch.nolix.element.GUI;

//Java imports
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import ch.nolix.element.GUI_API.Widget;
//own import
import ch.nolix.element.painter.SwingPainter;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 220
 */
public final class Frame extends GUI<Frame> {
	
	//constant
	public static final String TYPE_NAME = "Frame";
	
	//default values
	public static final int DEFAULT_WIDTH = 1200;
	public static final int DEFAULT_HEIGHT = 800;
			
	//default values
	private static final int DEFAULT_X_POSITION = 200;
	private static final int DEFAULT_Y_POSITION = 100;
	
	//limit values
	public static final int MIN_WIDTH = 200;
	public static final int MIN_HEIGHT = 100;
	
	//attribute
	private final JFrame frame = new JFrame();
	
	//attribute
	@SuppressWarnings("serial")
	private final JPanel panel = new JPanel() {

		//method
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void paintComponent(final Graphics graphics) {
			
			//Paints the title of the current frame.
			frame.setTitle(getTitle());
			
			//Calls method of the base class.
			super.paintComponent(graphics);
			
			//Creates swing painter.
			Frame.this.paint(new SwingPainter(graphics));
		}
	};
	
	//constructor
	/**
	 * Creates a new {@link Frame}.
	 */
	public Frame() {
			
		reset();
		
		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new FrameCloseListener(this));
		frame.addComponentListener(new FrameResizeListener(this));
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		frame.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		frame.setLocation(DEFAULT_X_POSITION, DEFAULT_Y_POSITION);
		
		//Add listeners to the current Frame.
		panel.addKeyListener(new FrameKeyListener(this));
		panel.addMouseListener(new FrameMouseListener(this));
		panel.addMouseMotionListener(new FrameMouseMotionListener(this));
		panel.addMouseWheelListener(new FrameMouseWheelListener(this));
		
		//This is important that key events are handled.
		panel.setFocusable(true);
		panel.requestFocus();
						
		refresh();
	}
	
	//constructor
	/**
	 * Creates a new {@link Frame} with the given title.
	 * 
	 * @param title
	 * @throws NullArgumentException if the given title is null.
	 * @throws InvalidArgumentException if the given title is blank.
	 */
	public Frame(final String title) {
		
		//Calls other constructor.
		this();
		
		setTitle(title);
	}
	
	//constructor
	/**
	 * Creates a new {@link Frame} with the given title and rootWidget.
	 * 
	 * @param title
	 * @param rootWidget
	 * @throws NullArgumentException if the given title is null.
	 * @throws InvalidArgumentException if the given title is blank.
	 * @throws NullArgumentException if the given rootWidget is null.
	 */
	public Frame(final String title, final Widget<?, ?> rootWidget) {
		
		//Calls other constructor.
		this(title);
		
		addLayerOnTop(rootWidget);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCursorXPosition() {
		
		if (panel.getMousePosition() == null) {
			return 0;
		}
		
		return (int)panel.getMousePosition().getX();
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCursorYPosition() {
		
		if (panel.getMousePosition() == null) {
			return 0;
		}
		
		return (int)panel.getMousePosition().getY();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getHeight() {
		return frame.getHeight();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWidth() {
		return frame.getWidth();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getViewAreaHeight() {
		return frame.getComponent(0).getHeight();
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getViewAreaWidth() {
		return frame.getComponent(0).getWidth();
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isRootGUI() {
		return true;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paint() {
		frame.setCursor(getCursorIcon().getJavaCursor());
		frame.repaint();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteClosing() {
		frame.dispose();
	}
}
