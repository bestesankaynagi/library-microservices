package tr.com.innova.internship.commonrest.rest.mapper;

public interface Mapper<E, D> {
    E toEntity(D dto);
    D toDto(E entity);




}
