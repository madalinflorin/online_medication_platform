package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.SecurityConfig.jwt.JwtUtils;
import ro.tuc.ds2020.SecurityConfig.services.UserDetailsImpl;
import ro.tuc.ds2020.entities.*;
import ro.tuc.ds2020.payload.request.LoginRequest;
import ro.tuc.ds2020.payload.request.SignupRequest;
import ro.tuc.ds2020.payload.response.JwtResponse;
import ro.tuc.ds2020.payload.response.MessageResponse;
import ro.tuc.ds2020.repositories.CaregiverRepository;
import ro.tuc.ds2020.repositories.PatientRepository;
import ro.tuc.ds2020.repositories.RoleRepository;
import ro.tuc.ds2020.repositories.UserRepository;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")

public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	CaregiverRepository caregiveerRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());


		return ResponseEntity.ok(new JwtResponse(jwt,
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_PATIENT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "doctor":
					Role adminRole = roleRepository.findByName(ERole.ROLE_DOCTOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "caregiver":
					Role modRole = roleRepository.findByName(ERole.ROLE_CAREGIVER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);



					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_PATIENT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);

				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);


		Role modRole = roleRepository.findByName(ERole.ROLE_CAREGIVER).get();
		Role userRole = roleRepository.findByName(ERole.ROLE_PATIENT).get();

		if(roles.contains(modRole)) {
			Caregiver caregiver = new Caregiver(signUpRequest.getName(),signUpRequest.getBirthDate(),signUpRequest.getGender(),signUpRequest.getAddress(),user.getId());


			caregiveerRepository.save(caregiver);
		}

		if(roles.contains(userRole)) {
			Patient patient;
			if(signUpRequest.getMedical_record()!=null)
			patient = new Patient(signUpRequest.getName(),signUpRequest.getBirthDate(),signUpRequest.getGender(),signUpRequest.getAddress(),signUpRequest.getMedical_record(),user.getId());

			else {
				patient = new Patient(signUpRequest.getName(),signUpRequest.getBirthDate(),signUpRequest.getGender(),signUpRequest.getAddress(),"",user.getId());
			}

			patientRepository.save(patient);
		}

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
