package json.anand.com.model.utils;

import java.util.ArrayList;

import retrofit2.Response;

/**
 * Base error class.
 *
 * @author Anand Shinde
 */
public class BaseError<T> {
  /**
   * Holds HTTP status code.
   */
  private int status;

  private String code;
  private String message;
  private ArrayList<String> faultInfo;

  /**
   * Holds language information for the error message
   */
  private String language;
  /**
   * Holds the raw response data.
   */
  private Response<T> rawResponse;

  /**
   * Error for which conversation request id.
   */
  private String conversationId;

  public BaseError() {
  }

  /**
   * Constructor
   *
   * @param errorMessage It holds info about error.
   */
  public BaseError(String errorMessage) {
    message = errorMessage;
  }

  public BaseError(String code, String message,
                   ArrayList<String> faultInfo,
                   String conversationId) {
    this.code = code;
    this.message = message;
    this.faultInfo = faultInfo;
    this.conversationId = conversationId;
  }

  /**
   * To get HTTP status
   *
   * @return HTTP status
   */
  public int getStatus() {
    return status;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public ArrayList<String> getFaultInfo() {
    return faultInfo;
  }

  /**
   * To set HTTP status
   *
   * @param status Set Http status
   */
  public void setStatus(int status) {
    this.status = status;
  }

  public void setCode(String code) {
    this.code = code;
  }

  /**
   * To set error message
   *
   * @param message Set error message
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Returns the raw response
   *
   * @return Response Raw Response
   */
  public Response<T> getRawResponse() {
    return rawResponse;
  }

  /**
   * Sets List of fault information
   *
   * @param faultInfo list of fault information
   */
  public void setFaultInfo(ArrayList<String> faultInfo) {
    this.faultInfo = faultInfo;
  }

  /**
   * To set the raw response
   *
   * @param rawResponse Raw response received.
   */
  public void setRawResponse(Response rawResponse) {
    this.rawResponse = rawResponse;
  }

  /**
   * Set language information for the error message
   *
   * @param language Language information for the error message
   */
  public void setLanguage(String language) {
    this.language = language;
  }

  /**
   * Get language information for the error message
   *
   * @return language Language information for the error message
   */
  public String getLanguage() {
    return language;
  }

  /**
   * Returns conversation id.
   *
   * @return
   */
  public String getConversationId() {
    return conversationId;
  }

  /**
   * Sets the conversation id.
   *
   * @param conversationId
   */
  public void setConversationId(String conversationId) {
    this.conversationId = conversationId;
  }
}