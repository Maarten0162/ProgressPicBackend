package com.Maarten0162.ProgressPicBackend.service;

import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import com.Maarten0162.ProgressPicBackend.DAL.PostRepo;
import com.Maarten0162.ProgressPicBackend.model.Post;
import com.Maarten0162.ProgressPicBackend.model.PostDTO;
import com.Maarten0162.ProgressPicBackend.model.websocket.PostCreatedEvent;

@Service
public class PostService {
    private final PostRepo repo;
    private final SimpMessagingTemplate messagingTemplate;
    private final UserService userService;

    public PostService(
            PostRepo postRepo,
            SimpMessagingTemplate messagingTemplate,
            UserService service
    ) {
        this.repo = postRepo;
        this.messagingTemplate = messagingTemplate;
        this.userService = service;
    }

    public Post createPost(PostDTO request) {

        Post post = new Post();
        post.setAfterDate(request.afterDate());
        post.setBeforeDate(request.beforeDate());
        post.setAfterImageUrl(request.afterImageUrl());
        post.setBeforeImageUrl(request.beforeImageUrl());
        post.setCaption(request.caption());
        post.setCreator(userService.getUserById(request.creatorId()));

        Post savedPost = repo.save(post);

        PostCreatedEvent event =
                new PostCreatedEvent(
                        savedPost.getPostId(),
                        savedPost.getCreator().getUuid(),
                        savedPost.getBeforeImageUrl(),
                        savedPost.getAfterImageUrl(),
                        savedPost.getBeforeDate(),
                        savedPost.getAfterDate(),
                        savedPost.getCaption(),
                        savedPost.getCreatedAt()
                );

        messagingTemplate.convertAndSend(
                "/topic/feed",
                event
        );

        return savedPost;
    }
}