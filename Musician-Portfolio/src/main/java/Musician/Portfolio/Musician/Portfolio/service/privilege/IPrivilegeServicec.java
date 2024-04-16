package Musician.Portfolio.Musician.Portfolio.service.privilege;


import Musician.Portfolio.Musician.Portfolio.domain.dtos.privileges.PrivilegeCreationDto;
import Musician.Portfolio.Musician.Portfolio.domain.dtos.privileges.PrivilegeResultDto;

import java.util.List;

public interface IPrivilegeServicec {
    PrivilegeResultDto create(PrivilegeCreationDto privilegeDto);
    PrivilegeResultDto getById(Long id);
    PrivilegeResultDto update(Long id, PrivilegeCreationDto privilegeDto);
    List<PrivilegeResultDto> getAll();
    void delete(Long id);
}
