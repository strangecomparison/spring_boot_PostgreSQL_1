package ru.vasseugs.spring_boot_postgresql_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vasseugs.spring_boot_postgresql_1.entities.GuitarEntity;
import ru.vasseugs.spring_boot_postgresql_1.entities.GuitarModelEntity;
import ru.vasseugs.spring_boot_postgresql_1.entities.ManufacturerEntity;
import ru.vasseugs.spring_boot_postgresql_1.models.GuitarModel;
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

    @GetMapping()
    public String getAllGuitars(Model model) {
        model.addAttribute("guitars", guitarService.getAllGuitars());
        return "guitars";
    }

    // страница для создания новой гитары
    @GetMapping("/new")
    public String createNewGuitar(Model model) {
        model.addAttribute("guitar", new GuitarModel());
        return "new";
    }


    @PostMapping()
    public String saveNewGuitar(@ModelAttribute("guitar") @Valid GuitarModel guitarModel,
                                BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "new";
        }

        GuitarEntity guitarEntity = new GuitarEntity();

        guitarService.save(guitarEntity);

        return "redirect:/guitars";
    }









}
