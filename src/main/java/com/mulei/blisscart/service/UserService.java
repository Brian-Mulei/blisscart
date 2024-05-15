package com.mulei.blisscart.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mulei.blisscart.model.User;
import com.mulei.blisscart.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService  implements UserDetailsService {

   private final UserRepository userRepository;

    private final static String USER_NOT_FOUND_MSG =
    "user with email %s not found";
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return userRepository.findByEmail(email)
        .orElseThrow(() ->
                new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG, email)));
 
    }

    public String signUpUser(User user) {
        boolean userExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }

        // String encodedPassword = bCryptPasswordEncoder
        //         .encode(appUser.getPassword());

        // appUser.setPassword(encodedPassword);

        // appUserRepository.save(appUser);

        // String token = UUID.randomUUID().toString();

        // ConfirmationToken confirmationToken = new ConfirmationToken(
        //         token,
        //         LocalDateTime.now(),
        //         LocalDateTime.now().plusMinutes(15),
        //         appUser
        // );

        // confirmationTokenService.saveConfirmationToken(
        //         confirmationToken);

//        TODO: SEND EMAIL

      //  return token;
      return  null;
    }

    public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }

}
