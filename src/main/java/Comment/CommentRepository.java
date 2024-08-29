package Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository <Comments, Integer> {

    Comments findByPostIdAndCommentId(int postId, int commentId);
}
