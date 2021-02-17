//package declaration
package ch.nolix.common.math;

//Java import
import java.util.Arrays;

//own imports
import ch.nolix.common.commontypehelper.DoubleHelper;
import ch.nolix.common.constant.PluralLowerCaseCatalogue;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.common.invalidargumentexception.ArgumentIsZeroException;
import ch.nolix.common.invalidargumentexception.NegativeArgumentException;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link Polynom} is not mutable.
 * 
 * A {@link Polynom} stores its coefficients in an array from the highest to the lowest coefficient.
 * -degree:	n
 * -array:	[a_n,... ,a_0]
 * 
 * @author Silvan Wyss
 * @date 2016-03-01
 * @lines 400
 */
public final class Polynom {
	
	//constant
	public static final String DEFAULT_PARAMTER_SYMBOL = "x";
	
	//static method
	public static Polynom withCoefficients(final double... coefficients) {
		return new Polynom(Arrays.copyOf(coefficients, coefficients.length));
	}
	
	//optional attribute
	private Polynom derivedPolynom;
	
	//multi-attribute
	private final double[] coefficients;
	
	//constructor
	/**
	 * Creates a new {@link Polynom} with the given coefficients.
	 * 
	 * @param coefficients
	 * @throws ArgumentIsNullException if the given coefficients is null.
	 * @throws ArgumentIsZeroException if the highest of the given coefficients is 0.0.
	 */
	private Polynom(final double[] coefficients) {
		
		//Asserts that the given coefficients is not null.
		Validator.assertThat(coefficients).thatIsNamed(PluralLowerCaseCatalogue.COEFFICIENTS).isNotNull();
		
		//Handles the case that the given coefficients is not empty.
		if (coefficients.length > 0) {
			
			//Asserts that the given highest coefficient is not 0.0.
			Validator.assertThat(coefficients[0]).thatIsNamed("highest coefficient").isNotZero();
		}
		
		//Sets the coefficients of the current Polynom.
		this.coefficients = coefficients;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object object) {
		return (object instanceof Polynom && equals((Polynom)object));
	}
	
	//method
	/**
	 * @param degree
	 * @return the coefficient for the given degree from the current {@link Polynom}.
	 * @throws ArgumentIsOutOfRangeException
	 * if the given degree is not between 0 and the degree of the current {@link Polynom}.
	 */
	public double getCoefficientForDegree(final int degree) {
		
		//Asserts that the given degree is between 0 and the degree of the current Polynom.
		Validator.assertThat(degree).thatIsNamed(LowerCaseCatalogue.DEGREE).isBetween(0, getDegree());
		
		return coefficients[coefficients.length - degree - 1];
	}
	
	//method
	/**
	 * @return a new {@link Polynom} that is derived from the current {@link Polynom}.
	 */
	public Polynom getDerived() {
		return getDerived(1);
	}
	
	//method
	/**
	 * @param deriveCount
	 * @return a new {@link Polynom}
	 * that is derived from the current {@link Polynom} as many times as the given deriveCount says.
	 * @throws NegativeArgumentException if the given deriveCount is negative.
	 */
	public Polynom getDerived(final int deriveCount) {
		
		if (deriveCount == 1) {
			
			if (derivedPolynom == null) {
				derivedPolynom = calculateDerived(1);
			}
			
			return derivedPolynom;
		}
		
		return calculateDerived(deriveCount);
	}
	
	//method
	/**
	 * @return the degree of the current {@link Polynom}.
	 */
	public int getDegree() {
		return (coefficients.length - 1);
	}
	
	//method
	/**
	 * @return a new {@link Polynom} that is the integration of the current {@link Polynom}.
	 */
	public Polynom getIntegrated() {
		return getIntegrated(1);
	}
	
	//method
	/**
	 * @param integrationCount
	 * @return a new {@link Polynom}
	 * that is the integration from the current {@link Polynom} as many times as the given integrationCount says.
	 * @throws NegativeArgumentException if the given integrationCount is negative.
	 */
	public Polynom getIntegrated(final int integrationCount) {
		
		Validator.assertThat(integrationCount).thatIsNamed("integration count").isNotNegative();
		
		final var degree = getDegree();
		final var integratedDegree = degree + integrationCount;
		
		final var integratedCoefficients = new double[integratedDegree + 1];
		for (var i = 0; i < integratedDegree; i++) {
			if (coefficients[i] == 0.0) {
				integratedCoefficients[i] = 0.0;
			} else {
				
				var integratedCoefficient = coefficients[i];
				
				for (var j = degree - i + 1; j < integratedDegree - i + 1; j++) {
					integratedCoefficient /= j;
				}
				
				integratedCoefficients[i] = integratedCoefficient;
			}
		}
		
		return new Polynom(integratedCoefficients);
	}
	
	//method
	/**
	 * @param x
	 * @return the slope of the current {@link Polynom} at the given x.
	 */
	public double getSlopeAt(final double x) {
		return getDerived().getValueAt(x);
	}
	
	//method
	/**
	 * This method uses the Horner scheme to calculate the value.
	 * 
	 * @param x
	 * @return the value of the current {@link Polynom} at the given x.
	 */
	public double getValueAt(final double x) {
		
		if (isZeroPolynom()) {
			return 0.0;
		}
		
		return getValueAtWhenIsNotZeroPolynom(x);
	}
	
