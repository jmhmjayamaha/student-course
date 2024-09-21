package com.interview.codingtest.mapper;

import org.mapstruct.Mapper;

import com.interview.codingtest.dto.CreateUpdateStudentReqeust;
import com.interview.codingtest.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    // @Mapping(source = "address", target = "address", qualifiedByName = "addressDtoToAddress")
    Student toEntity(CreateUpdateStudentReqeust request);

    // @Named("addressDtoToAddress")
    // default Address extract(AddressDTO dto) {
    // Address address = new Address();
    // address.setCity(dto.getCity());
    // address.setState(dto.getState());
    // address.setStreet(dto.getStreet());
    //
    // return address;
    // }
}
