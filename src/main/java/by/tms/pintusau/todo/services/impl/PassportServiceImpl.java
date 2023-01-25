package by.tms.pintusau.todo.services.impl;

import by.tms.pintusau.todo.dtos.PassportDto;
import by.tms.pintusau.todo.entities.Passport;
import by.tms.pintusau.todo.repositories.PassportRepository;
import by.tms.pintusau.todo.services.PassportService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PassportServiceImpl implements PassportService {

    private final PassportRepository passportRepository;

    @Override
    public PassportDto create(PassportDto passportDto) {
        Passport passport = Passport.builder()
                .serialNumber(passportDto.getSerial())
                .build();

        passportRepository.save(passport);
        return passportDto;
    }

    @Override
    public Page<Passport> getAll(Pageable pageable) {
        return passportRepository.findAll(pageable);
    }

    @Override
    public List<PassportDto> findAll() {
        return passportRepository.findAll()
                .stream()
                .map(passport -> PassportDto.builder().serial(passport.getSerialNumber()).build())
                .collect(Collectors.toList());
    }

    @Override
    public PassportDto findById(Long id) {
        return passportRepository.findById(id)
                .map(passport -> PassportDto.builder().serial(passport.getSerialNumber()).build())
                .orElse(null);
    }
}
