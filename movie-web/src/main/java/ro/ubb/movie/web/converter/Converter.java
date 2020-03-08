package ro.ubb.movie.web.converter;

import ro.ubb.movie.core.model.BaseEntity;
import ro.ubb.movie.web.dto.BaseEntityDto;

public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseEntityDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}

