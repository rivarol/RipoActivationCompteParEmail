package nc.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppTest {

	@Test
	public void test() {
		try {
			ClassPathXmlApplicationContext app=new ClassPathXmlApplicationContext(new String[]{"spring/application-config.xml"});
				assertTrue(true);
		} catch (Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}

}
