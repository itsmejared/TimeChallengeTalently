package jeejava.rest;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import talently.rest.SpringRestController;
import talently.sevice.ProductService;

@RunWith(MockitoJUnitRunner.class)
public class SpringRestControllerTest {
	@Mock
	private ProductService service;

	@Spy
	@InjectMocks
	private SpringRestController controller = new SpringRestController();

	@Before
	public void init() {
		MockMvcBuilders.standaloneSetup(controller).build();
	}

}