package za.co.newplant.musicapi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import za.co.newplant.musicapi.contstant.Status;
import za.co.newplant.musicapi.model.ContentFile;
import za.co.newplant.musicapi.model.ContentSpecification;
import za.co.newplant.musicapi.repository.ContentFileRepository;
import za.co.newplant.musicapi.util.SearchOperation;
import za.co.newplant.musicapi.util.SpecSearchCriteria;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIn.isIn;
import static org.hamcrest.core.IsNot.not;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JPASpecificationIntegrationTest {

    @Autowired
    ContentFileRepository contentFileRepository;

    private ContentFile contentFile;
    private ContentFile contentFileData;

    @Before
    public void init() {
        contentFile = new ContentFile();
        contentFile.setContentId(1);
        contentFile.setSongName("Mashegana");
        contentFile.setProducerName("john");
        contentFile.setArtistsNames("doe");
        contentFile.setReleaseDate(LocalDateTime.now());
        contentFile.setLocation("");
        contentFile.setLocationArtwork("");
        contentFile.setLocationProfile("");
        contentFile.setStatus(Status.UPLOADED);
        contentFile.setType("png");
        contentFileRepository.save(contentFile);

        contentFileData = new ContentFile();
        contentFileData.setContentId(2);
        contentFileData.setSongName("tom");
        contentFileData.setProducerName("kent");
        contentFileData.setArtistsNames("doe");
        contentFileData.setReleaseDate(LocalDateTime.now());
        contentFileData.setLocation("");
        contentFileData.setLocationArtwork("");
        contentFileData.setLocationProfile("");
        contentFileData.setStatus(Status.UPLOADED);
        contentFileData.setType("png");
        contentFileRepository.save(contentFileData);

    }



    @Test
    public void givenSongAndArtistName() {
        final ContentSpecification spec = new ContentSpecification(new SpecSearchCriteria("songName", SearchOperation.EQUALITY, "Mashegana"));
        final ContentSpecification spec1 = new ContentSpecification(new SpecSearchCriteria("artistsNames", SearchOperation.EQUALITY, "doe"));
        final List<ContentFile> results = contentFileRepository.findAll(Specification
                .where(spec)
                .and(spec1));

        assertThat(contentFile, isIn(results));

    }

    @Test
    public void givenSongNameInverse() {
        final ContentSpecification spec = new ContentSpecification(new SpecSearchCriteria("songName", SearchOperation.NEGATION, "Mashegana"));
        final List<ContentFile> results = contentFileRepository.findAll(Specification.where(spec));

        assertThat(contentFile, not(results));
        assertThat(contentFileData, isIn(results));
    }

    @Test
    public void givenSongNamePrefix() {
        final ContentSpecification spec = new ContentSpecification(new SpecSearchCriteria("songName", SearchOperation.STARTS_WITH, "mash"));
        final List<ContentFile> results = contentFileRepository.findAll(spec);
        assertThat(contentFile, isIn(results));
        assertThat(contentFileData, not(isIn(results)));
    }

    @Test
    public void givenSongNameSuffi() {
        final ContentSpecification spec = new ContentSpecification(new SpecSearchCriteria("songName", SearchOperation.ENDS_WITH, "na"));
        final List<ContentFile> results = contentFileRepository.findAll(spec);
        assertThat(contentFile, isIn(results));
        assertThat(contentFileData, not(isIn(results)));
    }

    @Test
    public void givenSongNameSubstring() {
        final ContentSpecification spec = new ContentSpecification(new SpecSearchCriteria("songName", SearchOperation.CONTAINS, "ga"));
        final List<ContentFile> results = contentFileRepository.findAll(spec);

        assertThat(contentFile, isIn(results));
        assertThat(contentFileData, not(isIn(results)));
    }

    @Test
    public void givenFileSizeRange() {
//        final ContentSpecification spec = new ContentSpecification(new SpecSearchCriteria("musicFileSize", SearchOperation.GREATER_THAN, "20"));
//        final ContentSpecification spec1 = new ContentSpecification(new SpecSearchCriteria("musicFileSize", SearchOperation.LESS_THAN, "25"));
//        final List<ContentFile> results = contentFileRepository.findAll(Specification
//                .where(spec)
//                .and(spec1));
//
//        assertThat(contentFile, isIn(results));
    }


        @Test
    public void givenFileSize_whenGettingListOfContentFiles_thenCorrect() {
//        final ContentSpecification spec = new ContentSpecification(new SpecSearchCriteria("musicFileSize", SearchOperation.GREATER_THAN, "25"));
//        final List<ContentFile> results = contentFileRepository.findAll(Specification.where(spec));
//        assertThat(contentFile, isIn(results));
//        assertThat(contentFileData, not(isIn(results)));
    }



}
