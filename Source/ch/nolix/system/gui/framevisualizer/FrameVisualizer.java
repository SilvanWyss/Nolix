//package declaration
package ch.nolix.system.gui.framevisualizer;

//Java imports
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.gui.base.GUI;
import ch.nolix.system.gui.base.IVisualizer;
import ch.nolix.system.gui.base.SwingPainter;
import ch.nolix.system.gui.image.Image;

//class
/**
 * @author Silvan Wyss
 * @date 2019-08-01
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
		
		//optional attribute
		private transient Image appliedIcon;
		
		//method
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void paintComponent(final Graphics graphics) {
			
			//Paints the title of the frame.
			frame.setTitle(parentGUI.getTitle());
			
			//Updates the icon of the Frame if needed.
			updateIconIfNeededTo(frame);
			
			//Calls method of the base class.
			super.paintComponent(graphics);
			
			//Creates painter.
			final var painter =
			SwingPainter.withImageCacheAndGraphics(parentGUI.getRefImageCache(), (Graphics2D)graphics);
			
			//Paints the view area of the frame
			FrameVisualizer.this.parentGUI.paint(painter);
		}
		
		//method
		private void updateIconIfNeededTo(final JFrame frame) {
			if (appliedIcon != parentGUI.getIcon()) {
				appliedIcon = parentGUI.getIcon();
				frame.setIconImage(parentGUI.getIcon().toBufferedImage());
			}
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
		
		GlobalValidator.assertThat(parentGUI).thatIsNamed("parent GUI").isNotNull();
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
		
		/* 
		 * The initial view area size must be set to the GUI when the GUI is initialized.
		 * Normally, the view area size of a GUI is set on a resize event only.
		 * On the creation of a GUI, there does not occur a resize event, what is correct.
		 */
		parentGUI.noteResize(getViewAreaWidth(), getViewAreaHeight());
		
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
