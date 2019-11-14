package tr.com.aselsan.uhkks.kafkabase.utils;

import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter {

  public static Object castToClass(Map<String,String> data, Class clazz){
    final ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return mapper.convertValue(data, clazz);
  }
}
