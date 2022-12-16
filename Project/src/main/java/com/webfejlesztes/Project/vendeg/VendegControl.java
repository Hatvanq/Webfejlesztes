package com.webfejlesztes.Project.vendeg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class VendegControl {
    @Autowired private VendegServ service;

    @GetMapping("/vendegek")
    public  String showVendegList(Model model) {
        List<Vendeg> listVendegek = service.listAll();
        model.addAttribute("listVendegek", listVendegek);
        return "vendegek";
    }
    @GetMapping("/vendegek/uj")
    public String showNewForm(Model model) {
        model.addAttribute("vendeg", new Vendeg());
        return "vendeg_form";
    }
    @PostMapping("/vendegek/mentes")
    public String saveVendeg(Vendeg vendeg) {
        service.save(vendeg);

        return "redirect:/vendegek";
    }
    @GetMapping("/vendegek/szerkesztes/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        try {
            Vendeg vendeg = service.get(id);
            model.addAttribute("vendeg", vendeg);
            return "vendeg_form";
        } catch (UserNotFoundExeption e) {
            e.printStackTrace();
            return "redirect:/vendegek";
        }
    }
    @GetMapping("/vendegek/torles/{id}")
    public String deleteVendeg(@PathVariable("id") Integer id) {
        try {
            service.delete(id);
        } catch (UserNotFoundExeption e) {
            e.printStackTrace();
        }
        return "redirect:/vendegek";

    }


}
