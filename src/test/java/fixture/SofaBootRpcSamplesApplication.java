package fixture;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import com.shfft.mock.service.PersonService;

@ImportResource({ "classpath*:provider.xml" })
@org.springframework.boot.autoconfigure.SpringBootApplication
public class SofaBootRpcSamplesApplication {

	public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SofaBootRpcSamplesApplication.class);
        ApplicationContext applicationContext = springApplication.run(args);          

        PersonService helloSyncServiceReference = (PersonService) applicationContext
          .getBean("helloSyncServiceImpl");

        System.out.println(helloSyncServiceReference.sayName("sync"));
  }
	
}
