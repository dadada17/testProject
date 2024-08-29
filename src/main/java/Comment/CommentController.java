package Comment;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CommentController {

    @Autowired
    public CommentRepository commentRepository;


    @PostMapping("/board/post/{postId}/comment")
    public String comment(@PathVariable int postId, Comments comments) {
        Optional<Comments> comment = commentRepository.findById(postId);
        comments.setPost_id(postId);
        commentRepository.save(comments);
        return "redirect:/board";
    }

    @GetMapping("/board/post/{postId}/comment/{commentId}/edit")
    public String commentEdit() {
        System.out.println("댓글 수정");
        return "comment-Edit";
    }

    @PostMapping("/board/post/{postId}/comment/{commentId}/edit")
    public String commentsEdit(@PathVariable int postId, @PathVariable int commentId, Comments comments) {
        Comments comment = commentRepository.findByPostIdAndCommentId(postId, commentId);
        comment.setContent(comments.getContent());
        commentRepository.save(comment);
        return "redirect:/board";
    }

    @GetMapping("/board/post/{postId}/comment/{commentId}/delete")
    public String commentDelete() {
        System.out.println("댓글 삭제");
        return "comment-Delete";
    }

    @PostMapping("/board/post/{postId}/comment/{commentId}/edit")
    public String commentsDelete(@PathVariable int postId, @PathVariable int commentId, Comments comments) {
        Comments comment = commentRepository.findByPostIdAndCommentId(postId, commentId);
        commentRepository.delete(comment);
        return "redirect:/board";
    }
}
