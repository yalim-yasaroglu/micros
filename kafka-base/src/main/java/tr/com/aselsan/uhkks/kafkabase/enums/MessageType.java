package tr.com.aselsan.uhkks.kafkabase.enums;

public enum MessageType {
  CREATE ("create"),
  UPDATE ("update"),
  DELETE ("delete"),
  GET_ALL ("get_all"),
  GET_BY_ID ("get_by_id"),
  SEARCH ("search"),
  GET_BY_ID_FOR_CART("get_buy_id_for_cart"),
  RESULT ("result");

  private String type;

  MessageType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
