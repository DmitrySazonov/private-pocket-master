package com.privatepocket.privatepocket.storage;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.LocalTime.now;

@Service
public class RecordStorageService {

    @Autowired
    private RecordRepository recordRepository;

    public Record storeFile(String repository, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Record record = new Record(repository, RecordType.FILE, null, null, java.time.LocalDateTime.now(), fileName, file.getContentType(), file.getBytes());
        return recordRepository.save(record);
    }

    public Record storeUrl(String repository, String url) throws IOException {
        Record record = new Record(repository, RecordType.URL, url, getTitleFromUrl(url), java.time.LocalDateTime.now(), null, null, null);
        return recordRepository.save(record);
    }

    public Record getRecord(String id) {
        return recordRepository.findById(id).get();
    }

    public List<Record> getAllRecordsByRepository(String repository) {
        return recordRepository.findByRepositoryOrderByCreateDate(repository);
    }

    public void deleteRecord(String id) {
        recordRepository.deleteById(id);
    }

    public Map<String, Long> getAllPockets() {
        Map<String, Long> pocketsCountMap = recordRepository.findAll().stream().collect(Collectors.groupingBy(Record::getRepository, Collectors.counting()));
        return pocketsCountMap;
    }

    public List<Record> getAllRecords() {
        return recordRepository.findAllByRecordTypeOrderByRepositoryAscCreateDateAsc(RecordType.URL);
    }

    private String getTitleFromUrl(String url) throws IOException {
        try {
            if (isValidURL(url)) {
                Document document = Jsoup.connect(url).get();
                return document.title();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    boolean isValidURL(String url) throws MalformedURLException {
        UrlValidator validator = new UrlValidator();
        return validator.isValid(url);
    }
}
