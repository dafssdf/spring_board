package com.example.app.service;

import com.example.app.dto.UserDto;
import com.example.app.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class UserServiceTest {
    @Mock
    private UserMapper userMapper;
    private UserDto userDto;

    @InjectMocks
    private UserService userService;

    @BeforeEach
     void setUp(){
        userDto = new UserDto();
        userDto.setUserId("testId");
        userDto.setUserPassword("1234");
        userDto.setUserGender("M");
        userDto.setUserEmail("teset@email.com");
        userDto.setUserAddress("test address");

    }

    @Test
    @DisplayName("회원 가입")
    void register() {
        doNothing().when(userMapper).insert(any(UserDto.class));

        userService.register(userDto);

//        반환 값이 없으면 verify, doNothing을 사용하면 verify를 사용한다.
        verify(userMapper, times(1)).insert(userDto);
    }

    @Test
    @DisplayName("회원 번호 조회 : 존재 하지 않는 회원 예외 검사")
    void findUserNumberException(){
        //UserService 에서 id,pw가 들어오면 값이 null이 뜨는 것을 예외로 던졌기 때문에 검증을 해줘야 한다.
        //밑에서 id,pw가 null인걸 검사하는건 사용자가 입력을 잘못했거나 그렇기 때문에 여기서 신경 쓸건 아닌거 같다.
        doReturn(null).when(userMapper).selectUserNumber(any(String.class),any(String.class));

        assertThatThrownBy(() -> userService.findUserNumber("a","a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않는");
    }

    @Test
    @DisplayName("회원 번호 조회")
    void findUserNumber() {
        //doReturn에는 반환할 값  마지막에는 넣을 매개변수 값
        //여기서 설정을 해주고 밑에서 값을 넣어준다.
        doReturn(1L).when(userMapper).selectUserNumber(any(String.class),any(String.class));

        //아무값이나 넣어준다. 위에 설정에 따르면 매개변수는 String 으로 아무값이나 넣어주고
        // 반환 값은 1L이다.
        Long userNumber = userService.findUserNumber("test","1234");

        //위에서 1L을 반환하기로 해서 넣어준다.
        assertThat(userNumber).isEqualTo(1L);

    }
}