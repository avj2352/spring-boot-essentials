package com.innovoskies.soul.lyrics.groups;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.innovoskies.soul.lyrics.groups.model.UserModel;

@Repository
public interface UserModelRepository extends MongoRepository<UserModel, String> {
    
}
