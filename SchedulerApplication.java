package ptoku.com.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@EnableAsync
@SpringBootApplication
public class SchedulerApplication  extends Application{
	
		
	private ConfigurableApplicationContext springContext;
	private Parent rootNode;	
	Task task; 	
	public static void main(String[] args) {
		launch(args);
	}	
	@Override
	public void init() throws Exception{
		System.out.println("Procesors: "+ Runtime.getRuntime().availableProcessors());
		springContext = SpringApplication.run(SchedulerApplication.class);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Pane.fxml"));
		fxmlLoader.setControllerFactory(springContext::getBean);
		rootNode = fxmlLoader.load();
		task = springContext.getBean(Task.class);
		task.print();		
	}
	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = new Scene(rootNode);
		stage.setScene(scene);
		//stage.show();
		springContext.getBean(PaneController.class).setStage(stage);
		
	}
	@Override
	public void stop() {
		task.stop();
		springContext.stop();
		try {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Bean
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setThreadNamePrefix("default_task_executor_thread");
        executor.initialize();
        return executor;
    }
	@Bean
	public TaskScheduler taskScheduler() {
		return new ConcurrentTaskScheduler();		
	}
}
