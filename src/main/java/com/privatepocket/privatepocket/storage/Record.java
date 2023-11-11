package com.privatepocket.privatepocket.storage;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "records")
public class Record {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  private String repository;
  private RecordType recordType;
  @Lob
  private String url;
  private String title;
  private LocalDateTime createDate;

  //for file storage
  private String fileType;
  private String fileName;
  @Lob
  private byte[] data;

  public Record(String repository, RecordType recordType, String url, String title, LocalDateTime createDate, String fileType, String fileName, byte[] data) {
    this.repository = repository;
    this.recordType = recordType;
    this.url = url;
    this.title = title;
    this.createDate = createDate;
    this.fileType = fileType;
    this.fileName = fileName;
    this.data = data;
  }

  public Record() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getRepository() {
    return repository;
  }

  public void setRepository(String repository) {
    this.repository = repository;
  }

  public RecordType getRecordType() {
    return recordType;
  }

  public void setRecordType(RecordType recordType) {
    this.recordType = recordType;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public void setCreateDate(LocalDateTime createDate) {
    this.createDate = createDate;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }
}
