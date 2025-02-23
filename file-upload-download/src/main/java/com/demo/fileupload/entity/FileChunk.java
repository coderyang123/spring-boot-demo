package com.demo.fileupload.entity;

/**
 * 文件分片实体类
 *
 * @author yueyang
 * @since 2025-02-23 12:50:00
 */
public class FileChunk {
  private String originalFilename;
  private int chunkNumber;
  private int totalChunks;
  private String identifier;

  public FileChunk() {}

  public FileChunk(String originalFilename, int chunkNumber, int totalChunks, String identifier) {
    this.originalFilename = originalFilename;
    this.chunkNumber = chunkNumber;
    this.totalChunks = totalChunks;
    this.identifier = identifier;
  }

  public String getOriginalFilename() {
    return originalFilename;
  }

  public void setOriginalFilename(String originalFilename) {
    this.originalFilename = originalFilename;
  }

  public int getChunkNumber() {
    return chunkNumber;
  }

  public void setChunkNumber(int chunkNumber) {
    this.chunkNumber = chunkNumber;
  }

  public int getTotalChunks() {
    return totalChunks;
  }

  public void setTotalChunks(int totalChunks) {
    this.totalChunks = totalChunks;
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }
}
