package org.kosa.project.controller;

import lombok.RequiredArgsConstructor;
import org.kosa.project.security.CustomUserDetails;
import org.kosa.project.service.CommentService;
import org.kosa.project.service.dto.comment.CommentRequestDto;
import org.kosa.project.service.dto.comment.CommentResponseDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class CommentRestController {
    private final CommentService commentService;

    @PostMapping("/{meetingId}/comments")
    public CommentResponseDto submitComment(@RequestBody final CommentRequestDto params, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = Long.valueOf(userDetails.getUserId());
        params.setUserId(userId);
        long reMeetingId = commentService.submitComment(params);
        return commentService.findCommentById(reMeetingId);
    }

    @GetMapping("/{meetingId}/comments")
    public List<CommentResponseDto> findAllComments(@PathVariable Long meetingId) {
        return commentService.findAllComments(meetingId);
    }

    @DeleteMapping("/{meetingId}/comments/{reMeetingId}")
    public Long deleteCommentById(@PathVariable final Long reMeetingId) {
        return commentService.deleteCommentById(reMeetingId);
    }

    @GetMapping("/{meetingId}/comments/{meetingCommentId}")
    public CommentResponseDto findCommentById(@PathVariable final Long meetingId, @PathVariable final Long meetingCommentId) {
        return commentService.findCommentById(meetingCommentId);
    }


    @PatchMapping("/{meetingId}/comments/{meetingCommentId}")
    public CommentResponseDto updateComment(@PathVariable final Long meetingId, @PathVariable final Long meetingCommentId, @RequestBody final CommentRequestDto params) {
        commentService.updateComment(params);
        return commentService.findCommentById(meetingCommentId);
    }

}
