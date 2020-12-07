//package declaration
package ch.nolix.element.discretegeometry;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.base.Element;

//class
/**
 * A {@link Discrete2DPoint} is a point with 2 discrete coordinates.
 * A {@link Discrete2DPoint} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2019-05
 * @lines 70
 */
public final class Discrete2DPoint extends Element<Discrete2DPoint> {
	
	//static method
	/**
	 * Creates a new {@link Discrete2DPoint} from the given specification.
	 * 
	 * @param specification
	 * @return a new {@link Discrete2DPoint} from the given specification.
	 */
	public static Discrete2DPoint fromSpecification(final BaseNode specification) {
		return
		new Discrete2DPoint(specification.getRefAttributeAt(1).toInt(), specification.getRefAttributeAt(2).toInt());
	}
	
	//attributes
	private final int x;
	private final int y;
	
	//method
	/**
	 * Creates a new {@link Discrete2DPoint} with the given x and y.
	 * 
	 * @param x
	 * @param y
	 */
	public Discrete2DPoint(final int x, final int y) {
		this.x = x;
		this.y = y;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public LinkedList<Node> getAttributes() {
		return LinkedList.withElements(new Node(x), new Node(y));
	}
	
	//method
	/**
	 * @return the x-coordinate of the current {@link Discrete2DPoint}.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return the y-coordinate of the current {@link Discrete2DPoint}.
	 */
	//method
	public int getY() {
		return y;
	}
}
