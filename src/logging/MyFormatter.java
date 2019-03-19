package logging;

import java.sql.Date;
import java.time.Instant;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		return "[" + Date.from(Instant.now()) + "] " + record.getLoggerName() + " : " + record.getMessage() + "\n";
	}

}
