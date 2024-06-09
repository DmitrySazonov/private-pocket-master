package com.privatepocket.privatepocket.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.privatepocket.privatepocket.storage.Record;
import com.privatepocket.privatepocket.storage.RecordStorageService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @RequestMapping(value = "/{pocket}/{id}", method = RequestMethod.POST)
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

    @RequestMapping(path = "/private/export", method = RequestMethod.GET)
    public ResponseEntity<Resource> exportRecords() throws IOException {
        List<Record> records = service.getAllRecords();


        List<UrlRecordDTO> urlRecords =  records.stream()
                .map(record -> new UrlRecordDTO(
                        record.getRepository(),
                        record.getUrl(),
                        record.getTitle(),
                        record.getCreateDate()))
                .collect(Collectors.toList());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        String filename = "private_pocket_" + formattedDateTime + ".json";

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String jsonContent = objectMapper.writeValueAsString(urlRecords);
        ByteArrayResource resource = new ByteArrayResource(jsonContent.getBytes());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }

}
