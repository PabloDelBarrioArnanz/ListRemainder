package com.delbarrio.pablo.listremainder.constant;

public class ConstantDefinition {

  private ConstantDefinition() { }

  public static final String ID = "id";
  public static final String NAME_QUERY_PARAM = "?".concat(ID).concat("=");

  public static final String API = "/listremainder/api";
  public static final String API_LIST_ALL = API.concat("/all");
  public static final String API_FIND_ONE = API.concat("/detail");
  public static final String API_EDIT = API.concat("/edit");
  public static final String API_CREATE = API.concat("/create");
  public static final String API_DELETE = API.concat("/delete");
  public static final String API_DELETE_ALL = API.concat("/delete-all");
  public static final String API_REDIRECT = API.concat(NAME_QUERY_PARAM);


  public static final String VALIDATION_ERROR = "Malformed list name and content cannot be empty";
}
