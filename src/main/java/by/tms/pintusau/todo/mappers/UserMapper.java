package by.tms.pintusau.todo.mappers;

import by.tms.pintusau.todo.dtos.auth.RegistrationRequest;
import by.tms.pintusau.todo.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "username", target = "login")
    User toEntity(RegistrationRequest registrationRequest);
}
