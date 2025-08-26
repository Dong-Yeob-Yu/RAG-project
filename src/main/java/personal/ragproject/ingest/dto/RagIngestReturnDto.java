package personal.ragproject.ingest.dto;

import personal.ragproject.ingest.entity.RagJobStatus;

public record RagIngestReturnDto(
        String jobId,
        String docId,
        RagJobStatus ragJobStatus
) {
}
