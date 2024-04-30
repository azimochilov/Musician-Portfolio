package Musician.Portfolio.Musician.Portfolio.api.role;


import Musician.Portfolio.Musician.Portfolio.domain.dtos.privileges.PrivilegeCreationDto;
import Musician.Portfolio.Musician.Portfolio.domain.dtos.roles.RoleCreationDto;
import Musician.Portfolio.Musician.Portfolio.domain.dtos.roles.RoleResultDto;
import Musician.Portfolio.Musician.Portfolio.domain.dtos.roles.RoleUpdateDto;
import Musician.Portfolio.Musician.Portfolio.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
@PreAuthorize("hasAuthority('ROLE_PRIVILEGE_SERVICE')")
@CrossOrigin("http://54.174.103.74:8080")
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public void create(@RequestBody RoleCreationDto roleDto) {

        roleService.create(roleDto);
    }

    @GetMapping
    public List<RoleResultDto> getAll() {
        return roleService.getAll();
    }


    @PatchMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody RoleUpdateDto roleDto) {
        roleService.update(id, roleDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }

    @PatchMapping("/{id}/privileges")
    public ResponseEntity<?> addPrivilege(@PathVariable Long id, @RequestBody List<PrivilegeCreationDto> privilegeDto) {
        return new ResponseEntity<>(roleService.addPrivilege(id, privilegeDto), HttpStatus.valueOf(201));
    }

}

