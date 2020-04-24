//package declaration
package ch.nolix.element.frameVisualizer;

//Java imports
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.GUI.IVisualizer;
import ch.nolix.element.painter.SwingPainter;

//class
/**
 * @author Silvan Wyss
 * @month 2019-08
 * @lines 180
 */
public final class FrameVisualizer implements IVisualizer {
	
	//constants
	public static final int INITIAL_FRAME_X_POSITION = 200;
	public static final int INITIAL_FRAME_Y_POSITION = 100;
		
	//constants
	public static final int INITIAL_FRAME_WIDTH = 1200;
	public static final int INITIAL_FRAME_HEIGHT = 800;
		
	//constants
	public static final int MIN_WIDTH = 200;
	public static final int MIN_HEIGHT = 100;
	
	//attributes
	private GUI<?> parentGUI;
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
			frame.setTitle(parentGUI.getTitle());
			
			//Calls method of the base class.
			super.paintComponent(graphics);
			
			//Creates swing painter.
			FrameVisualizer.this.parentGUI.paint(new SwingPainter(graphics));
		}
	};

	//constructor
	public void initialize(GUI<?> parentGUI) {
		
		Validator.assertThat(parentGUI).thatIsNamed("parent GUI").isNotNull();
		
		if (this.parentGUI != null) {
			throw new InvalidArgumentException(this);
		}
		
		this.parentGUI = parentGUI;
		
		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new FrameVisualizerCloseListener(parentGUI));
		frame.addComponentListener(new FrameVisualizerResizeListener(parentGUI));
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		frame.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		frame.setSize(INITIAL_FRAME_WIDTH, INITIAL_FRAME_HEIGHT);
		frame.setLocation(INITIAL_FRAME_X_POSITION, INITIAL_FRAME_Y_POSITION);
		
		//Add listeners to the current Frame.
		panel.addKeyListener(new FrameVisualizerKeyListener(parentGUI));
		panel.addMouseListener(new FrameVisualizerMouseListener(parentGUI));
		panel.addMouseMotionListener(new FrameVisualizerMouseMotionListener(parentGUI));
		panel.addMouseWheelListener(new FrameVisualizerMouseWheelListener(parentGUI));
		
		//This is important that key events are handled.
		panel.setFocusable(true);
		panel.requestFocus();
						
		parentGUI.refresh();
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
	public int getViewAreaCursorXPosition() {
		
		final var mousePosition = panel.getMousePosition();
		
		if (mousePosition == null) {
			return 0;
		}
		
		return (int)mousePosition.getX();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getViewAreaCursorYPosition() {
		
		final var mousePosition = panel.getMousePosition();
		
		if (mousePosition == null) {
			return 0;
		}
		
		return (int)mousePosition.getY();
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
	public int getWidth() {
		return frame.getWidth();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void repaint() {
		frame.setCursor(parentGUI.getCursorIcon().getJavaCursor());
		frame.repaint();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteClose() {
		frame.dispose();
	}
}
