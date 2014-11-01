import java.util.List;

import org.dbunit.*;
import org.dbunit.dataset.IDataSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import dao.RoleDao;
import dao.UserDao;
import dao.Role;
import dao.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:config-tests.xml"}) 
@Transactional
public class DaoRoleTest extends DBTestCase {
	    @Autowired
        private UserDao userDao;
	    
        
	    @Autowired
        private RoleDao roleDao;
        
        public UserDao getUserDao() {
            return userDao;
        }

        public void setUserDao(UserDao userDao) {
            this.userDao = userDao;
        }

        public RoleDao getRoleDao() {
            return roleDao;
        }

        public void setRoleDao(RoleDao roleDao) {
            this.roleDao = roleDao;
        }


        @Override
        protected IDataSet getDataSet() throws Exception {            
            return null;           

        }    

     
        @Test
        public void testfindByNameNoRole() throws Exception {
            Role res = roleDao.findByName("wrongRole");
            assertTrue(res == null);
        }

        @Test
        public void testCreateFindByNameRole() throws Exception {
            Role role = new Role();            
            role.setName("NewAdmin");
            roleDao.create(role);
            Role res = roleDao.findByName("NewAdmin");
            assertNotNull(res);
            assertEquals(res.getName(), "NewAdmin");
        }

        @Test
        public void testUpdateRole() throws Exception {
            Role role = new Role();            
            role.setName("NewAdmin");
            roleDao.create(role);
            role.setName("UpdatedAdmin");
            roleDao.update(role);
            Role res = roleDao.findByName("UpdatedAdmin");
            assertNotNull(res);
            assertEquals("UpdatedAdmin", res.getName());
        }

        @Test
        public void testRemoveRole() throws Exception {
            Role role = new Role();            
            role.setName("guest");
            roleDao.create(role);
            roleDao.remove(role);
            Role res = roleDao.findByName("guest");
            assertTrue(res == null);
        }
    
}
