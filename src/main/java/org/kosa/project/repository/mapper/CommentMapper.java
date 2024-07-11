package org.kosa.project.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosa.project.service.dto.CommentRequest;
import org.kosa.project.service.dto.CommentResponse;

@Mapper
public interface CommentMapper {
    void submit(CommentRequest params);

    CommentResponse findById(Long id);
}
