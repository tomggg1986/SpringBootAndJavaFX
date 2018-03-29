package ptoku.com.thread;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 * Created by gkatzioura on 10/18/17.
 */
@Component
@Scope("prototype")
public class MyThread implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyThread.class);
    @Override
    public void run() {
    	for(int i = 0;i<100;i++) {
    		System.out.println("Odliczanie: "+i);
    	}
        LOGGER.info("Called from thread");
    }
}