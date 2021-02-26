package za.co.newplant.musicapi.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Model for listing Music content
 */
@Schema(description = "Model for listing Music content")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-02-17T13:34:46.401Z[GMT]")


public class Content {
    @JsonProperty("id")
    private long id ;

    @JsonProperty("songName")
    private String songName = null;

    @JsonProperty("producerName")
    private String producerName = null;

    @JsonProperty("artwork")
    private Resource artwork = null;
    @JsonProperty("artworkBytes")
    private byte[] artworkBytes = null;

    @JsonProperty("artistsNames")
    private String artistsNames = null;

    @JsonProperty("releaseDate")
    private String releaseDate = null;

    @JsonProperty("profileImage")
    private Resource profileImage = null;
    @JsonProperty("profileImageBytes")
    private byte[] profileImageBytes = null;

    @JsonProperty("fileContent")
    private Resource fileContent = null;
    @JsonProperty("fileContentBytes")
    private byte[] fileContentBytes = null;

    @JsonIgnore
    private String locationContent;
    @JsonIgnore
    private String locationProfile;
    @JsonIgnore
    private String locationArtwork;

    public Content id(Integer id) {
        this.id = id;
        return this;
    }

    public byte[] getProfileImageBytes() {
        return profileImageBytes;
    }

    public void setProfileImageBytes(byte[] profileImageBytes) {
        this.profileImageBytes = profileImageBytes;
    }

    public byte[] getFileContentBytes() {
        return fileContentBytes;
    }

    public void setFileContentBytes(byte[] fileContentBytes) {
        this.fileContentBytes = fileContentBytes;
    }

    public byte[] getArtworkBytes() {
        return artworkBytes;
    }

    public void setArtworkBytes(byte[] artworkBytes) {
        this.artworkBytes = artworkBytes;
    }

    /**
     * The ID for music content
     *
     * @return id
     **/
    @Schema(example = "123", description = "The ID for music content")

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Content songName(String songName) {
        this.songName = songName;
        return this;
    }

    /**
     * Name of the song
     *
     * @return songName
     **/
    @Schema(example = "The hit one", description = "Name of the song")

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Content producerName(String producerName) {
        this.producerName = producerName;
        return this;
    }

    /**
     * Name of the song author and producer
     *
     * @return producerName
     **/
    @Schema(example = "DJ john Doe", description = "Name of the song author and producer")

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public Content artwork(Resource artwork) {
        this.artwork = artwork;
        return this;
    }

    /**
     * Artwork of the song
     *
     * @return artwork
     **/
    @Schema(example = "Artwork", description = "Artwork of the song")

    public Resource getArtwork() {
        return artwork;
    }

    public void setArtwork(Resource artwork) {
        this.artwork = artwork;
    }

    public Content artistsNames(String artistsNames) {
        this.artistsNames = artistsNames;
        return this;
    }

    /**
     * artists of the song
     *
     * @return artistsNames
     **/
    @Schema(example = "Dj Name ft singer", description = "artists of the song")

    public String getArtistsNames() {
        return artistsNames;
    }

    public void setArtistsNames(String artistsNames) {
        this.artistsNames = artistsNames;
    }

    public Content releaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    /**
     * Release date of the song
     *
     * @return releaseDate
     **/
    @Schema(example = "2021-02-14", description = "Release date of the song")

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Content profileImage(Resource profileImage) {
        this.profileImage = profileImage;
        return this;
    }

    /**
     * Profile Image
     *
     * @return profileImage
     **/
    @Schema(description = "Profile Image")

    @Valid
    public Resource getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Resource profileImage) {
        this.profileImage = profileImage;
    }

    public Content fileContent(Resource fileContent) {
        this.fileContent = fileContent;
        return this;
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

    /**
     * the actual file to be uploaded into the server
     *
     * @return fileContent
     **/
    @Schema(description = "the actual file to be uploaded into the server")

    @Valid
    public Resource getFileContent() {
        return fileContent;
    }

    public void setFileContent(Resource fileContent) {
        this.fileContent = fileContent;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Content content = (Content) o;
        return Objects.equals(this.id, content.id) &&
                Objects.equals(this.songName, content.songName) &&
                Objects.equals(this.producerName, content.producerName) &&
                Objects.equals(this.artwork, content.artwork) &&
                Objects.equals(this.artistsNames, content.artistsNames) &&
                Objects.equals(this.releaseDate, content.releaseDate) &&
                Objects.equals(this.profileImage, content.profileImage) &&
                Objects.equals(this.fileContent, content.fileContent) &&
                Objects.equals(this.profileImageBytes, content.profileImageBytes) &&
                Objects.equals(this.fileContentBytes, content.fileContentBytes) &&
                Objects.equals(this.artworkBytes, content.artworkBytes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, songName, producerName, artwork, artistsNames, releaseDate, profileImage,
                fileContent, profileImageBytes, fileContentBytes, artworkBytes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Content {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    songName: ").append(toIndentedString(songName)).append("\n");
        sb.append("    producerName: ").append(toIndentedString(producerName)).append("\n");
        sb.append("    artwork: ").append(toIndentedString(artwork)).append("\n");
        sb.append("    artistsNames: ").append(toIndentedString(artistsNames)).append("\n");
        sb.append("    releaseDate: ").append(toIndentedString(releaseDate)).append("\n");
        sb.append("    profileImage: ").append(toIndentedString(profileImage)).append("\n");
        sb.append("    fileContent: ").append(toIndentedString(fileContent)).append("\n");
        sb.append("    profileImageBytes: ").append(toIndentedString(profileImageBytes)).append("\n");
        sb.append("    fileContentBytes: ").append(toIndentedString(fileContentBytes)).append("\n");
        sb.append("    artworkBytes: ").append(toIndentedString(artworkBytes)).append("\n");
        sb.append("}");
        return sb.toString();
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
