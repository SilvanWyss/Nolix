//package declaration
package ch.nolix.core.sql;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;

//class
final class SQLConnectionWrapper {
	
	//attribute
	private final SQLConnection mSQLConnection;
	
	//attribute
	private boolean available = true;
	
	//constructor
	public SQLConnectionWrapper(final SQLConnection pSQLConnection) {
		
		Validator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		Validator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).fulfills(SQLConnection::isOpen);
		assertDoesNotBelongToSQLConnectionPool(pSQLConnection);
		
		mSQLConnection = pSQLConnection;
	}
	
	//method
	public SQLConnection getRefSQLConnection() {
		
		assertIsAvailable();
		
		return mSQLConnection;
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
	private void assertDoesNotBelongToSQLConnectionPool(final SQLConnection pSQLConnection) {
		if (pSQLConnection.belongsToSQLConnectionPool()) {
			throw new InvalidArgumentException(pSQLConnection, "belongs to SQLConnectionPool");
		}
	}
	
	//method
	private void assertIsAvailable() {
		if (!isAvailable()) {
			throw new InvalidArgumentException(this, "is not available");
		}
	}
}
