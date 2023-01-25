package by.tms.pintusau.todo.services;

import by.tms.pintusau.todo.dtos.PassportDto;
import by.tms.pintusau.todo.entities.Passport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PassportService {

    PassportDto create(PassportDto passportDto);

    Page<Passport> getAll(Pageable pageable);

    List<PassportDto> findAll();

    PassportDto findById(Long id);
}
