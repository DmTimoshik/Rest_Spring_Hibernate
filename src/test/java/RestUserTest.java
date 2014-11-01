import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import dao.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-beans.xml" })
public class RestUserTest {
	private static final String REST_SERVICE_URL = "http://10.10.3.243:8080/rest/user";
	
	 @Autowired
	 @Qualifier("restTemplate")
	 private RestTemplate restTemplate;

	@Test
	public void testFindUser() {
		String rest_service_url = REST_SERVICE_URL + "/log1";
		User user = (User)restTemplate.getForObject(
				rest_service_url, User.class);
		Assert.assertNotNull(user);
		Assert.assertTrue(user.getLogin().equals("log1"));
	}
	
	@Test
	public void testFindAllUser() {
		List<User> userAll = new ArrayList<User>();
		userAll = restTemplate.getForObject(REST_SERVICE_URL, List.class);
		Assert.assertNotNull(userAll);
	}
	
	@Test
	public void testCreateUser() {
		User userNew = new User();
		userNew.setLogin("log5");
		userNew.setEmail("mail5");
		new RestTemplate().postForObject(REST_SERVICE_URL, userNew, User.class);
		
		String rest_service_url = REST_SERVICE_URL + "/log5";
		User user = (User)restTemplate.getForObject(
				rest_service_url, User.class);
		Assert.assertNotNull(user);
		restTemplate.delete(rest_service_url);
	}
	
	@Test
	public void testDeleteAllUser() {
		User userNew = new User();
		userNew.setLogin("log5");
		userNew.setEmail("mail5");
		new RestTemplate().postForObject(REST_SERVICE_URL, userNew, User.class);
		
		String rest_service_url = REST_SERVICE_URL + "/log5";
		new RestTemplate().delete(rest_service_url);
		User user = (User)restTemplate.getForObject(
				rest_service_url, User.class);
		Assert.assertNull(user);
	}
	
	@Test
	public void testUpdateAllUser() {
		User userNew = new User();
		userNew.setLogin("log5");
		userNew.setEmail("mail5");
		new RestTemplate().postForObject(REST_SERVICE_URL, userNew, User.class);
		
		String rest_service_url = REST_SERVICE_URL + "/"+userNew.getLogin();
		User user = (User)restTemplate.getForObject(
				rest_service_url, User.class);
		Assert.assertNotNull(user);
		user.setLogin("log6");
		user.setEmail("demion@mail.ru");
		user.setFirstName("fname6");
		user.setLastName("lname6");
		
		new RestTemplate().put(REST_SERVICE_URL, user);
		
		String rest_service_url_put = REST_SERVICE_URL + "/"+user.getLogin();
		User user_put = (User)restTemplate.getForObject(
				rest_service_url_put, User.class);
		Assert.assertNotNull(user_put);
		Assert.assertEquals(user_put.getLogin(), user.getLogin());
		Assert.assertEquals(user_put.getEmail(), user.getEmail());
		Assert.assertEquals(user_put.getFirstName(), user.getFirstName());
		restTemplate.delete(rest_service_url_put);
	}
}
