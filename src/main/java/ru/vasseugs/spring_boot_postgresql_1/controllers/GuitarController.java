package ru.vasseugs.spring_boot_postgresql_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vasseugs.spring_boot_postgresql_1.dto.GuitarDTO;
import ru.vasseugs.spring_boot_postgresql_1.service.GuitarService;
import javax.validation.Valid;

@Controller
@RequestMapping("/guitars")
public class GuitarController {

    private final GuitarService guitarService;

    @Autowired
    public GuitarController(GuitarService guitarService) {
        this.guitarService = guitarService;
    }

    // showing all guitars
    @GetMapping()
    public String getAllGuitars(Model model) {
        model.addAttribute("guitars", guitarService.getAllGuitars());
        return "guitars";
    }

    // creating new guitar
    @GetMapping("/new")
    public String createNewGuitar(Model model) {
        model.addAttribute("guitar", new GuitarDTO());
        return "new";
    }

    // saving new guitar
    @PostMapping()
    public String saveNewGuitar(@ModelAttribute("guitar") @Valid GuitarDTO guitarDTO,
                                BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "new";
        }

        guitarService.save(guitarDTO);    // passing model to hide realization

        return "redirect:/guitars";
    }









}
