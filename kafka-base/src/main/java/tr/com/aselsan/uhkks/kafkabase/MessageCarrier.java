package tr.com.aselsan.uhkks.kafkabase;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.aselsan.uhkks.kafkabase.enums.MessageType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageCarrier implements Serializable {

  private MessageType messageType;
  private Object payload;


}
