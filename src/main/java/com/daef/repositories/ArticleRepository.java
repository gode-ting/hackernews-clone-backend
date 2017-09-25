/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daef.repositories;
import com.daef.models.Article;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {

    public Article findByHeadline(String firstName);
   //public List<Article> findByLastName(String lastName);

}
