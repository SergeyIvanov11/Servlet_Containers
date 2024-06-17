package ru.netology.repository;

import ru.netology.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class PostRepository {
    private final ConcurrentHashMap<Long, Post> repository;

    public PostRepository() {
        repository = new ConcurrentHashMap<>();
        repository.put(1L, new Post(23, "first"));
    }

    public List<Post> all() {
        return repository.values().stream().toList();
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(repository.get(id));
    }

    public Post save(Post post) {
        if (repository.isEmpty()) {
            return repository.put(1L, post);
        } else if (post.getId() == 0) {
            return repository.put((long) repository.size() + 1, post);
        } else if (repository.containsKey(post.getId())) {
            return repository.replace(post.getId(), post);
        }
        return new Post(0, "Error! Not saved!");
    }

    public void removeById(long id) {
        repository.remove(id);
    }
}
