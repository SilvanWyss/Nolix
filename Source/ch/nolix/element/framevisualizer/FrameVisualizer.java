//package declaration
package ch.nolix.element.framevisualizer;

//Java imports
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.gui.base.GUI;
import ch.nolix.element.gui.base.IVisualizer;
import ch.nolix.element.gui.base.SwingPainter;

//class
/**
 * @author Silvan Wyss
 * @date 2019-08-01
 * @lines 200
 */
public final class FrameVisualizer implements IVisualizer {
	
	//constants
	public static final int INITIAL_FRAME_X_POSITION = 100;
	public static final int INITIAL_FRAME_Y_POSITION = 50;
	
	//constants
	public static final int INITIAL_FRAME_WIDTH = 1200;
	public static final int INITIAL_FRAME_HEIGHT = 600;
		
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
			FrameVisualizer.this.parentGUI.paint(new SwingPainter(parentGUI.getRefImageCache(), graphics));
		}
	};
	
	//constructor
	/**
	 * Initializes the current {@link FrameVisualizer} with the given parentGUI.
	 * 
	 * @param parentGUI
	 * @throws ArgumentIsNullException if the given parentGUI is null.
	 * @throws InvalidArgumentException if the current {@link FrameVisualizer} has already been initialized.
	 */
	public void initialize(GUI<?> parentGUI) {
		
		Validator.assertThat(parentGUI).thatIsNamed("parent GUI").isNotNull();
		assertIsNotInitialized();
				
		this.parentGUI = parentGUI;
		
		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new FrameVisualizerCloseListener(parentGUI));
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		frame.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		frame.setSize(INITIAL_FRAME_WIDTH, INITIAL_FRAME_HEIGHT);
		frame.setLocation(INITIAL_FRAME_X_POSITION, INITIAL_FRAME_Y_POSITION);
		
		//Add listeners to the current Frame.
		panel.addComponentListener(new FrameVisualizerResizeListener(parentGUI));
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
	public void noteClose() {
		frame.dispose();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void repaint() {
		frame.setCursor(parentGUI.getCursorIcon().toCursor());
		frame.repaint();
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if the current {@link FrameVisualizer} has already been initialized.
	 */
	private void assertIsNotInitialized() {
		if (isInitialized()) {
			throw new InvalidArgumentException(this);
		}
	}
	
	//method
	/**
	 * @return true if the current {@link FrameVisualizer} is initialized.
	 */
	private boolean isInitialized() {
		return (parentGUI != null);
	}
}
