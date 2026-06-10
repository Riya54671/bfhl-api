package com.bfhl.bfhl_api;

import com.bfhl.bfhl_api.dto.BfhlRequest;
import com.bfhl.bfhl_api.dto.BfhlResponse;
import com.bfhl.bfhl_api.service.BfhlService;
import com.bfhl.bfhl_api.service.BfhlServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BfhlServiceTest {

	private final BfhlService service = new BfhlServiceImpl();

	@Test
	void exampleA() {
		BfhlRequest req = new BfhlRequest();
		req.setData(List.of("a", "1", "334", "4", "R", "$"));
		BfhlResponse res = service.process(req);

		assertTrue(res.isSuccess());
		assertEquals(List.of("1"), res.getOddNumbers());
		assertEquals(List.of("334", "4"), res.getEvenNumbers());
		assertEquals(List.of("A", "R"), res.getAlphabets());
		assertEquals(List.of("$"), res.getSpecialCharacters());
		assertEquals("339", res.getSum());
		assertEquals("Ra", res.getConcatString());
	}

	@Test
	void exampleB() {
		BfhlRequest req = new BfhlRequest();
		req.setData(List.of("2", "a", "y", "4", "&", "-", "*", "5", "92", "b"));
		BfhlResponse res = service.process(req);

		assertEquals(List.of("5"), res.getOddNumbers());
		assertEquals(List.of("2", "4", "92"), res.getEvenNumbers());
		assertEquals(List.of("A", "Y", "B"), res.getAlphabets());
		assertEquals(List.of("&", "-", "*"), res.getSpecialCharacters());
		assertEquals("103", res.getSum());
		assertEquals("ByA", res.getConcatString());
	}

	@Test
	void exampleC() {
		BfhlRequest req = new BfhlRequest();
		req.setData(List.of("A", "ABCD", "DOE"));
		BfhlResponse res = service.process(req);

		assertTrue(res.getOddNumbers().isEmpty());
		assertTrue(res.getEvenNumbers().isEmpty());
		assertEquals(List.of("A", "ABCD", "DOE"), res.getAlphabets());
		assertEquals("0", res.getSum());
		assertEquals("EoDdCbAa", res.getConcatString());
	}
	@Test
	void emptyData() {
		BfhlRequest req = new BfhlRequest();
		req.setData(List.of());
		BfhlResponse res = service.process(req);
		assertTrue(res.isSuccess());
		assertEquals("0", res.getSum());
		assertEquals("", res.getConcatString());
	}

	@Test
	void onlyNumbers() {
		BfhlRequest req = new BfhlRequest();
		req.setData(List.of("1", "2", "3"));
		BfhlResponse res = service.process(req);
		assertEquals(List.of("1", "3"), res.getOddNumbers());
		assertEquals(List.of("2"), res.getEvenNumbers());
		assertEquals("6", res.getSum());
		assertEquals("", res.getConcatString());
	}

	@Test
	void onlySpecialChars() {
		BfhlRequest req = new BfhlRequest();
		req.setData(List.of("$", "#", "@"));
		BfhlResponse res = service.process(req);
		assertEquals(List.of("$", "#", "@"), res.getSpecialCharacters());
		assertEquals("0", res.getSum());
	}
}