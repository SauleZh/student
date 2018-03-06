package com.example.student.controllers;

import com.example.student.models.Uchitel;
import com.example.student.reposotories.UchitelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/demo")

public class UchitelController {@Autowired
private UchitelRepository uchitelRepository ;
    private long a;

    @RequestMapping("/add")
    public String showForm(Model model){
        model.addAttribute("uchitel",new Uchitel());
        return "uchitel_add_form";
    }

    @RequestMapping("/addd")
    public String showFormm(Model model){
        model.addAttribute("uchitel",new Uchitel());
        return "update";
    }
    @PostMapping("/add")
    public String addUchitel(@ModelAttribute Uchitel uchitel){
        uchitelRepository.save(uchitel) ;

        return "redirect:/demo/all" ;
    }


    @GetMapping("/all2")
    public @ResponseBody Iterable<Uchitel> allUchitels(){
        return uchitelRepository.findAll() ;
    }

    @GetMapping("/all")
    public String allUchitel2(Model model){
        List<Uchitel> uchitels = (List<Uchitel>) uchitelRepository.findAll();
        model.addAttribute("uchitels", allUchitels()) ;
        return "uchitels" ;
    }

    @PostMapping("/adds")
    public String addsUchitel(@ModelAttribute Uchitel uchitel){
        Uchitel uchitel1 = new Uchitel();
        uchitel1.setId(a);
        uchitel1.setFirstName(uchitel.getFirstName());
        uchitel1.setLastName(uchitel.getLastName());
        uchitel1.setEmail(uchitel.getEmail());
        uchitelRepository.save(uchitel1) ;

        return "redirect:/demo/all" ;
    }

    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public ModelAndView updateUchitels(Model model,@RequestParam("id") long id){
        a=id;
        Optional<Uchitel> uchitel = (Optional <Uchitel> ) uchitelRepository.findById(id);
        model.addAttribute("uchitell",uchitel);
        return new ModelAndView("update");
    }
    @RequestMapping(value = "/deleteContact",method = RequestMethod.GET)
    public ModelAndView deleteContact(@RequestParam("id") long idd){
        uchitelRepository.deleteById(idd);
        return new ModelAndView("redirect:/demo/all");
    }
}
