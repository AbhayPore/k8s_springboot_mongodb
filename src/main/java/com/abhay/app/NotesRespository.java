package com.abhay.app;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotesRespository extends MongoRepository<Note, String>{

}
