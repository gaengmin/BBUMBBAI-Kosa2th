package org.kosa.project.repository.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.kosa.project.service.dto.UserRegisterForm;

@Mapper
public interface UserMapper {
    void save(UserRegisterForm userDto);
}
