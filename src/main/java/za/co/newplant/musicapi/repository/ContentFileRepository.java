package za.co.newplant.musicapi.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import za.co.newplant.musicapi.model.ContentFile;

@Repository
public interface ContentFileRepository extends PagingAndSortingRepository<ContentFile, Long>, JpaSpecificationExecutor<ContentFile> {
}
