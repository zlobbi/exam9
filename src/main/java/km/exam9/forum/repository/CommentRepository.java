package km.exam9.forum.repository;

import km.exam9.forum.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CommentRepository extends PagingAndSortingRepository<Comment, String> {

}
