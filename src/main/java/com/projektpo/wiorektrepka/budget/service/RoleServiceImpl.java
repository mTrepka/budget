package com.projektpo.wiorektrepka.budget.service;


import com.projektpo.wiorektrepka.budget.domain.Role;
import com.projektpo.wiorektrepka.budget.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("roleService")
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
	private final RoleRepository roleRepository;

	@Override
	public Role getUserRole() {
		return roleRepository.findByRole("USER");
	}
}
