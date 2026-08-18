package com.Maarten0162.ProgressPicBackend.DAL;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Maarten0162.ProgressPicBackend.model.Post;

public interface PostRepo extends JpaRepository<Post, UUID> {

}
