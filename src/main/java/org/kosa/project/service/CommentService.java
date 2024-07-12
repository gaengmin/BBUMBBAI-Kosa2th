package org.kosa.project.service;

import lombok.RequiredArgsConstructor;
import org.kosa.project.repository.mapper.CommentMapper;
import org.kosa.project.service.dto.comment.CommentRequestDto;
import org.kosa.project.service.dto.comment.CommentResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;

    @Transactional
    public Long submitComment(final CommentRequestDto params) {
        System.out.println("Service"+params);
        commentMapper.submit(params);
        return params.getMeetingCommentId();
    }

    public CommentResponseDto findCommentById(final Long reMeetingId) {
        return commentMapper.findById(reMeetingId);
    }

    public List<CommentResponseDto> findAllComments(final Long meetingId) {
        return commentMapper.findAll(meetingId);
    }
    @Transactional
    public long deleteCommentById(final Long reMeetingId) {
        commentMapper.deleteById(reMeetingId);
        return reMeetingId;
    }
}