	//method
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	//method
	/**
	 * @param polynom
	 * @return true if the current {@link Polynom} has the same degree as the given polynom.
	 */
	public boolean hasSameDegreeAs(final Polynom polynom) {
		return (polynom != null && getDegree() == polynom.getDegree());
	}
	
	//method
	/**
	 * A {@link Polynom} is a zero {@link Polynom} if all its coefficients are 0.0.
	 * 
	 * @return true if the current {@link Polynom} is a zero {@link Polynom}.
	 */
	public boolean isZeroPolynom() {
		return (coefficients.length == 0);
	}
	
	//method
	/**
	 * @return a new array with the coefficients of the current {@link Polynom}.
	 */
	public double[] toArray() {
		return coefficients.clone();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return toString(DEFAULT_PARAMTER_SYMBOL);
	}
	
	//method
	/**
	 * @param parameterSymbol
	 * @return a {@link String} representation of the current {@link Polynom} with the given parameterSymbol
	 * @throws ArgumentIsNullException if the given parameterSymbol is null.
	 */
	public String toString(final String parameterSymbol) {
		
		//Handles the case that the current Polynom is a zero Polynom.
		if  (isZeroPolynom()) {
			return toStringWhenIsZeroPolynom(parameterSymbol);
		}
		
		//Handles the case that the current Polynom is not a zero Polynom.
		return toStringWhenIsNotZeroPolynom(parameterSymbol);
	}
	
	//method
	/**
	 * @return a new {@link Vector} with the coefficients of the current {@link Polynom}.
	 */
	public Vector toVector() {
		return new Vector(coefficients);
	}
	
	//method
	private Polynom calculateDerived(final int deriveCount) {
		
		//Asserts that the given derviceCount is not negative.
		Validator.assertThat(deriveCount).thatIsNamed("derive count").isNotNegative();
		
		final var degree = getDegree();
		final var derivedDegree = Calculator.getMax(0, degree - deriveCount);
		
		final var derivedCoefficients = new double[derivedDegree];
		for (var i = 0; i < derivedDegree; i++) {
			if (coefficients[i] == 0.0) {
				derivedCoefficients[i] = 0.0;
			} else {
				
				var derivedCoefficient = coefficients[i];
				
				for (var j = degree - i; j > derivedDegree - i; j--) {
					derivedCoefficient *= j;
				}
				
				derivedCoefficients[i] = derivedCoefficient;
			}
		}
		
		return new Polynom(derivedCoefficients);
	}
	
	//method
	private boolean equals(final Polynom polynom) {
		
		if (!hasSameDegreeAs(polynom)) {
			return false;
		}
		
		for (var i = 0; i < coefficients.length; i++) {
			if (coefficients[i] != polynom.coefficients[i]) {
				return false;
			}
		}
		
		return true;
	}
	
	//method
	private double getValueAtWhenIsNotZeroPolynom(final double x) {
		
		var value = coefficients[0];
		
		var base = 1.0;
		for (int i = 1; i < coefficients.length; i++) {
			base *= x;
			value += coefficients[i] * base;
		}
		
		return value;
	}
	
	//method
	private String toStringWhenIsZeroPolynom(final String parameterSymbol) {
		
		Validator.assertThat(parameterSymbol).thatIsNamed("parameter symbol").isNotBlank();
		
		return (parameterSymbol + "->0.0");
	}
	
	//method
	private String toStringWhenIsNotZeroPolynom(final String parameterSymbol) {
		
		Validator.assertThat(parameterSymbol).thatIsNamed("parameter symbol").isNotBlank();
		
		final var stringBuilder = new StringBuilder();
		
		stringBuilder.append(parameterSymbol + "->");
		appendHigherCoefficientsTo(stringBuilder, parameterSymbol);
		appendLinearCoefficientTo(stringBuilder, parameterSymbol);
		appendConstantTo(stringBuilder);
						
		return stringBuilder.toString();
	}
	
	//method
	private void appendConstantTo(final StringBuilder stringBuilder) {
		if (coefficients.length > 0) {
			
			final var constant = coefficients[coefficients.length - 1];
			
			if (constant != 0.0) {
				
				if (coefficients.length > 1 && constant > 0.0) {
					stringBuilder.append("+");
				}
			
				stringBuilder.append(DoubleHelper.toString(constant));
			}
		}
	}
	
	//method
	private void appendHigherCoefficientsTo(final StringBuilder stringBuilder, final String parameterSymbol) {
		
		final var degree = getDegree();
		
		for (var i = 0; i < degree - 1; i++) {
						
			final var coefficient = coefficients[i];
			
			if (coefficient != 0.0) {
				
				if (i > 0 && coefficient > 0.0) {
					stringBuilder.append('+');
				}
				
				if (coefficient != 1.0) {
					stringBuilder.append(DoubleHelper.toString(coefficient));
				}
				
				final var coefficientDegree = degree - i;
				stringBuilder.append(parameterSymbol + "^" + coefficientDegree);
			}
		}
	}
	
	//method
	private void appendLinearCoefficientTo(final StringBuilder stringBuilder, final String parameterSymbol) {
		if (coefficients.length > 1) {
			
			final var linearCoefficient = coefficients[coefficients.length - 2];
			
			if (linearCoefficient != 0.0) {
				
				if (coefficients.length > 2 && linearCoefficient > 0.0) {
					stringBuilder.append("+");
				}
				
				stringBuilder.append(DoubleHelper.toString(linearCoefficient) + parameterSymbol);
			}
		}
	}
}
