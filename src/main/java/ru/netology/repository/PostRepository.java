package ru.netology.repository;

import ru.netology.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class PostRepository {
    private final CopyOnWriteArrayList<Post> repository;
    private final AtomicLong counter;

    public PostRepository() {
        repository = new CopyOnWriteArrayList<>();
        counter = new AtomicLong(0);
    }

    public List<Post> all() {
        return repository.stream().toList();
    }

    public Optional<Post> getById(long id) {
        Optional<Post> p = null;
        for (Post post : repository){
            if(post.getId() == id){
                p = Optional.of(post);
            }
        }
        return p;
    }

    public Post save(Post post) {
        if(post.getId() == 0){
            post.setId(counter.incrementAndGet());
            repository.add(post);
            return post;
        }
        for (Post p : repository){
            if(p.getId() == post.getId()){
                p.setContent(post.getContent());
                return p;
            }
        }
        post.setId(counter.incrementAndGet());
        repository.add(post);
        return post;
    }

    public void removeById(long id) {
        for (Post post : repository){
            if(post.getId() == id){
               repository.remove(post);
            }
        }
    }
}
