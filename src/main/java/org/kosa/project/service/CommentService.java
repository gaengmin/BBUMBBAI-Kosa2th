package org.kosa.project.service;

import lombok.RequiredArgsConstructor;
import org.kosa.project.repository.mapper.CommentMapper;
import org.kosa.project.service.dto.comment.CommentRequestDto;
import org.kosa.project.service.dto.comment.CommentResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Transactional
    public Long updateComment(final CommentRequestDto params) {
        commentMapper.update(params);
        return params.getMeetingCommentId();
    }

    public List<CommentResponseDto> findAllComments(final long meetingId, final int page) {
        return commentMapper.findAll(meetingId, page);
    }

    public  int countAllComments(final long meetingId) {
        return commentMapper.count(meetingId);
    }

    @Transactional
    public long deleteCommentById(final Long reMeetingId) {
        commentMapper.deleteById(reMeetingId);
        return reMeetingId;
    }
}
