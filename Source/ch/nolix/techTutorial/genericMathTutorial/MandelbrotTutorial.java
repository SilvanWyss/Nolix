package ch.nolix.techTutorial.genericMathTutorial;

import java.math.BigDecimal;

import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.ImageWidget;
import ch.nolix.element.color.Color;
import ch.nolix.tech.genericMath.ClosedInterval;
import ch.nolix.tech.genericMath.ComplexNumber;
import ch.nolix.tech.genericMath.Fractal;

public class MandelbrotTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		final var maxIterationCount = 200;
		
		new Frame(
			"Fractal Painter",
			new ImageWidget(
				new Fractal(
					new ClosedInterval(new BigDecimal(-1.5), new BigDecimal(-1.45)),
					new ClosedInterval(new BigDecimal(-0.025), new BigDecimal(0.025)),
					600,
					1,
					new ComplexNumber(0.0, 0.0),
					(z, c) -> z.getSquare().getSum(c),
					new BigDecimal(2.5),
					maxIterationCount,
					i -> i == maxIterationCount ? Color.BLACK : new Color(i, i, (i * i) % 255),
					10
				)
				.toImage()
			)
		);
	}
}
