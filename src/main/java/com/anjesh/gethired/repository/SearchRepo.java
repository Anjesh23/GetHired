package com.anjesh.gethired.repository;

import com.anjesh.gethired.model.Post;

import java.util.List;

public interface SearchRepo {

    List<Post> findByText(String text);
}
