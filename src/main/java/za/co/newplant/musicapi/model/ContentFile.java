package za.co.newplant.musicapi.model;

import za.co.newplant.musicapi.contstant.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="content_file")
public class ContentFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="content_id")
    private long contentId ;
    @Column(name="song_name")
    private String songName ;
    @Column(name="producer_name")
    private String producerName;
    @Column(name="artists_names")
    private String artistsNames;
    @Column(name="release_date")
    private LocalDateTime releaseDate;
    @Column(name="location")
    private String location;
    @Column(name="location_profile")
    private String locationProfile;
    @Column(name="location_artwork")
    private String locationArtwork;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name="type")
    private String type;

    public ContentFile() {
    }


    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }


    public String getArtistsNames() {
        return artistsNames;
    }

    public void setArtistsNames(String artistsNames) {
        this.artistsNames = artistsNames;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public ContentFile(String songName, String producerName, String artistsNames, LocalDateTime releaseDate, String location, String locationProfile, String locationArtwork, Status status, String type) {
        this.songName = songName;
        this.producerName = producerName;
        this.artistsNames = artistsNames;
        this.releaseDate = releaseDate;
        this.location = location;
        this.locationProfile = locationProfile;
        this.locationArtwork = locationArtwork;
        this.status = status;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContentFile)) return false;
        ContentFile that = (ContentFile) o;
        return getContentId() == that.getContentId() &&
                getSongName().equals(that.getSongName()) &&
                getArtistsNames().equals(that.getArtistsNames()) &&
                getType().equals(that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContentId(), getSongName(), getArtistsNames(), getType());
    }


    @Override
    public String toString() {
        return "ContentFile{" +
                "contentId=" + contentId +
                ", songName='" + songName + '\'' +
                ", producerName='" + producerName + '\'' +
                ", artistsNames='" + artistsNames + '\'' +
                ", releaseDate=" + releaseDate +
                ", location='" + location + '\'' +
                ", locationProfile='" + locationProfile + '\'' +
                ", locationArtwork='" + locationArtwork + '\'' +
                ", status=" + status +
                ", type='" + type + '\'' +
                '}';
    }
}
