package cz.cvut.fel.oi.swa.rengars.userservice.rest.users.dtos;

import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.dtos.UserListDTO;
import org.junit.Assert;
import org.junit.Test;

public class UserListDTOTest {

    @Test
    public void userListDTOTest() {
        UserListDTO userListDTO = new UserListDTO();

        Assert.assertNotNull(userListDTO.getUserList().size());
        Assert.assertEquals(0, userListDTO.getUserList().size());
    }

}
