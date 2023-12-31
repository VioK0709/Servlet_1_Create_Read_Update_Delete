package service;

import exception.NotFoundException;
import model.Post;
import org.springframework.stereotype.Service;
import repository.PostRepository;

import java.util.List;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> all() {
        return repository.all();
    }

    public Post getById(long id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public Post save(Post post) {
        if (post.getId() <= repository.all().size()) {
            return repository.save(post);
        }
        throw new NotFoundException("Post with id: " + post.getId() + " not found.");
    }

    public void removeById(long id) {
        repository.removeById(id);
    }
}
