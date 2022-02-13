package com.delbarrio.pablo.listremainder.constant;

public class ConstantDefinition {

  private ConstantDefinition() { }

  public static final String ID = "id";
  public static final String TOPIC = "topic";
  public static final String NEW_TOPIC = "newTopic";
  public static final String NAME_QUERY_PARAM = "?".concat(ID).concat("=");
  public static final String TOPIC_QUERY_PARAM = "?".concat(TOPIC).concat("=");
  public static final String NEW_TOPIC_QUERY_PARAM = "?".concat(NEW_TOPIC).concat("=");

  public static final String API = "/listremainder/api";
  public static final String LIST = "/list";
  public static final String API_LIST_ALL = API.concat(LIST).concat("/all");
  public static final String API_LIST_GROUPED = API.concat(LIST).concat("/grouped");
  public static final String API_LIST_TOPIC = API.concat(LIST).concat("/topic");
  public static final String API_FIND_ONE = API.concat("/detail");
  public static final String API_EDIT_TOPIC = API.concat("/edit").concat("/topic");
  public static final String API_CREATE = API.concat("/create");
  public static final String API_DELETE = API.concat("/delete");
  public static final String API_DELETE_ALL = API.concat("/delete-all");
  public static final String API_REDIRECT_CREATED = API.concat(NAME_QUERY_PARAM);
  public static final String API_REDIRECT_TOPIC_EDITED = API_LIST_TOPIC.concat(TOPIC_QUERY_PARAM);


  public static final String VALIDATION_ERROR = "Malformed list name and content cannot be empty";

  public static final String UNCOMPLETED_REMIND = "UNCOMPLETED";
  public static final String COMPLETED_REMIND = "COMPLETED";
  public static final String NONE = "NONE";
}
