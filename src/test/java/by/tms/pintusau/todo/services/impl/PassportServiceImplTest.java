package by.tms.pintusau.todo.services.impl;

import by.tms.pintusau.todo.dtos.PassportDto;
import by.tms.pintusau.todo.entities.Passport;
import by.tms.pintusau.todo.exceptions.ResourceNotFoundException;
import by.tms.pintusau.todo.repositories.PassportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PassportServiceImplTest {
    @Mock
    private PassportRepository passportRepository;
    @InjectMocks
    private PassportServiceImpl passportService;
    private PassportDto passportDto;
    private Passport passport;

    @BeforeEach
    public void init() {
        String serial = "123-TEST-SERIAL";
        passportDto = PassportDto.builder()
                .serial(serial)
                .build();

        passport = Passport.builder()
                .id(1L)
                .serialNumber(serial)
                .build();
    }

    @Test
    void testCreate() {
        PassportDto actual = passportService.create(passportDto);

        assertEquals(passportDto, actual);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        when(passportRepository.findById(anyLong())).thenReturn(Optional.of(passport));

        PassportDto actual = passportService.findById(id);

        assertEquals(passportDto, actual);
    }

    @Test
    void testFindByIdThrowsNotFound() {
        Long id = 1L;
        when(passportRepository.findById(anyLong())).thenThrow(new ResourceNotFoundException("Not found"));

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            passportService.findById(id);
        });

        assertEquals("Not found", thrown.getMessage());
    }
}
