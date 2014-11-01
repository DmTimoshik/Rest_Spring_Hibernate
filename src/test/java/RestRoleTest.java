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

import dao.Role;
import dao.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-beans.xml" })
public class RestRoleTest {
	private static final String REST_SERVICE_URL = "http://10.10.3.243:8080/rest/role";
	
	 @Autowired
	 @Qualifier("restTemplate")
	 private RestTemplate restTemplate;

	@Test
	public void testFindRole() {
		String rest_service_url = REST_SERVICE_URL + "/Admin";
		Role role = (Role)new RestTemplate().getForObject(rest_service_url, Role.class);
		Assert.assertNotNull(role);
		Assert.assertTrue(role.getName().equals("Admin"));
	}
	
	@Test
	public void testCreateRole() {
		Role roleNew = new Role();
		roleNew.setName("Guest");
		new RestTemplate().postForObject(REST_SERVICE_URL, roleNew, User.class);
		
		String rest_service_url = REST_SERVICE_URL + "/Guest";
		Role role = (Role)new RestTemplate().getForObject(rest_service_url, Role.class);
		Assert.assertNotNull(role);
		
		new RestTemplate().delete(rest_service_url);
	}

	@Test
	public void testDeleteRole() {
		Role roleNew = new Role();
		roleNew.setName("Guest");
		new RestTemplate().postForObject(REST_SERVICE_URL, roleNew, User.class);
		
		String rest_service_url = REST_SERVICE_URL + "/"+roleNew.getName();
		new RestTemplate().delete(rest_service_url);
		
		Role role = (Role)new RestTemplate().getForObject(rest_service_url, Role.class);
		Assert.assertNull(role);
	}
	
	@Test
	public void testUpdateRole() {
		
		String rest_service_url = REST_SERVICE_URL + "/User";
		Role role = (Role)new RestTemplate().getForObject(rest_service_url, Role.class);
		role.setName("Guest");
		new RestTemplate().put(REST_SERVICE_URL,role);
		
		String rest_service_url_superAdmin = REST_SERVICE_URL + "/"+role.getName();
		role = (Role)new RestTemplate().getForObject(rest_service_url_superAdmin, Role.class);
		Assert.assertNotNull(role);
		
		role.setName("User");
		new RestTemplate().put(REST_SERVICE_URL,role);
	}
}
