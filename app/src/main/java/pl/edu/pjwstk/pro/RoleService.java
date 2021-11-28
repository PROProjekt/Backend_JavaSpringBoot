package pl.edu.pjwstk.pro;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjwstk.pro.entities.RoleEntity;

import java.util.Optional;

@Service
@Transactional
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleEntity getOrCreate(String roleName){
        Optional<RoleEntity> roleOptional = roleRepository.getRoleEntityByRole(roleName);
        if(roleOptional.isEmpty()){
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setRole(roleName);
            return roleRepository.save(roleEntity);
        }else{
            return roleOptional.get();
        }

    }




}
