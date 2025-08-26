package personal.ragproject.ingest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.ragproject.ingest.entity.RagJobEntity;

@Repository
public interface RagRepository extends JpaRepository<RagJobEntity, String> {
}
