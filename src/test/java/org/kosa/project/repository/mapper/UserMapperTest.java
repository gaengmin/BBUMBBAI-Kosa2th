package org.kosa.project.repository.mapper;

import org.junit.jupiter.api.Test;
import org.kosa.project.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserMapperTest {

    @Autowired
    UserMapper mapper;
    @Test
    void save() {
        UserDto userDto = new UserDto(null, "010-3903-3030", "sexy", "sexy", LocalDate.now(), "ssdd", null);
        mapper.save(userDto);
    }
}