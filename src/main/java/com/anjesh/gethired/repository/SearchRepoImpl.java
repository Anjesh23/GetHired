package com.anjesh.gethired.repository;

import com.anjesh.gethired.model.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchRepoImpl implements SearchRepo{

    @Autowired
    MongoClient mongoClient;
    @Autowired
    MongoConverter convert;

    @Override
    public List<Post> findByText(String text) {

        final List<Post> posts = new ArrayList<>();

        MongoDatabase database = mongoClient.getDatabase("HireDB");
        MongoCollection<Document> collection = database.getCollection("JobPosted");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("text",
                        new Document("query", text)
                        .append("path", Arrays.asList("techs", "desc", "profile")))),
                        new Document("$sort",
                        new Document("exp", 1L)),
                        new Document("$limit", 5L)));

        result.forEach(doc -> posts.add(convert.read(Post.class, doc)));
        return posts;
    }
}
