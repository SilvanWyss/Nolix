//package declaration
package ch.nolix.core.sql;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//class
final class SQLConnectionWrapper implements AutoCloseable {
	
	//static method
	public static SQLConnectionWrapper forSQLConnection(final SqlConnection pSQLConnection) {
		return new SQLConnectionWrapper(pSQLConnection);
	}
	
	//attribute
	private final SqlConnection mSQLConnection;
	
	//attribute
	private boolean available = true;
	
	//constructor
	private SQLConnectionWrapper(final SqlConnection pSQLConnection) {
		
		GlobalValidator.assertThat(pSQLConnection).thatIsNamed(SqlConnection.class).isNotNull();
		GlobalValidator.assertThat(pSQLConnection).thatIsNamed(SqlConnection.class).fulfills(SqlConnection::isOpen);
		
		mSQLConnection = pSQLConnection;
	}
	
	//method
	public SqlConnection getOriSQLConnection() {
		
		assertIsAvailable();
		
		return mSQLConnection;
	}
	
	//method
	@Override
	public void close() {
		mSQLConnection.internalCloseDirectly();
	}
	
	//method
	public boolean contains(final SqlConnection pSQLConnection) {
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
			throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not available");
		}
	}
}
