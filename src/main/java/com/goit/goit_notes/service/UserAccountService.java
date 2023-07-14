package com.goit.goit_notes.service;

import com.goit.goit_notes.dto.UserAccountDto;
import com.goit.goit_notes.entity.UserAccountEntity;
import com.goit.goit_notes.mapper.AccountMapper;
import com.goit.goit_notes.repository.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserAccountService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    public UserAccountService(UserAccountRepository userAccountRepository, AccountMapper accountMapper, PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserAccountDto loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountMapper.mapEntityToDto(userAccountRepository.findUserAccountEntityByUsername(username));
    }

    public void createUser(UserDetails user) {
        UserAccountEntity userAccountEntity = accountMapper.mapDtoToEntity((UserAccountDto) user);
        encodePassword(userAccountEntity);
        userAccountRepository.save(userAccountEntity);
    }

    public void updateUser(UserDetails user) {
        UserAccountEntity userAccountEntityByUsername = userAccountRepository.findUserAccountEntityByUsername(user.getUsername());
        UserAccountEntity userAccountEntity = accountMapper.mapDtoToEntity((UserAccountDto) user);
        encodePassword(userAccountEntity);
        BeanUtils.copyProperties(userAccountEntity, userAccountEntityByUsername);
        userAccountRepository.save(userAccountEntityByUsername);
    }

    public void deleteUser(String username) {
        userAccountRepository.deleteByUsername(username);
    }

    private void encodePassword(UserAccountEntity userAccountEntity) {
        userAccountEntity.setPassword(passwordEncoder.encode(userAccountEntity.getPassword()));
    }
}
