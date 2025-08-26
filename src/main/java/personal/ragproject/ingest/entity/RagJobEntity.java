package personal.ragproject.ingest.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;

import java.time.LocalDateTime;

@Entity
@Table(name = "rag_job")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class RagJobEntity implements Persistable<String> {

    @Id
    private String jobId;

    private String docId;

    private String rawText;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RagJobStatus ragJobStatus;

    private String errorMsg;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @Version
    private Long version;

    @Override
    public String getId() {
        return this.getJobId();
    }

    @Override
    public boolean isNew() {
        return createdDate == null;
    }

    public void markRunning() {
        this.ragJobStatus = RagJobStatus.RUNNING;
        this.errorMsg = null;
    }

    public void markDone() {
        this.ragJobStatus = RagJobStatus.DONE;
        this.errorMsg = null;
    }

    public void markFailed(String errorMsg) {
        this.ragJobStatus = RagJobStatus.FAILED;
        this.errorMsg = errorMsg;
    }
}
