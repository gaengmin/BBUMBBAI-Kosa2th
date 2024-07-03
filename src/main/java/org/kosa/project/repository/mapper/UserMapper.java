package org.kosa.project.repository.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.kosa.project.service.dto.UserDto;

@Mapper
public interface UserMapper {
    void save(UserDto userDto);
}
