package ru.vasseugs.spring_boot_postgresql_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.vasseugs.spring_boot_postgresql_1.dto.GuitarDTO;
import ru.vasseugs.spring_boot_postgresql_1.service.GuitarService;
import javax.validation.Valid;
import java.io.IOException;

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
    public String saveNewGuitar(@RequestParam(name="manufacturer") String manufacturer,
                                @RequestParam(name="model") String guitarModel,
                                @RequestParam(name="country") String country,
                                @RequestParam(name="year") String year,
                                @RequestParam(name="guitar_img") MultipartFile multipartFile,
                                Model model) {


        try {
            guitarService.save(manufacturer,
                    guitarModel,
                    country,
                    year,
                    multipartFile);    // passing model to hide realization
            model.addAttribute("guitars", guitarService.getAllGuitars());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "guitars";
    }

    // showing the guitar
    @GetMapping("/{id}")
    public String showGuitar(@PathVariable("id") long id, Model model) {
        // returning DTO because it inclues image of a guitar
        // converted to a string
        model.addAttribute("guitarDTO", guitarService.showGuitar(id));
        return "show";
    }

    // a method to find guitar by id
    // handles the request param and concatenates it to url as PathVariable
    @GetMapping("/find")
    public String findById(@RequestParam(name = "id") String param) {
        return "redirect:/guitars/" + param;        // redirects to /guitars/{id} = showGuitar() method
    }


    @DeleteMapping("/{id}")
    public String deleteGuitar(@PathVariable("id") long id) {
        guitarService.deleteGuitar(id);
        return "redirect:/guitars";
    }









}
