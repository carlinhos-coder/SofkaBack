package co.com.sofka.appPrueba.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.sofka.appPrueba.models.UserModel;
import co.com.sofka.appPrueba.services.UserService;


@RestController
//@CrossOrigin(origins = "http://localhost:3000" )
@RequestMapping("/api/usuarios")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping
	public ResponseEntity<UserModel> createUser(@RequestBody UserModel user) {
		return new ResponseEntity<UserModel>(userService.save(user), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<UserModel>> FindAll() {
		return ResponseEntity.ok(this.userService.listUsers());
	}

	@PostMapping(path = "/update")
	public ResponseEntity<UserModel> updateUser(@RequestBody UserModel userModel) {
		if (userModel == null) {
			return new ResponseEntity<UserModel>(HttpStatus.NOT_ACCEPTABLE);
		}

		UserModel user = this.userService.findById(userModel.getId());
		if (user == null) {
			return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
		} else {
			System.out.println("");
			return new ResponseEntity<UserModel>(userService.save(userModel), HttpStatus.CREATED);
		}
	}

	@GetMapping(path = "/validateName")
	public ResponseEntity<UserModel> validate(@RequestParam(value = "name") String name) {
		UserModel userModel=this.userService.findNameValidate(name);
		if (userModel== null) {
			throw new IllegalArgumentException("\"error message the field is null\"");
		}else {
			return ResponseEntity.ok(userService.findNameValidate(name));
		}
		
	}

	@DeleteMapping
	public ResponseEntity<String> removeUser(@RequestParam("id") int id) {
		UserModel userModel = this.userService.findById(id);
		if (userModel != null) {
			this.userService.deleteUser(userModel);
			return new ResponseEntity<String>(HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
		}
	}

}
