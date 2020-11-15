//package declaration
package ch.nolix.element.input;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.base.Element;
import ch.nolix.element.base.Property;

//class
public final class KeyInput extends Element<KeyInput> implements IInput<KeyInput> {
	
	//constant
	private static final String INPUT_TYPE_HEADER = "InputType";
	
	//static method
	public static KeyInput[]  fromCharacter(final char character) {
		switch (character) {
			case 'a':
				return new KeyInput[] {new KeyInput(Key.A, KeyInputType.Typing)};
			case 'b':
				return new KeyInput[] {new KeyInput(Key.B, KeyInputType.Typing)};
			case 'c':
				return new KeyInput[] {new KeyInput(Key.C, KeyInputType.Typing)};
			case 'd':
				return new KeyInput[] {new KeyInput(Key.D, KeyInputType.Typing)};
			case 'e':
				return new KeyInput[] {new KeyInput(Key.E, KeyInputType.Typing)};
			case 'f':
				return new KeyInput[] {new KeyInput(Key.F, KeyInputType.Typing)};
			case 'g':
				return new KeyInput[] {new KeyInput(Key.G, KeyInputType.Typing)};
			case 'h':
				return new KeyInput[] {new KeyInput(Key.H, KeyInputType.Typing)};
			case 'i':
				return new KeyInput[] {new KeyInput(Key.I, KeyInputType.Typing)};
			case 'j':
				return new KeyInput[] {new KeyInput(Key.J, KeyInputType.Typing)};
			case 'k':
				return new KeyInput[] {new KeyInput(Key.K, KeyInputType.Typing)};
			case 'l':
				return new KeyInput[] {new KeyInput(Key.L, KeyInputType.Typing)};
			case 'm':
				return new KeyInput[] {new KeyInput(Key.M, KeyInputType.Typing)};
			case 'n':
				return new KeyInput[] {new KeyInput(Key.N, KeyInputType.Typing)};
			case 'o':
				return new KeyInput[] {new KeyInput(Key.O, KeyInputType.Typing)};
			case 'p':
				return new KeyInput[] {new KeyInput(Key.P, KeyInputType.Typing)};
			case 'q':
				return new KeyInput[] {new KeyInput(Key.Q, KeyInputType.Typing)};
			case 'r':
				return new KeyInput[] {new KeyInput(Key.R, KeyInputType.Typing)};
			case 's':
				return new KeyInput[] {new KeyInput(Key.S, KeyInputType.Typing)};
			case 't':
				return new KeyInput[] {new KeyInput(Key.T, KeyInputType.Typing)};
			case 'u':
				return new KeyInput[] {new KeyInput(Key.U, KeyInputType.Typing)};
			case 'v':
				return new KeyInput[] {new KeyInput(Key.V, KeyInputType.Typing)};
			case 'w':
				return new KeyInput[] {new KeyInput(Key.W, KeyInputType.Typing)};
			case 'x':
				return new KeyInput[] {new KeyInput(Key.X, KeyInputType.Typing)};
			case 'y':
				return new KeyInput[] {new KeyInput(Key.Y, KeyInputType.Typing)};
			case 'z':
				return new KeyInput[] {new KeyInput(Key.Z, KeyInputType.Typing)};
			case 'A':
				return createTypingBetweenShiftPressAndRelease(Key.A);
			case 'B':
				return createTypingBetweenShiftPressAndRelease(Key.B);
			case 'C':
				return createTypingBetweenShiftPressAndRelease(Key.C);
			case 'D':
				return createTypingBetweenShiftPressAndRelease(Key.D);
			case 'E':
				return createTypingBetweenShiftPressAndRelease(Key.E);
			case 'F':
				return createTypingBetweenShiftPressAndRelease(Key.F);
			case 'G':
				return createTypingBetweenShiftPressAndRelease(Key.G);
			case 'H':
				return createTypingBetweenShiftPressAndRelease(Key.H);
			case 'I':
				return createTypingBetweenShiftPressAndRelease(Key.I);
			case 'J':
				return createTypingBetweenShiftPressAndRelease(Key.J);
			case 'K':
				return createTypingBetweenShiftPressAndRelease(Key.K);
			case 'L':
				return createTypingBetweenShiftPressAndRelease(Key.L);
			case 'M':
				return createTypingBetweenShiftPressAndRelease(Key.M);
			case 'N':
				return createTypingBetweenShiftPressAndRelease(Key.N);
			case 'O':
				return createTypingBetweenShiftPressAndRelease(Key.O);
			case 'P':
				return createTypingBetweenShiftPressAndRelease(Key.P);
			case 'Q':
				return createTypingBetweenShiftPressAndRelease(Key.Q);
			case 'R':
				return createTypingBetweenShiftPressAndRelease(Key.R);
			case 'S':
				return createTypingBetweenShiftPressAndRelease(Key.S);
			case 'T':
				return createTypingBetweenShiftPressAndRelease(Key.T);
			case 'U':
				return createTypingBetweenShiftPressAndRelease(Key.U);
			case 'V':
				return createTypingBetweenShiftPressAndRelease(Key.V);
			case 'W':
				return createTypingBetweenShiftPressAndRelease(Key.W);
			case 'X':
				return createTypingBetweenShiftPressAndRelease(Key.X);
			case 'Y':
				return createTypingBetweenShiftPressAndRelease(Key.Y);
			case 'Z':
				return createTypingBetweenShiftPressAndRelease(Key.Z);
			case '0':
				return new KeyInput[] {new KeyInput(Key.NUMBER_0, KeyInputType.Typing)};
			case '1':
				return new KeyInput[] {new KeyInput(Key.NUMBER_1, KeyInputType.Typing)};
			case '2':
				return new KeyInput[] {new KeyInput(Key.NUMBER_1, KeyInputType.Typing)};
			case '3':
				return new KeyInput[] {new KeyInput(Key.NUMBER_2, KeyInputType.Typing)};
			case '4':
				return new KeyInput[] {new KeyInput(Key.NUMBER_4, KeyInputType.Typing)};
			case '5':
				return new KeyInput[] {new KeyInput(Key.NUMBER_5, KeyInputType.Typing)};
			case '6':
				return new KeyInput[] {new KeyInput(Key.NUMBER_6, KeyInputType.Typing)};
			case '7':
				return new KeyInput[] {new KeyInput(Key.NUMBER_7, KeyInputType.Typing)};
			case '8':
				return new KeyInput[] {new KeyInput(Key.NUMBER_8, KeyInputType.Typing)};
			case '9':
				return new KeyInput[] {new KeyInput(Key.NUMBER_9, KeyInputType.Typing)};
			case ',':
				return new KeyInput[] {new KeyInput(Key.COMMA, KeyInputType.Typing)};
			case '.':
				return new KeyInput[] {new KeyInput(Key.DOT, KeyInputType.Typing)};
			case ' ':
				return new KeyInput[] {new KeyInput(Key.SPACE, KeyInputType.Typing)};
			default:
				throw new InvalidArgumentException(VariableNameCatalogue.CHARACTER, character, "is not valid");
		}
	}
	
