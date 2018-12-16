package it.zan.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import it.zan.model.User;
import it.zan.service.UserService;
import it.zan.view.UserListView;
import it.zan.view.UserView;

@Controller
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user")
	public ResponseEntity<?> save(@RequestBody User user) {
		int id = userService.save(user);
		return ResponseEntity.ok().body("Utente inserito correttamente con id:" + id);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<UserView> get(@PathVariable("id") int id) {
		User user = userService.get(id);
		UserView userView = buildUserViewFromEntity(user);
		return ResponseEntity.ok().body(userView);
	}
	
	@GetMapping("/user")
	public ResponseEntity<UserListView> list() {
		List<User> users = userService.list();
		UserListView userListView = new UserListView();
		for(User user : users) {
			UserView userView = buildUserViewFromEntity(user);
			userListView.getUserList().add(userView);
		}
		return ResponseEntity.ok().body(userListView);
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody User user) {
		userService.update(id, user);
		return ResponseEntity.ok().body("Dati utente aggiornati correttamente");
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		userService.delete(id);
		return ResponseEntity.ok().body("Utente eliminato correttamente");
	}
	
	// Trasforma l'entity in una view
	private UserView buildUserViewFromEntity(User userEntity) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		UserView userView = new UserView();
		userView.setId(userEntity.getId());
		userView.setNome(userEntity.getFirstName());
		userView.setCognome(userEntity.getLastName());
		if(userEntity.getDateOfBirth() != null) {
			userView.setDataNascita(sdf.format(userEntity.getDateOfBirth()));
		}
		return userView;
		
	}

}
