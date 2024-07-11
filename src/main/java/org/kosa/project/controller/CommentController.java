package org.kosa.project.controller;

import lombok.RequiredArgsConstructor;
import org.kosa.project.service.CommentService;
import org.kosa.project.service.dto.CommentRequest;
import org.kosa.project.service.dto.CommentResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public CommentResponse submitComment(@PathVariable final Long postId, @RequestBody final CommentRequest commentRequest) {
        long id = commentService.submitComment(commentRequest);
        return commentService.findCommentById(id);
    }
}
