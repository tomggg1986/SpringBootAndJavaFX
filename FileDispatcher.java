package ptoku.com.scheduler;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FileDispatcher {
	@Autowired
	PaneController controller;
	RestTemplate restTemplate = new RestTemplate();
	//@Async
	public void sendFile(Stream<Path> paths) {
		System.out.println("File to send");
		paths.forEach(path -> System.out.println(path.getFileName()));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.controller.setFileName("Pliki Wysłane");
		this.controller.show();
		System.out.println("End sendFile method");
	}

}
