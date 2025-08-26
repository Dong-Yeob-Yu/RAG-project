package personal.ragproject.ingest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import personal.ragproject.ingest.dto.RagIngestDto;
import personal.ragproject.ingest.dto.RagIngestReturnDto;
import personal.ragproject.ingest.entity.RagJobEntity;
import personal.ragproject.ingest.entity.RagJobStatus;
import personal.ragproject.ingest.repository.RagRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RagService {

    private final RagRepository ragRepository;
    private final TaskExecutor ingestTaskExecutor;

    @Transactional
    public RagIngestReturnDto execute(RagIngestDto ragIngestDto) {


        String jobId = UUID.randomUUID().toString();
        String docId = UUID.randomUUID().toString();
        RagJobStatus pending = RagJobStatus.PENDING;

        RagJobEntity ragJobEntity = RagJobEntity.builder()
                                         .jobId(jobId)
                                         .docId(docId)
                                         .rawText(ragIngestDto.rawText())
                                         .ragJobStatus(pending)
                                         .build();

        ragRepository.save(ragJobEntity);

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
//                ingestTaskExecutor.execute(() -> processor.process(jobId));
            }
        });

        return new RagIngestReturnDto(jobId, docId, pending);
    }

}
