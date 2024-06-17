package ru.netology.repository;

import ru.netology.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class PostRepository {
    private final ConcurrentHashMap<Integer, Post> repository;

    public PostRepository() {
        repository = new ConcurrentHashMap<>();
    }

    public List<Post> all() {
        return repository.values().stream().toList();
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(repository.get(id));
    }

    public Post save(Post post) {
        if (repository.containsKey(post.hashCode())) {
            return repository.replace(post.hashCode(), post);
        }
        return repository.put(post.hashCode(), post);
/*
Как должен работать save:

-Если от клиента приходит пост с id=0, значит, это создание нового поста. Вы сохраняете его в списке
и присваиваете ему новый id. Достаточно хранить счётчик с целым числом и увеличивать на 1
при создании каждого нового поста.
-Если от клиента приходит пост с id !=0, значит, это сохранение (обновление) существующего поста.
Вы ищете его в списке по id и обновляете. Продумайте самостоятельно, что вы будете делать, если поста
с таким id не оказалось: здесь могут быть разные стратегии.
 */
    }

    public void removeById(long id) {
        repository.remove(id);
    }
}
