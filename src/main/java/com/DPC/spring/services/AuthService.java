package com.DPC.spring.services;

import com.DPC.spring.Mappers.MappersDto;
import com.DPC.spring.entities.ERole;
import com.DPC.spring.entities.Role;
import com.DPC.spring.entities.User;
import com.DPC.spring.exceptions.EmailAlreadyUsedException;
import com.DPC.spring.exceptions.ResourceNotFoundException;
import com.DPC.spring.payload.requests.LoginRequest;
import com.DPC.spring.payload.requests.RegisterRequest;
import com.DPC.spring.payload.responses.LoginResponse;
import com.DPC.spring.repositories.RoleRepository;
import com.DPC.spring.repositories.UserRepository;
import com.DPC.spring.security.jwt.JwtTokenUtils;
import com.DPC.spring.services.Impl.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DeviceNotificationService deviceNotificationService;

    @Autowired
    RoleRepository roleRepository;

    // pour crypter le password (NB: il faut ajouter le bean BCryptPasswordEncoder dans l'application)
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtils jwtTokenUtils;
    @Autowired
    MailServiceImpl mailservice;

    final MappersDto mappersDto;

    public AuthService(MappersDto mappersDto) {
        this.mappersDto = mappersDto;
    }

    @Transactional
    public LoginResponse login(LoginRequest loginRequest)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername());
        List<Role> roles= roleRepository.findByUsers(user);
        userDetails.getAuthorities().stream().map(grantedAuthority -> grantedAuthority.getAuthority().toString()).collect(Collectors.toList());

        this.deviceNotificationService.saveOrUpdateDevice(loginRequest.getTypeDevice(),loginRequest.getTokenNotification(),user.getId());

        return new LoginResponse(this.jwtTokenUtils.generateToken(userDetails),roles);
    }

    public String register(RegisterRequest registerRequest) throws EmailAlreadyUsedException {
        // test if email already used
        if (this.userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new EmailAlreadyUsedException("Error: Email is already in use!");
        }
        // Create new user account
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setpassword(passwordEncoder.encode(registerRequest.getPassword()));




        // Traitement des Roles
        Set<String> registerRequestRoles = registerRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        // find Roles
        if (registerRequestRoles == null) {
            Role guestRole = this.roleRepository.findByName(ERole.GUEST)
                    .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
            roles.add(guestRole);
        } else {
            registerRequestRoles.forEach(role -> {
                switch (role) {

                    case "admin":
                        Role adminRole = this.roleRepository.findByName(ERole.ADMIN)

                                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "adherent":
                        Role userRole = this.roleRepository.findByName(ERole.ADHERENT)
                                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
                        roles.add(userRole);
                        break;
                    default:
                        Role guestRole = this.roleRepository.findByName(ERole.GUEST)
                                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
                        roles.add(guestRole);
                }
            });
        }

        // Affect User Roles
        user.setRoles(roles);
        this.userRepository.save(user);

        //return mappersDto.UserToUserDto(userData);
        return "User registered successfully!";
    }

    public Optional<User> requestPasswordReset(String mail) {
        return userRepository.findOneByEmailIgnoreCase(mail)
                .map(user -> {
                    user.setResetKey(RandomUtil.generateResetKey());
                    user.setResetDate(Instant.now());
                    this.userRepository.saveAndFlush(user);
                    return user;
                });
    }
    public Optional<User> resetcode(String mail) {
        return userRepository.findOneByEmailIgnoreCase(mail)
                .map(user -> {
                    user.setResetKey(RandomUtil.generateResetKey());
                    user.setResetDate(Instant.now());
                    this.userRepository.saveAndFlush(user);
                    return user;
                });
    }
    public Optional<User> finishcode(String key,String code ) {
        //log.debug("Reset user password for reset key {}", key);
        return userRepository.findOneByResetKey(key)
                .filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400)))
                .map(user -> {
                    user.setCodeverification(code);
                    user.setResetKey(null);
                    user.setResetDate(null);
                    this.userRepository.saveAndFlush(user);
                    return user;
                });
    }

    public Optional<User> completePasswordReset(String newPassword, String key) {
         //log.debug("Reset user password for reset key {}", key);
        return userRepository.findOneByResetKey(key)
                .filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400)))
                .map(user -> {
                    user.setpassword(passwordEncoder.encode(newPassword));
                    user.setResetKey(null);
                    user.setResetDate(null);
                    this.userRepository.saveAndFlush(user);
                    return user;
                });
    }


}
