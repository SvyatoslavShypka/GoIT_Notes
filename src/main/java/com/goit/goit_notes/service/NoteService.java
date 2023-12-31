package com.goit.goit_notes.service;

import com.goit.goit_notes.dto.NoteDto;
import com.goit.goit_notes.entity.Note;
import com.goit.goit_notes.mapper.NoteMapper;
import com.goit.goit_notes.repository.NoteRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.Map;

@Slf4j
@Data
@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final NoteMapper noteMapper;

    public NoteService(NoteRepository noteRepository, NamedParameterJdbcTemplate jdbcTemplate, NoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.noteMapper = noteMapper;
    }

    public List<NoteDto> listAll() {
        List<Note> result = noteRepository.findAll();
        return noteMapper.mapEntityToDto(result);
    }

    public NoteDto add(Note note) {
        Random random = new Random();
        final long randomValue = 1000000L;
        note.setId(random.nextLong(randomValue));
        noteRepository.save(note);
        return noteMapper.mapEntityToDto(note);
    }

    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }

    public void update(Note note) {
        noteRepository.save(note);
    }

    public NoteDto getById(long id) {
        String query = "SELECT n.id, n.title, n.content FROM notes n WHERE id=:id";
        return jdbcTemplate.queryForObject(
                query,
                Map.of("id", id),
                (resultSet, index) -> {
                    return NoteDto.of(
                            resultSet.getLong("id"),
                            resultSet.getString("title"),
                            resultSet.getString("content")
                    );
                }
        );
    }

    @PostConstruct
    public void construct() {
//        log.info("CustomerService construct");
    }

    @PreDestroy
    public void destroy() {
//        log.info("CustomerService destroy");
    }
}
