package com.example.demoprojectapi;

import com.example.demoprojectapi.models.Document;
import com.example.demoprojectapi.repositories.DocumentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DemoProjectApiApplicationTests {
	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	void testInsertDocument() throws IOException {
		try {
			String pathname = "/Users/khoanguyen/Downloads/thong-bao-ve-viec-tsnn--kltn-dot-2-2021-sv-(20210604_040931_122).doc";
			File file = new File(pathname);
			System.out.println(file.getName());
			Document doc = new Document();
			doc.setName(file.getName());
			byte[] bytes = Files.readAllBytes(file.toPath());
			doc.setContnet(bytes);
			long sizeFile = bytes.length;
			doc.setSize(sizeFile);

			Document docSave = documentRepository.save(doc);

			Document exitsDoc = entityManager.find(Document.class , docSave.getId());

			assertThat(exitsDoc.getSize()).isEqualTo(sizeFile);
		}catch (Exception e){
			throw new IOException();
		}

	}

}
