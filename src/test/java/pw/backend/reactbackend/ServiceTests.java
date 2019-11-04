package pw.backend.reactbackend;


import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pw.backend.reactbackend.entity.User;
import pw.backend.reactbackend.exceptions.ResourceNotFoundException;
import pw.backend.reactbackend.repository.UserRepository;
import pw.backend.reactbackend.service.UserService;


import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;



@RunWith(MockitoJUnitRunner.Silent.class)
public class ServiceTests {
    private UserService service;

    @Mock
    private UserRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = mock(UserRepository.class);
        service = new UserService(repository);
    }

    @Test
    public void givenNotExistingUser_whenFindByLogin_thenNotFoundException() {
        User user = new User();
        user.setLogin("login");

        try {
            service.FindByLogin(user.getLogin());

        } catch (ResourceNotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("User with login [login] not found.")));
        }
    }


    @Test
    public void givenExistingUser_whenFindByLogin_thenReturningUser() {
        User user = new User("login", "firstname", "lastname", "date", true);

       given(repository.findByLogin("login")).willReturn(user);
        assertThat(service.FindByLogin(user.getLogin()), is(equalTo(user)));
    }

    @Test
    public void givenNotExistingUser_whenUpdateUser_thenThrowResourceNotFoundException() {
        User user = new User();
        user.setLogin("login");

        try {
            service.updateUser(user);
            fail("Should throw ResourceNotFoundException");
        } catch (ResourceNotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("User with login [login] not found.")));
        }
        verify(repository, times(0)).save(any(User.class));
    }

    @Test
    public void givenExistingUser_whenUpdateUser_thenExecuteSaveMethod() {
        User user = new User();
        user.setLogin("login");
        given(repository.findByLogin(user.getLogin())).willReturn(user);
        service.updateUser(user);

        verify(repository, times(1)).save(eq(user));
    }

}
