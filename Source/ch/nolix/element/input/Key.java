//package declaration
package ch.nolix.element.input;

//Java import
import java.awt.event.KeyEvent;

//own imports
import ch.nolix.core.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.core.invalidArgumentExceptions.NonRepresentingArgumentException;
import ch.nolix.core.node.BaseNode;
import ch.nolix.element.baseAPI.IElementEnum;

//enum
public enum Key implements IElementEnum {
	A,
	B,
	C,
	D,
	E,
	F,
	G,
	H,
	I,
	J,
	K,
	L,
	M,
	N,
	O,
	P,
	Q,
	R,
	S,
	T,
	U,
	V,
	W,
	X,
	Y,
	Z,
	AE,
	OE,
	UE,
	NUMBER_0,
	NUMBER_1,
	NUMBER_2,
	NUMBER_3,
	NUMBER_4,
	NUMBER_5,
	NUMBER_6,
	NUMBER_7,
	NUMBER_8,
	NUMBER_9,
	F1,
	F2,
	F3,
	F4,
	F5,
	F6,
	F7,
	F8,
	F9,
	F10,
	F11,
	F12,
	ARROW_UP,
	ARROW_DOWN,
	ARROW_LEFT,
	ARROW_RIGHT,
	ALTERNATIVE,
	BACKSPACE,
	BREAK,
	CAPS_LOOK,
	COMMA,
	CONTROL,
	DELETE,
	DOLLAR_SYMBOL,
	DOT,
	END,
	ENTER,
	ESCAPE,
	EXCLAMATION_MARK,
	GRADE_SYMBOL,
	GRAVIS,
	HOME,
	HYPHEN,
	INSERT,
	MENU,
	NUMBER_LOCK,
	PAGE_DOWN,
	PAGE_UP,
	PRINT_SCREEN,
	QUESTION_MARK,
	RELATIONS,
	SCROLL_LOCK,
	SHIFT,
	SPACE,
	TABULATOR,
	WINDOWS;
	
	//static method
	public static Key fromAWTKeyEvent(final KeyEvent keyEvent) {
		switch (keyEvent.getKeyCode()) {
			case KeyEvent.VK_A:
				return A;
			case KeyEvent.VK_B:
				return B;
			case KeyEvent.VK_C:
				return C;	
			case KeyEvent.VK_D:
				return D;
			case KeyEvent.VK_E:
				return E;
			case KeyEvent.VK_F:
				return F;	
			case KeyEvent.VK_G:
				return G;
			case KeyEvent.VK_H:
				return H;
			case KeyEvent.VK_I:
				return I;	
			case KeyEvent.VK_J:
				return J;
			case KeyEvent.VK_K:
				return K;
			case KeyEvent.VK_L:
				return L;	
			case KeyEvent.VK_M:
				return M;
			case KeyEvent.VK_N:
				return N;
			case KeyEvent.VK_O:
				return O;	
			case KeyEvent.VK_P:
				return P;
			case KeyEvent.VK_Q:
				return Q;
			case KeyEvent.VK_R:
				return R;
			case KeyEvent.VK_S:
				return S;
			case KeyEvent.VK_T:
				return T;
			case KeyEvent.VK_U:
				return U;
			case KeyEvent.VK_V:
				return V;
			case KeyEvent.VK_W:
				return W;
			case KeyEvent.VK_X:
				return X;
			case KeyEvent.VK_Y:
				return Y;
			case KeyEvent.VK_Z:
				return Z;
			case KeyEvent.VK_0:
				return NUMBER_0;
			case KeyEvent.VK_1:
				return NUMBER_1;
			case KeyEvent.VK_2:
				return NUMBER_2;
			case KeyEvent.VK_3:
				return NUMBER_3;
			case KeyEvent.VK_4:
				return NUMBER_4;
			case KeyEvent.VK_5:
				return NUMBER_5;
			case KeyEvent.VK_6:
				return NUMBER_6;
			case KeyEvent.VK_7:
				return NUMBER_7;
			case KeyEvent.VK_8:
				return NUMBER_8;
			case KeyEvent.VK_9:
				return NUMBER_9;
			case KeyEvent.VK_LEFT:
				return ARROW_LEFT;
			case KeyEvent.VK_RIGHT:
				return ARROW_RIGHT;
			case KeyEvent.VK_UP:
				return ARROW_UP;
			case KeyEvent.VK_DOWN:
				return ARROW_DOWN;
			case KeyEvent.VK_BACK_SPACE:
				return BACKSPACE;
			case KeyEvent.VK_COMMA:
				return COMMA;
			case KeyEvent.VK_CONTROL:
				return CONTROL;
			case KeyEvent.VK_DELETE:
				return Key.DELETE;
			case KeyEvent.VK_DOLLAR:
				return DOLLAR_SYMBOL;
			case KeyEvent.VK_ENTER:
				return Key.ENTER;
			case KeyEvent.VK_ESCAPE:
				return ESCAPE;
			default:
				throw new InvalidArgumentException(keyEvent);
		}
	}
	
