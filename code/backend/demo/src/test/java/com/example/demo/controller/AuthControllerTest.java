package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Optional;
import com.example.demo.jwtTest.UserDetailsDummy;
import com.example.demo.message.request.SignUpForm;
import com.example.demo.message.response.JwtResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.WebSecurityConfig;
import com.example.demo.security.jwt.JwtAuthEntryPoint;
import com.example.demo.security.jwt.JwtProvider;
import com.example.demo.security.services.UserDetailsServiceImpl;
import com.example.demo.service.AuthService;
import io.jsonwebtoken.Clock;
import org.assertj.core.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@WebMvcTest(AuthController.class)
@Import(WebSecurityConfig.class)
public class AuthControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private JwtAuthEntryPoint jwtAuthEntryPoint;

	@TestConfiguration
	static class asdff {
		@Bean
		public JwtProvider jwtProvider() {
			return new JwtProvider();
		}

		@Bean
		public UserDetailsService userDetailsService() {
			return new UserDetailsServiceImpl();
		}
	}

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private UserDetailsService userDetailsService;


	@MockBean
	AuthService authService;

	@Mock
	private Clock clockMocker;

	@Autowired
	private JwtProvider jwtProvider;

	private String token;


	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		ReflectionTestUtils.setField(jwtProvider, "jwtExpiration", 3600000); // an hour
		ReflectionTestUtils.setField(jwtProvider, "jwtSecret", "mySecret");

		when(clockMocker.now())
				.thenReturn(DateUtil.now());

		User user = new User();
		user.setId(1L);
		user.setUsername("john");
		Optional<User> userOptional = Optional.of(user);

		when(userRepository.findById(1L)).thenReturn(userOptional);
		when(userRepository.findByUsername("john")).thenReturn(userOptional);

		token = getToken("john");
		JwtResponse jwtResponse = new JwtResponse();
		jwtResponse.setAccessToken(jwtProvider.generateJwtToken(new UserDetailsDummy("john")));
		when(authService.userSignIn(anyString(),anyString())).thenReturn(jwtResponse);
		when(authService.userSignUp(any(SignUpForm.class))).thenReturn("User registered successfully!");
	}

	private String getToken(String name) {
		return "Bearer " + jwtProvider.generateJwtToken(new UserDetailsDummy(name));
	}

	@Test
	public void login() throws Exception {
		mvc.perform(post("/api/auth/signin").contentType(MediaType.APPLICATION_JSON).content("{\"username\":\"liu123\",\"password\":\"liu123\"}\n"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.tokenType").value("Bearer"));
	}

	@Test
	public void signUp() throws Exception {
		mvc.perform(post("/api/auth/signup").contentType(MediaType.APPLICATION_JSON).content("{\"username\":\"liu123\",\"password\":\"liu123\"}\n"))
				.andDo(print())
				.andExpect(status().isOk());

	}

}
