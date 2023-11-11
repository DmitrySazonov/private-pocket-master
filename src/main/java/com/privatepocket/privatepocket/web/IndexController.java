package com.privatepocket.privatepocket.web;

import com.privatepocket.privatepocket.storage.RecordStorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.Map;

@Controller
public class IndexController {

    private final RecordStorageService service;

    public IndexController(RecordStorageService service) {
        this.service = service;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String post(@RequestParam(required = false) String name) {
        if (name != null) {
            return "redirect:/" + name;
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/{pocket}", method = RequestMethod.GET)
    public String pocket(@PathVariable String pocket, Model model) {
        model.addAttribute("name", pocket);
        model.addAttribute("records", service.getAllRecordsByRepository(pocket));
        return "index";
    }

    @RequestMapping(value = "/{pocket}/{id}", method = RequestMethod.GET)
    public View pocketDelete(@PathVariable String pocket, @PathVariable String id, Model model) {
        service.deleteRecord(id);
        return new RedirectView("/{pocket}");
    }

    @RequestMapping(value = "/{pocket}", method = RequestMethod.POST)
    public View pocketAdd(@PathVariable String pocket, @RequestParam String url, Model model) throws IOException {
        service.storeUrl(pocket, url);
        return new RedirectView("/{pocket}");
    }

    @ResponseBody
    @RequestMapping(path = "/private/pocket-admin", method = RequestMethod.GET)
    public Map<String, Long> getAll() {
        return service.getAllPockets();
    }

}
