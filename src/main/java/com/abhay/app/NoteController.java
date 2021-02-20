package com.abhay.app;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class NoteController {

	@Autowired
	private NotesRespository notesRespository;

	@GetMapping("/")
	public String index(Model model) {
		getAllNotes(model);
		return "index";
	}

	private void getAllNotes(Model model) {
		List<Note> findAll = notesRespository.findAll();
		Collections.reverse(findAll);
		model.addAttribute("notes", findAll);
	}

	private void saveNotes(String description, Model model) {
		notesRespository.save(new Note(null, description.trim()));
		model.addAttribute("description", "");
	}

	@PostMapping("/note")
	public String saveNotes(@RequestParam("image") MultipartFile file, @RequestParam String description,
			@RequestParam(required = false) String publish, @RequestParam(required = false) String upload,
			Model model) {

		if (publish != null && publish.equals("Publish")) {
			saveNotes(description, model);
			getAllNotes(model);
			return "redirect:/";
		}
		return "index";
	}
}
