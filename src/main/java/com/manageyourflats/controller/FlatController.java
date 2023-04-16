package com.manageyourflats.controller;

import com.manageyourflats.model.Flat;
import com.manageyourflats.repositories.FlatRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/flat")
public class FlatController {

    private final FlatRepository flatRepository;

    public FlatController(FlatRepository flatRepository) {
        this.flatRepository = flatRepository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addFlat(Model model) {
        model.addAttribute("flat", new Flat());
        return "flat/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(Flat flat, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "flat/add";
        }
        flatRepository.save(flat);

        return "redirect:/flat/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("flats", flatRepository.findAll());
        return "flat/list";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editBook(@RequestParam Long id, Model model) {
        model.addAttribute("flat", flatRepository.findById(id));
        return "flat/edit";
    }
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(Flat flat, Model model) {
        flatRepository.save(flat);
        //    flatRepository.save(flat);

        return "redirect:/flat/list";
    }

    //    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
//    public String confirm(@RequestParam Long id, Model model) {
//        model.addAttribute("id", id);
//        return "confirm";
//    }
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String remove(@RequestParam Long id) {
        flatRepository.deleteById(id);
        return "redirect:/flat/list";
    }


}
