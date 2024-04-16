package Musician.Portfolio.Musician.Portfolio.service.role;


import Musician.Portfolio.Musician.Portfolio.domain.dtos.privileges.PrivilegeCreationDto;
import Musician.Portfolio.Musician.Portfolio.domain.dtos.roles.RoleCreationDto;
import Musician.Portfolio.Musician.Portfolio.domain.dtos.roles.RoleResultDto;
import Musician.Portfolio.Musician.Portfolio.domain.dtos.roles.RoleUpdateDto;

import java.util.List;

public interface IRoleService {
    RoleResultDto getByName(String name);
    RoleResultDto getById(Long id);
    List<RoleResultDto> getAll();
    RoleResultDto update(Long id, RoleUpdateDto roleDto);
    RoleResultDto create(RoleCreationDto roleDto);
    RoleResultDto addPrivilege(Long roleId, List<PrivilegeCreationDto> privileges);
    void delete(Long id);

}
