package testik;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import dao.Role;
import dao.User;

public class Testil {
	private static final String REST_SERVICE_URL = "http://localhost:8085/rest/user";
	private static final String REST_SERVICE_URL_ROLE = "http://localhost:8085/rest/role";
	
	public static void main(String[] args){
		
		
		System.out.println("*****find log1*******");
		User user = (User)new RestTemplate().getForObject(
                "http://localhost:8085/rest/user/log1", User.class);
		System.out.println(user);
		
		System.out.println("******findAll******");
		
		List<User> userAll = new ArrayList<User>();
		userAll = new RestTemplate().getForObject(REST_SERVICE_URL, List.class);
		System.out.println(userAll);
		
		System.out.println("******create log5******");
		User userNew = new User();
		userNew.setLogin("log5");
		userNew.setEmail("log5");
		new RestTemplate().postForObject(REST_SERVICE_URL, userNew, User.class);
		System.out.println("create log5");
		
		System.out.println("******delete log5******");
		new RestTemplate().delete("http://localhost:8085/rest/user/log5");
		System.out.println("delete log5");
		
		System.out.println("******update log5******");
		user.setEmail("log5@mail.ru");
		
		new RestTemplate().put(REST_SERVICE_URL, user);
		
		System.out.println("*****find role = Admin*******");
		Role role = (Role)new RestTemplate().getForObject(
                "http://localhost:8085/rest/role/Admin", Role.class);
		System.out.println(role);
		
		System.out.println("*****create role = guest*******");
		Role roleNew = new Role();
		roleNew.setName("Guest");
		new RestTemplate().postForObject(REST_SERVICE_URL_ROLE, roleNew, User.class);
		System.out.println("create Guest");
		
		System.out.println("*****delete role = guest*******");
		new RestTemplate().delete("http://localhost:8085/rest/role/Guest");
		System.out.println("delete Guest");
		
		System.out.println("*****update role = guest*******");
		role.setName("SuperAdmin");
		new RestTemplate().put(REST_SERVICE_URL_ROLE,role);
		role.setName("Admin");
		new RestTemplate().put(REST_SERVICE_URL_ROLE,role);
		System.out.println("update Guest");		
	}
}
