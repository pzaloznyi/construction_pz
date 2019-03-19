package logging;

import java.io.IOException;
import java.util.logging.FileHandler;

public class MyHandler extends FileHandler {

	public MyHandler(String pattern) throws IOException, SecurityException {
		super(pattern);
	}

}
