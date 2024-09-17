package com.anjesh.gethired.repository;

import com.anjesh.gethired.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post,String> {
}
