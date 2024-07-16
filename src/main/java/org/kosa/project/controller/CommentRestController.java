package org.kosa.project.controller;

import lombok.RequiredArgsConstructor;
import org.kosa.project.security.CustomUserDetails;
import org.kosa.project.service.CommentService;
import org.kosa.project.service.dto.comment.CommentRequestDto;
import org.kosa.project.service.dto.comment.CommentResponseDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, Object> findAllComments(@RequestParam("meetingId") Long meetingId, @RequestParam(value = "page",defaultValue = "1") int page) {
        List<CommentResponseDto> comments = commentService.findAllComments(meetingId, page);
        int totalPages = (int) Math.ceil((double) commentService.countAllComments(meetingId)/10);

        Map<String, Object> response = new HashMap<>();
        response.put("comments", comments);
        response.put("page", page);
        response.put("totalPage", totalPages);

        return response;
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