	//static method
	public static Key fromSpecification(final BaseNode specification) {
		return valueOf(specification.getOneAttributeAsString());
	}
	
	//method
	public boolean isCharacter() {
		
		if (isLetter() || isNumber()) {
			return true;
		}
		
		switch (this) {
			case COMMA:			
			case DOLLAR_SYMBOL:
			case DOT:
			case EXCLAMATION_MARK:
			case GRADE_SYMBOL:
			case GRAVIS:
			case HYPHEN:
			case QUESTION_MARK:
			case RELATIONS:
			case SPACE:
				return true;
			default:
				return false;
		}
	}
	
	//method
	public boolean isLetter() {
		switch(this) {
			case A:
			case B:
			case C:
			case D:
			case E:
			case F:
			case G:
			case H:
			case I:
			case J:	
			case K:
			case L:
			case M:
			case N:
			case O:
			case P:
			case Q:
			case R:
			case S:
			case T:
			case U:
			case V:
			case W:
			case X:
			case Y:
			case Z:
				return true;
			default:
				return false;
		}
	}
	
	//method
	public boolean isNumber() {
		switch(this) {
			case NUMBER_0:
			case NUMBER_1:
			case NUMBER_2:
			case NUMBER_3:
			case NUMBER_4:
			case NUMBER_5:
			case NUMBER_6:
			case NUMBER_7:
			case NUMBER_8:
			case NUMBER_9:
				return true;
			default:
				return false;
		}
	}
	
	//method
	public char toChar() {
		switch(this) {
			case A:
				return 'a';
			case B:
				return 'b';
			case C:
				return 'c';
			case D:
				return 'd';
			case E:
				return 'e';
			case F:
				return 'f';
			case G:
				return 'g';
			case H:
				return 'h';
			case I:
				return 'i';
			case J:
				return 'j';
			case K:
				return 'k';
			case L:
				return 'l';
			case M:
				return 'm';
			case N:
				return 'n';
			case O:
				return 'o';
			case P:
				return 'p';
			case Q:
				return 'q';
			case R:
				return 'r';
			case S:
				return 's';
			case T:
				return 't';
			case U:
				return 'u';
			case V:
				return 'v';
			case W:
				return 'w';
			case X:
				return 'x';
			case Y:
				return 'y';
			case Z:
				return 'z';
			case NUMBER_0:
				return '0';
			case NUMBER_1:
				return '1';
			case NUMBER_2:
				return '2';
			case NUMBER_3:
				return '3';
			case NUMBER_4:
				return '4';
			case NUMBER_5:
				return '5';
			case NUMBER_6:
				return '6';
			case NUMBER_7:
				return '7';
			case NUMBER_8:
				return '8';
			case NUMBER_9:
				return '9';
			case COMMA:
				return ',';
			case DOLLAR_SYMBOL:
				return '$';
			case DOT:
				return '.';
			case EXCLAMATION_MARK:
				return '!';
			case GRADE_SYMBOL:
				return '�';
			case GRAVIS:
				return '`';
			case HYPHEN:
				return '-';
			case QUESTION_MARK:
				return '?';
			case RELATIONS:
				return '<';
			case SPACE:
				return ' ';
		default:
			throw new NonRepresentingArgumentException(this, Character.class);				
		}
	}
}
