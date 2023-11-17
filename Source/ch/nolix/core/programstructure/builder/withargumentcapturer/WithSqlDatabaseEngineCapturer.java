//package declaration
package ch.nolix.core.programstructure.builder.withargumentcapturer;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.builder.main.ArgumentCapturer;
import ch.nolix.core.sql.SqlDatabaseEngine;

//class
public class WithSqlDatabaseEngineCapturer<N>
extends ArgumentCapturer<SqlDatabaseEngine, N> {

  //constructor
  public WithSqlDatabaseEngineCapturer(final N nextArgumentCapturer) {
    super(nextArgumentCapturer);
  }

  //method
  public final SqlDatabaseEngine getSqlDatabaseEngine() {
    return getStoredArgument();
  }

  //method
  public final N withSqlDatabaseEngine(final SqlDatabaseEngine sqlDatabaseEngine) {

    GlobalValidator.assertThat(sqlDatabaseEngine).thatIsNamed(SqlDatabaseEngine.class).isNotNull();

    return setArgumentAndGetNext(sqlDatabaseEngine);
  }
}
