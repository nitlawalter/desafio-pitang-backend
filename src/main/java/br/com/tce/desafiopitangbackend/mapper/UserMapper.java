package br.com.tce.desafiopitangbackend.mapper;

import br.com.tce.desafiopitangbackend.dto.UserRequestDTO;
import br.com.tce.desafiopitangbackend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true) // Ignorar a senha para que seja codificada manualmente
    User toUser(UserRequestDTO userRequestDTO);

    UserRequestDTO toUserRequestDTO(User user);

}
