package dev.jt.community.repository;

import dev.jt.community.model.BoardDto;
import dev.jt.community.model.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InmemoryPostRepository implements PostRepository{
    private final BoardRepository boardRepository;
    private Long lastIndex = 0L;
    private final Map<Long, PostDto> memory = new HashMap<>();

    public InmemoryPostRepository(
            @Autowired BoardRepository boardRepository){ // 스프링의 기능을 활용해서 주입시켜 준다.
        this.boardRepository = boardRepository;

    }
    @Override
    public PostDto create(Long boardId, PostDto dto) {
        // 검증
        BoardDto boardDto = this.boardRepository.read(boardId);
        if (boardDto == null) return null;

        dto.setBoardId(boardId);
        lastIndex++;
        dto.setId(lastIndex);
        memory.put(lastIndex, dto);
        return dto;
    }

    @Override
    public PostDto read(Long boardId, Long postId) {
        PostDto postDto = memory.getOrDefault(postId, null);
        if(postDto == null) {
            return null;
        }
        else if (!Objects.equals(postDto.getBoardId(),boardId)){
            return null;
        }
        return null;
    }

    @Override
    public Collection<PostDto> readAll(Long boarId) {
        if (boardRepository.read(boarId) == null) {
            return null;
        }
        Collection<PostDto> postList = new ArrayList<>();
        memory.forEach((postId,postDto) -> {
            if (Objects.equals(postDto.getBoardId(), boarId)) {
                postList.add(postDto);
            }
        });
        return postList;
    }

    @Override
    public boolean update(Long boardId, Long postId, PostDto dto) {
        PostDto targetPost = memory.getOrDefault(postId, null);
        if(targetPost == null){
            return false;
        }
        else if (!Objects.equals(targetPost.getBoardId(), boardId)){
            return false;
        }
        else if (!Objects.equals(targetPost.getPassword(), dto.getPassword())){
            return false;
        }
        targetPost.setTitle(
                dto.getTitle() == null ? targetPost.getTitle() : dto.getTitle()
        );
        targetPost.setContent(
                dto.getContent() == null ? targetPost.getContent() : dto.getContent()
        );
        return true;
    }

    @Override
    public boolean delete(Long boardId, Long postId, String password) {
        PostDto targetPost = memory.getOrDefault(postId, null);
        if (targetPost == null){
            return false;
        }
        else if(!Objects.equals(targetPost.getBoardId(),boardId)){
            return false;
        }
        else if(!Objects.equals(targetPost.getPassword(),password)){
            return false;
        }
        memory.remove(postId);
        return true;
    }
}
