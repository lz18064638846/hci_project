package com.example.demo.jwtTest;

import com.example.demo.security.jwt.JwtProvider;
import io.jsonwebtoken.Clock;
import org.assertj.core.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

/**
 * @author Zhe Li
 * @date 2020/05/10
 */
public class JwtProviderTest {
    private static final String TEST_USERNAME = "testUser";
    private static final String ERROR_INFO = "abcd";

    @Mock
    private Clock clockMocker;

    @InjectMocks
    private JwtProvider jwtProvider;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);

        ReflectionTestUtils.setField(jwtProvider, "jwtExpiration", 3600000); // an hour
        ReflectionTestUtils.setField(jwtProvider, "jwtSecret","mySecret");

    }

    @Test
    public void differentTimeShouldCreateDifferentTokens(){
        when(clockMocker.now())
                .thenReturn(DateUtil.yesterday())
                .thenReturn(DateUtil.now());
        final String token = getToken();
        final String tokenLater = getToken();
        assertThat(token).isNotEqualTo(tokenLater);
    }

    @Test
    public void usernameFromTokenShouldBeTheSameAsItWas(){
        when(clockMocker.now()).thenReturn(DateUtil.now());

        final String token = getToken();

        assertThat(jwtProvider.getUserNameFromJwtToken(token)).isEqualTo(TEST_USERNAME);
    }

    @Test
    public void failCases(){
        when(clockMocker.now()).thenReturn(DateUtil.yesterday());

        final String token = getToken();

        assertFalse("invalid token", jwtProvider.validateJwtToken(ERROR_INFO + token));
        assertFalse("expired token", jwtProvider.validateJwtToken(token));
        assertFalse("empty token", jwtProvider.validateJwtToken(""));
    }

    private String getToken() {
        return jwtProvider.generateJwtToken(new UserDetailsDummy(TEST_USERNAME));
    }
}
