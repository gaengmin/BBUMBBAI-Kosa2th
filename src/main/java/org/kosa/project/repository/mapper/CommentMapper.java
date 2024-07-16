package org.kosa.project.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.kosa.project.service.dto.comment.CommentRequestDto;
import org.kosa.project.service.dto.comment.CommentResponseDto;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentMapper {
    void submit(CommentRequestDto params);

    CommentResponseDto findById(Long reMeetingId);

    List<CommentResponseDto> findAll(@Param("meetingId") Long meetingId, @Param("page") int page);


    void update(CommentRequestDto params);

    void deleteById(Long meetingCommentId);

    int count(Long meetingId);
}
