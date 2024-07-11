package org.kosa.project.service;

import lombok.RequiredArgsConstructor;
import org.kosa.project.repository.mapper.CommentMapper;
import org.kosa.project.service.dto.CommentRequest;
import org.kosa.project.service.dto.CommentResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;

    @Transactional
    public Long submitComment(final CommentRequest params) {
        commentMapper.submit(params);
        return params.getReMeetingId();
    }

    public CommentResponse findCommentById(final Long Id) {
        return commentMapper.findById(Id);
    }
}
