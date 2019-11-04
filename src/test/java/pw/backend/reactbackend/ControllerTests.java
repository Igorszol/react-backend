package pw.backend.reactbackend;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pw.backend.reactbackend.controller.ApplicationController;
import pw.backend.reactbackend.entity.User;
import pw.backend.reactbackend.exceptions.ResourceExistsException;
import pw.backend.reactbackend.exceptions.ResourceNotFoundException;
import pw.backend.reactbackend.repository.UserRepository;
import pw.backend.reactbackend.service.UserService;


import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.Fail.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.BDDMockito.given;


@RunWith(MockitoJUnitRunner.Silent.class)
public class ControllerTests {
    private ApplicationController cont;
    private UserService service;
    @Mock
    private UserRepository repository;


    @Before
    public void setUp() throws Exception {
        service = new UserService(repository);
        cont = new ApplicationController(repository, service);
    }

    @Test
    public void givenExistingUser_whenCreateUser_thenResourceExistsException() {
        User user = new User("login","name1","name2","birth",true);
        given(repository.findByLogin(user.getLogin())).willReturn(user);

        try {
            cont.createUser(user);
            fail("Should throw WrongDataException");
        } catch (ResourceExistsException ex) {
            assertThat(ex.getMessage(), is(equalTo("User with login [login] exists.")));
        }

    }

    @Test
    public void givenNotExistingUser_whenCreateUser_thenExecuteSaveMethod() {
        User user = new User("login","name1","name2","birth",true);
        given(cont.createUser(user)).willReturn(ResponseEntity.ok(user));
    }


    @Test
    public void givenNotExistingUser_findByLogin_thenNotFoundException() {
        User user = new User("login","name1","name2","birth",true);
        given(repository.findByLogin(user.getLogin())).willReturn(null);

        try {
            cont.findByLogin(user.getLogin());
            fail("Should throw NotFoundException");
        } catch (ResourceNotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("User with login [login] not found.")));
        }
    }

    @Test
    public void givenExistingUser_findByLogin_thenReturningUser() {
        User user = new User("login","name1","name2","birth",true);
        given(repository.findByLogin(user.getLogin())).willReturn(user);

        ResponseEntity<User> response = cont.findByLogin(user.getLogin());
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(response.getBody()).isEqualToComparingFieldByField(user);
    }


    @Test
    public void givenNotExistingUser_whenUpdateUser_thenNotFoundException() {
        //User user = new User("login","name1","name2","birth",true);
        User newuser = new User();
        newuser.setLogin("login");

       // given(repository.findByLogin(user.getLogin())).willReturn(null);

        try {
            cont.updateUser(newuser);
            fail("Should throw NotFoundException");
        } catch (ResourceNotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo(String.format("User with login [%s] not found.", newuser.getLogin()))));
        }
    }



    @Test
    public void givenNotExistingUser_DeleteUser_thenNotFoundException() {
        User user = new User("login","name1","name2","birth",true);

        given(repository.findByLogin(user.getLogin())).willReturn(null);

        try {
            cont.deleteByLogin(user.getLogin());
            fail("Should throw NotFoundException");
        } catch (ResourceNotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo(String.format("User with login [%s] not found.", user.getLogin()))));
        }
    }

    @Test
    public void givenExistingUser_DeleteUser_thenReturning() {
        User user = new User("login","name1","name2","birth",true);

        given(repository.findByLogin(user.getLogin())).willReturn(user);

        String response = cont.deleteByLogin(user.getLogin());
        then(response).isEqualTo("User deleted.");
    }

}