package org.kosa.project.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosa.project.service.dto.comment.CommentRequestDto;
import org.kosa.project.service.dto.comment.CommentResponseDto;

import java.util.List;

@Mapper
public interface CommentMapper {
    void submit(CommentRequestDto params);

    CommentResponseDto findById(Long reMeetingId);

    List<CommentResponseDto> findAll(Long MeetingId);

    void deleteById(Long meetinCommentId);
}
