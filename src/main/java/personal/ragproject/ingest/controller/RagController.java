package personal.ragproject.ingest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personal.ragproject.ingest.dto.RagIngestDto;
import personal.ragproject.ingest.dto.RagIngestReturnDto;
import personal.ragproject.ingest.service.RagService;

/**
 * 문서 -> 벡터 DB에 넣는 과정
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ingest")
public class RagController {

    private final RagService ragService;

    @PostMapping("/text")
    public ResponseEntity<RagIngestReturnDto> rag(@RequestBody RagIngestDto ragIngestDto) {
        RagIngestReturnDto execute = ragService.execute(ragIngestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(execute);
    }
}
