package com.skillzstreet.talentspy.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.math.Quantiles;
import com.skillzstreet.talentspy.tenant.dto.AssessmentScore;
import com.skillzstreet.talentspy.tenant.dto.NameValue;
import com.skillzstreet.talentspy.tenant.entity.Answer;
import com.skillzstreet.talentspy.tenant.entity.Response;
import com.skillzstreet.talentspy.tenant.entity.Questions;
import com.skillzstreet.talentspy.tenant.entity.Skill;
import com.skillzstreet.talentspy.tenant.service.AssessmentService;
import com.skillzstreet.talentspy.tenant.service.SkillService;
import com.skillzstreet.talentspy.util.StringListDeserializer;

@RestController
@RequestMapping("/assessments")
public class AssessmentController {
	
	private static final Logger LOG = LoggerFactory
            .getLogger(AssessmentController.class);

	@Value("value")
	public String value;
	@Autowired
	private AssessmentService assessmentService;
	
	@Autowired
	private SkillService skillService;

	@RequestMapping(value="/counts", produces = "application/json")
	@ResponseBody
    public List<NameValue> getCounts()  {
		return assessmentService.getCountByStatus();
	}
	
	@RequestMapping(value="/scores", produces = "application/json")
	@ResponseBody
    public String getScoresBySkill() throws JsonProcessingException {
		List<AssessmentScore> results = assessmentService.findAllAssessmentScores();
		
		//Map<String, List<Double>> scores = new Hashtable<String, List<Double>>();
		//Map<String, String> titles = new LinkedHashMap<String, String>();
		
		//Map<String, double[]> series = new LinkedHashMap<String, double[]>();
		//List<NameValue> series = new ArrayList<NameValue>();
		
		Map<UUID, String> skillNames = new LinkedHashMap<UUID, String>();
		List<Skill> skills = skillService.findAllSkills();
		
		for(Skill s : skills) {
			skillNames.put(s.getId(), s.getName());
		}
		
		Table<String, String, List<Double>> table = HashBasedTable.create();
		Table<String, String, double[]>  lq1mq2hTable = HashBasedTable.create();
		
		//group by skill and pre/post/all
		
		for (AssessmentScore a : results) {
			/*List<Double> data = scores.get(a.getId().toString());
			if (data == null) {
				data = new ArrayList<Double>();
				scores.put(a.getId().toString(), data);
				LOG.debug(a.getTitle());
				
				String t = (a.getTitle().indexOf(":") > 0 ?  a.getTitle().substring(a.getTitle().indexOf(":") + 1).trim() : a.getTitle());
				titles.put(a.getId().toString(), t);
			}
			data.add(a.getScore());*/
			String skill = skillNames.get(a.getSkillId());
			List<Double> data = table.get(skill, a.getSubCategory());
			if (data == null) {
				data = new ArrayList<Double>();
				table.put(skill, a.getSubCategory(), data);
				LOG.debug(a.getTitle() + ": " + skill + "/" + a.getSubCategory());
			}
			
			data.add(a.getScore());
		}
		
		
		
		/*for (String key : titles.keySet()) {
			
			List<Double> values = scores.get(key);
			Double[] d = (Double[]) values.toArray(new Double[values.size()]);
			
			//double[] doubles = (double[]) ArrayUtils.toPrimitive(d); 
			double[] doubles = Stream.of(d).mapToDouble(Double::doubleValue).toArray();
			
			double l = Arrays.stream(doubles)
				      .min()
				      .getAsDouble();
			
			double q1 = Quantiles.quartiles().index(1).compute(doubles);
			double m = Quantiles.median().compute(doubles);
			double q2 = Quantiles.quartiles().index(3).compute(doubles);
			double h = Arrays.stream(doubles)
				      .max()
				      .getAsDouble();
			
			double[] data = new double[] { l, q1, m, q2, h };
			
			//series.put(titles.get(key), data);
			series.add(new NameValue(titles.get(key), data));
		}
		
		return series;
		*/
		
		String[] cols = {"Pre", "Post", "Others"};
		double[] nil = {0, 0, 0, 0, 0};
		
		for (String r : table.rowKeySet()) {
			for (String c : cols) {
			
				List<Double> values = table.get(r, c);
				if (values != null) {
					Double[] d = (Double[]) values.toArray(new Double[values.size()]);
					
					//double[] doubles = (double[]) ArrayUtils.toPrimitive(d); 
					double[] doubles = Stream.of(d).mapToDouble(Double::doubleValue).toArray();
					
					double l = Arrays.stream(doubles)
						      .min()
						      .getAsDouble();
					
					double q1 = Quantiles.quartiles().index(1).compute(doubles);
					double m = Quantiles.median().compute(doubles);
					double q2 = Quantiles.quartiles().index(3).compute(doubles);
					double h = Arrays.stream(doubles)
						      .max()
						      .getAsDouble();
					
					double[] data = new double[] { l, q1, m, q2, h };
					lq1mq2hTable.put(r, c, data);
				} else {
					lq1mq2hTable.put(r, c, nil);
				}
			
			}
			
			
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		//mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
		//mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, true);
		mapper.registerModule(new GuavaModule());

		String tableString = mapper.writeValueAsString(lq1mq2hTable);

		return tableString;
		
		
		
    }
	
	private Table<String, String, String> populateInTable(List<Response> response) throws JsonMappingException, JsonProcessingException {
		
		
		Table<String, String, String> table = HashBasedTable.create();

		for (Response r : response) {
			LOG.debug("Talent: " + r.getTalent().getProfile().getName());
			LOG.debug("Questions: " + r.getQuestions());

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			Questions questions = objectMapper.readValue(r.getQuestions(), Questions.class);

			LOG.debug("Answers: " + r.getAnswers());
			List<Answer> answers = Arrays.asList(objectMapper.readValue(r.getAnswers(), Answer[].class));

			LOG.debug("Values: " + r.getValues());

			ObjectMapper mapper = new ObjectMapper();
			SimpleModule module = new SimpleModule();
			module.addDeserializer(List.class, new StringListDeserializer());
			mapper.registerModule(module);

			List<List> valueList = Arrays.asList(mapper.readValue(r.getValues(), List[].class));

			for (int i = 0; i < questions.getProperties().size(); i++) {
				String answer = "";
				List<Integer> a = answers.get(i).getAnswer();

				if (a.size() == valueList.get(i).size()) {

					answer = "correct";
					for (int j = 0; j < valueList.get(i).size(); j++) {
						if (!valueList.get(i).contains(a.get(j).toString())) {
							answer = "wrong";
							break;
						}
					}
					LOG.debug("compare " + valueList.get(i) + " " + a.toString() + " " + answer);
				} else {
					LOG.debug("wrong " + a.toString() + "==" + valueList.get(i));
					answer = "wrong";
				}

				

				table.put(r.getTalent().getProfile().getName().replace(",", " "),
						questions.getProperties().get(i).getTitle().replace(",", " "), answer);

			}

		}
		return table;
	}
	
	@RequestMapping(value="/{id}/responses", produces = "application/json")
	@ResponseBody
	public String getResponses(@PathVariable String id, HttpServletResponse res) throws IOException {
		List<Response> response = assessmentService.findAssessmentResponses(UUID.fromString(id));

		Table<String, String, String> table = populateInTable(response);

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new GuavaModule());

		String tableString = mapper.writeValueAsString(table);

		return tableString;

	}
	
	@RequestMapping("/{id}/responses/csv")
	public void getResponsesInCsv(@PathVariable String id, HttpServletResponse res) throws IOException {
		List<Response> response = assessmentService.findAssessmentResponses(UUID.fromString(id));

		res.setContentType("text/csv");
		res.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"responses.csv\"");
		PrintWriter writer = res.getWriter();

		Table<String, String, String> table = populateInTable(response);

		Set<String> columns = table.columnKeySet();
		Set<String> rows = table.rowKeySet();

		writer.print("Name");
		for (String c : columns) {
			writer.print("," + c);
		}
		writer.println();
		for (String r : rows) {
			writer.print(r);
			for (String c : columns) {
				writer.print("," + table.get(r, c));
			}
			writer.println();
		}

		writer.flush();

	}
}
