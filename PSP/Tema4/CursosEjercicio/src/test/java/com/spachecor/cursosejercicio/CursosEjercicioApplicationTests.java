package com.spachecor.cursosejercicio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.spachecor.cursosejercicio.model.entity.Curso;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest
class CursosEjercicioApplicationTests {
	@Autowired
	MockMvc mockMvc;

	@Test
	void postTest() throws Exception {
		Curso curso = new Curso("2DAM", 300, "matutino");
		XmlMapper xmlMapper = new XmlMapper();
		String cursoXml = xmlMapper.writeValueAsString(curso);
		this.mockMvc.perform(
				post("/curso")
						.contentType(MediaType.APPLICATION_XML_VALUE)
						.content(cursoXml)
		).andDo(print());
	}
	@Test
	void putTest() throws Exception {
		Curso curso = new Curso("2DAM", 300, "según el tren");
		XmlMapper xmlMapper = new XmlMapper();
		String cursoXml = xmlMapper.writeValueAsString(curso);
		this.mockMvc.perform(
				put("/curso")
						.contentType(MediaType.APPLICATION_XML_VALUE)
						.content(cursoXml)
		).andDo(print());
	}
	@Test
	void getTest() throws Exception {
		this.mockMvc.perform(get("/curso?nombre=2DAM")).andDo(print());
	}
	@Test
	void deleteTest() throws Exception {
		Curso curso = new Curso("2DAM", null, null);
		XmlMapper xmlMapper = new XmlMapper();
		String cursoXml = xmlMapper.writeValueAsString(curso);
		this.mockMvc.perform(
				delete("/curso")
						.contentType(MediaType.APPLICATION_XML_VALUE)
						.content(cursoXml)
		).andDo(print());
	}
}
