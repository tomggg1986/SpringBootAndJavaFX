package ptoku.com.scheduler;

import java.nio.file.Path;
import java.util.List;

public class FileCatcher {
	private List<Path> pathList;
	
	public void catchFile(Path path) {
		this.pathList.add(path);
	}

}
