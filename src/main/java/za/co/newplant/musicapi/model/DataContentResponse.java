package za.co.newplant.musicapi.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * DataContentResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-02-17T13:34:46.401Z[GMT]")


public class DataContentResponse   {
  @JsonProperty("contentId")
  private Long contentId = null;

  @JsonProperty("songName")
  private String songName = null;

  @JsonProperty("producerName")
  private String producerName = null;



  @JsonProperty("artistsNames")
  private String artistsNames = null;

  @JsonProperty("releaseDate")
  private String releaseDate = null;

  @JsonProperty("profileImageUrl")
  private String profileImageUrl = null;

  @JsonProperty("fileContentUrl")
  private String fileContentUrl = null;

  @JsonProperty("artworkUrl")
  private String artworkUrl = null;

  @JsonProperty("status")
  private String status = null;
  @JsonIgnore
  private String locationContent;
  @JsonIgnore
  private String locationProfile;
  @JsonIgnore
  private String locationArtwork;

  public DataContentResponse() {
  }

  public DataContentResponse contentId(Long contentId) {
    this.contentId = contentId;
    return this;
  }

  public DataContentResponse(Long contentId, String songName, String producerName, String artistsNames,
                             String releaseDate, String profileImageUrl, String fileContentUrl, String artworkUrl, String status) {
    this.contentId = contentId;
    this.songName = songName;
    this.producerName = producerName;
    this.artistsNames = artistsNames;
    this.releaseDate = releaseDate;
    this.profileImageUrl = profileImageUrl;
    this.fileContentUrl = fileContentUrl;
    this.artworkUrl = artworkUrl;
    this.status = status;
  }

  public DataContentResponse(Long contentId, String songName, String producerName, String artistsNames, String releaseDate,
                             String profileImageUrl, String fileContentUrl, String artworkUrl, String status,
                             String locationContent, String locationProfile, String locationArtwork) {
    this.contentId = contentId;
    this.songName = songName;
    this.producerName = producerName;
    this.artistsNames = artistsNames;
    this.releaseDate = releaseDate;
    this.profileImageUrl = profileImageUrl;
    this.fileContentUrl = fileContentUrl;
    this.artworkUrl = artworkUrl;
    this.status = status;
    this.locationContent = locationContent;
    this.locationProfile = locationProfile;
    this.locationArtwork = locationArtwork;
  }

  public String getArtworkUrl() {
    return artworkUrl;
  }

  public void setArtworkUrl(String artworkUrl) {
    this.artworkUrl = artworkUrl;
  }

  /**
   * Uploaded content ID to be used to create a link for pulling the uploaded details
   * @return contentId
   **/
  @Schema(example = "123", description = "Uploaded content ID to be used to create a link for pulling the uploaded details")
  
    public Long getContentId() {
    return contentId;
  }

  public void setContentId(Long contentId) {
    this.contentId = contentId;
  }

  public DataContentResponse songName(String songName) {
    this.songName = songName;
    return this;
  }

  /**
   * Name of the song
   * @return songName
   **/
  @Schema(example = "The hit one", description = "Name of the song")
  
    public String getSongName() {
    return songName;
  }

  public void setSongName(String songName) {
    this.songName = songName;
  }

  public DataContentResponse producerName(String producerName) {
    this.producerName = producerName;
    return this;
  }

  /**
   * Name of the song author and producer
   * @return producerName
   **/
  @Schema(example = "DJ john Doe", description = "Name of the song author and producer")
  
    public String getProducerName() {
    return producerName;
  }

  public void setProducerName(String producerName) {
    this.producerName = producerName;
  }



  public DataContentResponse artistsNames(String artistsNames) {
    this.artistsNames = artistsNames;
    return this;
  }

  /**
   * artists of the song
   * @return artistsNames
   **/
  @Schema(example = "Dj Name ft singer", description = "artists of the song")
  
    public String getArtistsNames() {
    return artistsNames;
  }

  public void setArtistsNames(String artistsNames) {
    this.artistsNames = artistsNames;
  }

  public DataContentResponse releaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
    return this;
  }

  /**
   * Release date of the song
   * @return releaseDate
   **/
  @Schema(example = "2021-02-14", description = "Release date of the song")
  
    public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public DataContentResponse profileImageUrl(String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
    return this;
  }

  /**
   * Profile Image Url for downloadind
   * @return profileImageUrl
   **/
  @Schema(example = "http://localhost:8089/prod/music/profileImage/123", description = "Profile Image Url for downloadind")
  
    public String getProfileImageUrl() {
    return profileImageUrl;
  }

  public void setProfileImageUrl(String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
  }

  public DataContentResponse fileContentUrl(String fileContentUrl) {
    this.fileContentUrl = fileContentUrl;
    return this;
  }

  /**
   * the actual file - music content to be downloaded from the server
   * @return fileContentUrl
   **/
  @Schema(example = "http://localhost:8089/prod/music/musicfile/123", description = "the actual file - music content to be downloaded from the server")
  
    public String getFileContentUrl() {
    return fileContentUrl;
  }

  public void setFileContentUrl(String fileContentUrl) {
    this.fileContentUrl = fileContentUrl;
  }

  public DataContentResponse status(String status) {
    this.status = status;
    return this;
  }

  /**
   * The result of the upload: * `UPLOADED` - The content has uploaded for this request * `UPDATED` - Content error occurred * `REJECTED` - Content error occurred 
   * @return status
   **/
  @Schema(example = "UPLOADED", description = "The result of the upload: * `UPLOADED` - The content has uploaded for this request * `UPDATED` - Content error occurred * `REJECTED` - Content error occurred ")
  
    public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getLocationContent() {
    return locationContent;
  }

  public void setLocationContent(String locationContent) {
    this.locationContent = locationContent;
  }

  public String getLocationProfile() {
    return locationProfile;
  }

  public void setLocationProfile(String locationProfile) {
    this.locationProfile = locationProfile;
  }

  public String getLocationArtwork() {
    return locationArtwork;
  }

  public void setLocationArtwork(String locationArtwork) {
    this.locationArtwork = locationArtwork;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DataContentResponse dataContentResponse = (DataContentResponse) o;
    return Objects.equals(this.contentId, dataContentResponse.contentId) &&
        Objects.equals(this.songName, dataContentResponse.songName) &&
        Objects.equals(this.producerName, dataContentResponse.producerName) &&
        Objects.equals(this.artistsNames, dataContentResponse.artistsNames) &&
        Objects.equals(this.releaseDate, dataContentResponse.releaseDate) &&
        Objects.equals(this.profileImageUrl, dataContentResponse.profileImageUrl) &&
        Objects.equals(this.fileContentUrl, dataContentResponse.fileContentUrl) &&
        Objects.equals(this.status, dataContentResponse.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contentId, songName, producerName, artistsNames, releaseDate, profileImageUrl, fileContentUrl, status);
  }

  @Override
  public String toString() {
    return "DataContentResponse{" +
            "contentId=" + contentId +
            ", songName='" + songName + '\'' +
            ", producerName='" + producerName + '\'' +
            ", artistsNames='" + artistsNames + '\'' +
            ", releaseDate='" + releaseDate + '\'' +
            ", profileImageUrl='" + profileImageUrl + '\'' +
            ", fileContentUrl='" + fileContentUrl + '\'' +
            ", artworkUrl='" + artworkUrl + '\'' +
            ", status='" + status + '\'' +
            ", locationContent='" + locationContent + '\'' +
            ", locationProfile='" + locationProfile + '\'' +
            ", locationArtwork='" + locationArtwork + '\'' +
            '}';
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
