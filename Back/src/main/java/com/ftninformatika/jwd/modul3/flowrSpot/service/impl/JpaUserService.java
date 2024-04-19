package com.ftninformatika.jwd.modul3.flowrSpot.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.flowrSpot.model.User;
import com.ftninformatika.jwd.modul3.flowrSpot.repository.UserRepository;
import com.ftninformatika.jwd.modul3.flowrSpot.service.UserService;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.UserPasswordChangeDTO;

@Service
public class JpaUserService implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public User findOneById(Long id) {
		return userRepository.findOneById(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public Page<User> findAll(int pageNo) {
		return userRepository.findAll(PageRequest.of(pageNo, 3));
	}

	@Override
	public boolean changePassword(Long id, UserPasswordChangeDTO userPasswordChangeDTO) {

		Optional<User> result = userRepository.findById(id);

        if(!result.isPresent()) {
            throw new EntityNotFoundException();
        }

        User user = result.get();

        if(!user.getUsername().equals(userPasswordChangeDTO.getUsername())
                || !user.getPassword().equals(userPasswordChangeDTO.getPassword())){
            return false;
        }

        String password = userPasswordChangeDTO.getPassword();
        if (!userPasswordChangeDTO.getPassword().equals("")) {
            password = passwordEncoder.encode(userPasswordChangeDTO.getPassword());
        }

        user.setPassword(password);

        userRepository.save(user);

        return true;
	}

	@Override
	public Optional<User> findOne(Long id) {
        return userRepository.findById(id);

	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);	
	}

	@Override
	public Optional<User> findbyUsername(String username) {
		return userRepository.findFirstByUsername(username);
	}

}
