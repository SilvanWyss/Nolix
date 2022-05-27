//package declaration
package ch.nolix.core.sql;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//class
final class SQLConnectionWrapper implements AutoCloseable {
	
	//static method
	public static SQLConnectionWrapper forSQLConnection(final SQLConnection pSQLConnection) {
		return new SQLConnectionWrapper(pSQLConnection);
	}
	
	//attribute
	private final SQLConnection mSQLConnection;
	
	//attribute
	private boolean available = true;
	
	//constructor
	private SQLConnectionWrapper(final SQLConnection pSQLConnection) {
		
		GlobalValidator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		GlobalValidator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).fulfills(SQLConnection::isOpen);
		
		mSQLConnection = pSQLConnection;
	}
	
	//method
	public SQLConnection getRefSQLConnection() {
		
		assertIsAvailable();
		
		return mSQLConnection;
	}
	
	//method
	@Override
	public void close() {
		mSQLConnection.internalCloseDirectly();
	}
	
	//method
	public boolean contains(final SQLConnection pSQLConnection) {
		return (mSQLConnection == pSQLConnection);
	}
	
	//method
	public boolean isAvailable() {
		return available;
	}
	
	//method
	public boolean isInUse() {
		return !isAvailable();
	}
	
	//method
	public void setAvailable() {
		available = true;
	}
	
	//method
	public void setAsInUse() {
		
		assertIsAvailable();
		
		available = false;
	}
		
	//method
	private void assertIsAvailable() {
		if (!isAvailable()) {
			throw new InvalidArgumentException(this, "is not available");
		}
	}
}
