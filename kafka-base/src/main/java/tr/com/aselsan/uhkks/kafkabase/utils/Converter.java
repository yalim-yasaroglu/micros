package tr.com.aselsan.uhkks.kafkabase.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class Converter {

  public static Object castToClass(Map<String,String> data, Class clazz){
    final ObjectMapper mapper = new ObjectMapper();
    return mapper.convertValue(data, clazz);
  }
}
