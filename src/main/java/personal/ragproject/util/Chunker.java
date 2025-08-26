package personal.ragproject.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Chunker {

    // 청킹 작업
    public List<String> split(String text) {

        if(text == null || text.isEmpty()) return List.of();

        int chunkSize = 1000;
        int textLength = text.length();
        List<String> chunkList = new ArrayList<>();

        for (int i = 0; i <textLength; i+=chunkSize) {
            int end = Math.min(textLength, i + chunkSize);
            chunkList.add(text.substring(i, end));
        }

        return chunkList;

    }

    // TODO :: Embedder(임베딩)


    // TODO :: Qdrant upsert(벡터 DB 업서트)
}
