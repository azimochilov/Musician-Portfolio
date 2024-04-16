package Musician.Portfolio.Musician.Portfolio.domain.dtos.roles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RolePrivilegeDto {
    private String privilege;
}
