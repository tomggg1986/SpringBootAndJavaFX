package ptoku.com.scheduler;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Task {
	
	@Autowired
	PaneController controller;
	boolean work = true;
	public Task() {
		System.out.println("Task Created");
	}
	@Async
	public void print() {
		System.out.println(LocalDate.now());
		System.out.println("-------------------------------------------------------------------------------------------------");
		try {
			WatchService service = FileSystems.getDefault().newWatchService();
			Map<WatchKey, Path> keyMap = new HashMap<>();
			Path path = Paths.get("C:\\files"); //observable directory
			Path path2 = Paths.get("C:\\files2"); 
			keyMap.put(path.register(service, 
					StandardWatchEventKinds.ENTRY_CREATE,
					//StandardWatchEventKinds.ENTRY_MODIFY,
					StandardWatchEventKinds.ENTRY_DELETE), path);
			keyMap.put(path2.register(service, 
					StandardWatchEventKinds.ENTRY_CREATE,
					StandardWatchEventKinds.ENTRY_MODIFY,
					StandardWatchEventKinds.ENTRY_DELETE), path2);
			keyMap.forEach((k, v) -> System.out.println("Key"+k.hashCode()+" val "+ v));
//			path.register(service, 
//					StandardWatchEventKinds.ENTRY_CREATE,
//					StandardWatchEventKinds.ENTRY_MODIFY,
//					StandardWatchEventKinds.ENTRY_DELETE);
			WatchKey watchKey;
			do {
				if(!work)
					break;
				System.out.println("after do");
				watchKey = service.take();
				System.out.println("HashCode: "+ watchKey.hashCode());
				Path eventDir = keyMap.get(watchKey);				
				for(WatchEvent<?> event : watchKey.pollEvents()) {			
						System.out.println("length: "+ event);
						WatchEvent.Kind<?> kind = event.kind();
						System.out.println("kind "+kind);
						Path eventPath = (Path) event.context();
						System.out.println(eventDir+": "+ kind+ ":"+ eventPath);						
				}
			}while(watchKey.reset());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void stop() {
		this.work = false;
	}
}