	//static method
	public static KeyInput fromSpecification(final BaseNode specification) {
		return
		new KeyInput(
			Key.fromSpecification(specification.getRefAttributeAt(1)),
			KeyInputType.fromSpecification(specification.getRefAttributeAt(2))
		);
	}
	
	//static method
	private static KeyInput[] createTypingBetweenShiftPressAndRelease(final Key key) {
		return
		new KeyInput[] {
			new KeyInput(Key.SHIFT, KeyInputType.Press),
			new KeyInput(key, KeyInputType.Typing),
			new KeyInput(Key.SHIFT, KeyInputType.Release)
		};
	}
	
	//attribute
	private final Property<Key> key =
	new Property<>(PascalCaseNameCatalogue.KEY, this::setKey, Key::fromSpecification);
	
	//attribute
	private final Property<KeyInputType> inputType =
	new Property<>(INPUT_TYPE_HEADER, this::setInputType, KeyInputType::fromSpecification);
	
	//constructor
	public KeyInput(final Key key, final KeyInputType inputType) {
		setKey(key);
		setInputType(inputType);
	}
	
	//method
	public KeyInputType getInputType() {
		return inputType.getValue();
	}
	
	//method
	public Key getKey() {
		return key.getValue();
	}
	
	//method
	private void setInputType(final KeyInputType inputType) {
		this.inputType.setValue(inputType);
	}
	
	//method
	private void setKey(final Key key) {
		this.key.setValue(key);
	}
}
